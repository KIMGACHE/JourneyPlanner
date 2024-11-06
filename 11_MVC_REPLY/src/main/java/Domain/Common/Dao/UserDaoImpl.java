package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Domain.Common.Dao.ConnectionPool.ConnectionItem;
import Domain.Common.Dao.ConnectionPool.ConnectionPool;
import Domain.Common.Dto.UserDto;

public class UserDaoImpl {
	//DBCONN 속성
//	private String id="root";
//	private String pw="1234";
//	private String url="jdbc:mysql://localhost:3306/bookDB";
//	
//	private Connection conn ;
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//
	//ConnectionPoolByHikari connectionPool; 
	ConnectionPool connectionPool;
	
	//싱글톤 패턴
	private UserDaoImpl() throws Exception{
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		conn = DriverManager.getConnection(url,id,pw);
//		System.out.println("UserDaoImpl DB Connection Success");
		
		//this.connectionPool = ConnectionPoolByHikari.getInstance();
		this.connectionPool = ConnectionPool.getInstance();
		
	};
	private static UserDaoImpl instance;
	public static UserDaoImpl getInstance() throws Exception {
		if(instance==null)
			instance = new UserDaoImpl();
		return instance;
	}
	
	//CRUD
	public List<UserDto> select(){
		return null;
	}
	public UserDto select(String username) throws Exception {
		//Connection Pool code
		ConnectionItem connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		
		pstmt = conn.prepareStatement("select * from tbl_user where username=?");
		pstmt.setString(1, username);
		
		rs = pstmt.executeQuery();
		UserDto dto=null;
		if(rs!=null) {
			
			if(rs.next()){
				dto = new UserDto();
				dto.setUsername(rs.getString("username"));	
				dto.setPassword(rs.getString("password"));
				dto.setRole(rs.getString("role"));
				dto.setLocked(rs.getBoolean("isLocked"));
			}
		}
		
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		rs.close();
		pstmt.close();
		
		return dto;
	}
	
	
	public int insert(UserDto dto) throws Exception {
		//Connection Pool code
		ConnectionItem connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
	
		pstmt = conn.prepareStatement("insert into tbl_user values(?,?,?,?)");
		pstmt.setString(1, dto.getUsername());
		pstmt.setString(2, dto.getPassword());
		pstmt.setString(3, dto.getRole());
		pstmt.setBoolean(4, false);

		int result = pstmt.executeUpdate();
		
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return result;
	}
	
	public int update(UserDto dto) {
		return 0;
	}
	public int delete(long bookCode) {
		return 0;
	}

}







