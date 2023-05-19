package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

// 연결
public class DBConnection {
	
	private final static String TAG = "DBConnection : ";
	
	Connection conn;
	Statement stmt;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String driver = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "chat1_1";
			String pass = "bitc5600";
			
			Connection conn = 
					DriverManager.getConnection(driver, id, pass);
			
			System.out.println("DB 연결 성공");
			return conn;
		} catch (Exception e) {
			System.out.println(TAG + "DB 연결 실패" + e.getMessage());
		}
		return null;
	}
	
}