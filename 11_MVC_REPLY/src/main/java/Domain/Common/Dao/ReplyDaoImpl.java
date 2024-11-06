package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dao.ConnectionPool.ConnectionItem;
import Domain.Common.Dao.ConnectionPool.ConnectionPool;
import Domain.Common.Dto.ReplyDto;



public class ReplyDaoImpl {

	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private ConnectionPool connectionPool;
	private ConnectionItem connItem;
	
	//싱글톤 패턴 처리코드
	private ReplyDaoImpl() throws SQLException, ClassNotFoundException {
	
		connectionPool = ConnectionPool.getInstance();
	};
	
	private static ReplyDaoImpl instance = null;
	public static ReplyDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new ReplyDaoImpl();
		return instance;
	}
	
	//CRUD 
	public int insert(ReplyDto dto) throws Exception{
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		
		pstmt = conn.prepareStatement("insert into tbl_reply values(null,?,?,?,now())");
		pstmt.setString(1, dto.getUsername());
		pstmt.setLong(2, dto.getBookCode());
		pstmt.setString(3, dto.getContents());
		
		int result =  pstmt.executeUpdate();
		
		//자원제거
		pstmt.close();
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return result;
	}

	public List<ReplyDto> select(Long bookCode) throws Exception {
		// TODO Auto-generated method stub
		List<ReplyDto> list = new ArrayList();
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		pstmt = conn.prepareStatement("SELECT * FROM bookdb.tbl_reply where bookCode= ? order by replyId desc");
		pstmt.setLong(1, bookCode);
		
		rs = pstmt.executeQuery();
		ReplyDto dto=null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new ReplyDto();
				dto.setReplyId(rs.getInt("replyId"));
				dto.setBookCode(rs.getLong("bookCode"));
				dto.setUsername(rs.getString("username"));
				dto.setContents(rs.getString("contents"));
				dto.setCreateAt(rs.getObject("createAt",LocalDateTime.class));
				
				list.add(dto);
			}
		}
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return list;
		
	}
	
	
//	public List<BookDto> select() throws Exception{}
//	public BookDto select(long bookCode) throws Exception{}

//	public int update(BookDto dto) throws Exception{}	
//	public int delete(long bookCode) throws Exception {}
//	public List<BookDto> select(int offset, int amount) throws Exception{}
//	public List<BookDto> select(int offset, int amount,String type, String keyword) throws Exception{}
//	public int count() throws Exception{}
//	public int count(Criteria criteria) throws Exception{}


	
}
