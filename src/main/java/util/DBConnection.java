package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection connectionToDatabase (){
		Connection conn = null;
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentlist", "root", "Suraj123@");
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
}
