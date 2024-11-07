package Domain.Common.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl {
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static UserDaoImpl instance = null;
	public static UserDaoImpl getInstance() {
		if(instance==null)
			instance = new UserDaoImpl();
		return instance;
	}
	
}
