package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import Domain.Common.Dto.PlannerDto;
import Domain.Common.Dto.UserDto;
import Utils.DBConnect;

public class PlannerDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static PlannerDao instance;
	
	public static PlannerDao getInstance() {
		if (instance == null)
			instance = new PlannerDao();
		return instance;
	}
	
	
	
	
	
	
	
	
}
