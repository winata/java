package latihan_koneksi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Statement;

public class koneksi {
	
	//oracle
	public static final String dburl = "jdbc:oracle:thin:@10.17.33.131:1521:orcl";
	public static final String dbuser = "oig_oim";
	public static final String dbpass = "Admin123";
	//oracle
	
	public static void main(String[] args)throws SQLException, IOException{ 
		//String sqlQuery = "select * from dbo.mhs";
		String sqlQuery3 = "SELECT * FROM krs ORDER BY login ASC";
		//String sqlQuery2 = "SELECT act_key, count(act_key) as jumlahActKey FROM usr WHERE usr_login not like 'TESTOUT%' AND usr_login not like 'TESTE%' AND usr_login not like 'TESTO%' Group BY act_key";
		
		PreparedStatement stp = null;
		Statement stmt = null;
		ResultSet rs = null;
		BufferedReader br = null;
		String login, firstname, lastname, sqlQuery4, sqlQuery5, sqlQuery6, input;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection koneksi = DriverManager.getConnection(dburl,dbuser,dbpass);
			
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Connection koneksi = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=perpus;user=aris;password=aris1234;");
			
			//stp = koneksi.prepareStatement(sqlQuery3);
			//rs = stp.executeQuery();		
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("ingin memanipulasi tabel krs? y/n");
			input = br.readLine();
			while("y".equals(input)){
				System.out.println("ingin input ketik a");
				System.out.println("ingin update ketik b");
				System.out.println("ingin delete ketik c");
				System.out.println("ingin selesai dan baca data ketik selain huruf diatas");
				input = br.readLine();
				
				if("a".equals(input)){
					try{
						System.out.print("masukkan data login : ");
						login = br.readLine();
						System.out.print("masukkan data firstname : ");
						firstname = br.readLine();
						System.out.print("masukkan data lastname : ");
						lastname = br.readLine();
						
						sqlQuery4 = "INSERT INTO KRS(LOGIN,FIRST_NAME,LAST_NAME)VALUES('" + login + "','" + firstname + "','" + lastname + "')";
						stmt = koneksi.createStatement();
						stmt.executeUpdate(sqlQuery4);
						System.out.println("Data telah masuk");
					}catch(SQLException e){
						System.out.println(e.getMessage());
					}			
			}else if("b".equals(input)){
				try{
					System.out.print("id login yang akan id UPDATE : ");
					login = br.readLine();
					System.out.print("masukkan data firstname baru : ");
					firstname = br.readLine();
					System.out.print("masukkan data lastname baru : ");
					lastname = br.readLine();
					
					sqlQuery5 = "UPDATE KRS SET FIRST_NAME = '" + firstname + "', LAST_NAME = '" + lastname + "' WHERE LOGIN= '" + login + "'";
					stp = koneksi.prepareStatement(sqlQuery5);
					stmt = koneksi.createStatement();
					stmt.executeUpdate(sqlQuery5);
					System.out.println("Data telah diupdate");
				}catch(SQLException e){
					System.out.println(e.getMessage());
				}	
			}else if("c".equals(input)){
				try{
					System.out.print("id login yang akan di HAPUS : ");
					login = br.readLine();
					
					sqlQuery6 = "DELETE FROM krs WHERE LOGIN = '" + login + "'";
					stp = koneksi.prepareStatement(sqlQuery6);
					stmt = koneksi.createStatement();
					stmt.executeUpdate(sqlQuery6);
					System.out.println("Data telah didelete");
				}catch(SQLException e){
					System.out.println(e.getMessage());
				}
			}
				
			}
			
			
			System.out.println("berikut data dari tabel krs");
			stp = koneksi.prepareStatement(sqlQuery3);
			rs = stp.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString("login") + " - " + rs.getString("first_name") + " - " + rs.getString("last_name"));
			}	
			koneksi.close();
			
		}catch(ClassNotFoundException  e){
			e.printStackTrace();
			return;
		}
	}
}
