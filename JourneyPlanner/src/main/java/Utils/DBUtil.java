package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection conn;
	private static String url = "jdbc:mysql://localhost:3306/plandb?serverTimeZone=UTC";
	private static String id = "root";
	private static String pw = "1234";
	private static Connection getConnection()  {
		try {
			
			conn =  DriverManager.getConnection(url,id,pw);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
