package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Domain.Common.Dto.UserDto;

public class UserDao {

	private PreparedStatement pstmt;
	private ResultSet rs;

	private static UserDao instance = null;

	public static UserDao getInstance() {
		if (instance == null)
			instance = new UserDao();
		return instance;
	}
	
	//회원가입
	public int insert(UserDto dto) throws Exception {
		
		Connection conn = null;
		pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?,?,?)");
		pstmt.setString(1, dto.getUserid());
		pstmt.setString(2, dto.getPassword());
		pstmt.setString(3, dto.getRole());
		pstmt.setInt(4, dto.getAge());
		pstmt.setString(5, dto.getGender());
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		return result;
	}
	
	//회원 단건 조회
	public void select() {
		
	}
	
	public List<UserDto> select(int userid){
		return null;
		
	}
	
	
	
	public int update() {
		
		return 0;
	}
	
	public int delete() {
		
		return 0;
	}
	
	
	
}
