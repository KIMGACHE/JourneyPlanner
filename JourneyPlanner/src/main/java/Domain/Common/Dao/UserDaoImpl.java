package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.UserDto;
import Utils.DBConnect;

public class UserDaoImpl {


private Connection conn;
private PreparedStatement pstmt;
private ResultSet rs;

private static UserDaoImpl instance;

private UserDaoImpl() {}

public static UserDaoImpl getInstance() {
	if (instance == null)
		instance = new UserDaoImpl();
	return instance;
}

//회원가입
public int insert(UserDto dto) throws Exception {

	int result = 0;
	try {
		conn = DBConnect.getInstance().getConnection();
		pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?,?,?)");
		pstmt.setString(1, dto.getUserid());
		pstmt.setString(2, dto.getPassword());
		pstmt.setString(3, dto.getRole());
		pstmt.setInt(4, dto.getAge());
		pstmt.setString(5, dto.getGender());

		result = pstmt.executeUpdate();

	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		pstmt.close();
		conn.close();
	}

	return result;
}

//회원 단건 조회
public UserDto select(String userid) throws SQLException {
	UserDto dto = null;
	try {
		conn = DBConnect.getInstance().getConnection();
		pstmt = conn.prepareStatement("select * from tbl_user where userid=?");
		pstmt.setString(1, userid);
		
		rs = pstmt.executeQuery();
		if(rs!=null) {
			
			if(rs.next()) {
				dto = new UserDto();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setRole(rs.getString("role"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		conn.close();
		pstmt.close();
	}
	return dto;
}


//회원 전체 조회
public List<UserDto> select() throws SQLException{

	List<UserDto> list = new ArrayList();
	UserDto dto = null;
	try {
		conn = DBConnect.getInstance().getConnection();
		pstmt = conn.prepareStatement("select * from tbl_user");
		
		rs = pstmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				dto = new UserDto();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setRole(rs.getString("role"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				
				list.add(dto);
			}
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		conn.close();
		pstmt.close();
	}
	
	
	return list;

}

//회원 정보 수정
public int update(UserDto dto) throws Exception {

	int result = 0;
	try {
		conn = DBConnect.getInstance().getConnection();
		pstmt= conn.prepareStatement("update tbl_user set password=?,role=?,age=?, gender=? where userid=?");
		pstmt.setString(1, dto.getPassword());
		pstmt.setString(2, dto.getRole());
		pstmt.setInt(3, dto.getAge());
		pstmt.setString(4, dto.getGender());
		pstmt.setString(5, dto.getUserid());

		result = pstmt.executeUpdate();

	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		pstmt.close();
		conn.close();
	}

	return result;
}

//회원 정보 삭제
public int delete(UserDto dto) throws SQLException {
	int result = 0;
	try {
		conn = DBConnect.getInstance().getConnection();
		pstmt = conn.prepareStatement("delete from tbl_user where userid=?");
		pstmt.setString(1, dto.getUserid());
		
		result = pstmt.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
		
	}finally {
		conn.close();
		pstmt.close();
	}

	return result;
}



}