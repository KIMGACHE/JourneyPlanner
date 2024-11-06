package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dao.ConnectionPool.ConnectionItem;
import Domain.Common.Dao.ConnectionPool.ConnectionPool;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.Criteria;



public class BookDaoImpl {
	//DBCONN 속성
//	private String id="root";
//	private String pw="1234";
//	private String url="jdbc:mysql://localhost:3306/bookDB";
//	
//	private Connection conn ;

	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private ConnectionPool connectionPool;
	private ConnectionItem connItem;
	

	
	
	//싱글톤 패턴 처리코드
	private BookDaoImpl() throws SQLException, ClassNotFoundException {
	
		connectionPool = ConnectionPool.getInstance();
	};
	
	private static BookDaoImpl instance = null;
	public static BookDaoImpl getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new BookDaoImpl();
		return instance;
	}
	
	//CRUD 
	public List<BookDto> select() throws Exception{
		
		List<BookDto> list = new ArrayList();
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		pstmt = conn.prepareStatement("select * from tbl_book");
		
		rs = pstmt.executeQuery();
		BookDto dto=null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getLong("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				
				list.add(dto);
			}
		}
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return list;
	}
	
	public BookDto select(long bookCode) throws Exception{
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		
		pstmt = conn.prepareStatement("select * from tbl_book where bookCode=?");
		pstmt.setLong(1, bookCode);
		
		rs = pstmt.executeQuery();
		BookDto dto=null;
		if(rs!=null) {
			
			if(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getLong("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
			}
			
		}
		
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		rs.close();
		pstmt.close();
		
		return dto;
	}


	public int insert(BookDto dto) throws Exception{
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		
		pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
		pstmt.setLong(1,dto.getBookCode());
		pstmt.setString(2, dto.getBookName());
		pstmt.setString(3, dto.getPublisher());
		pstmt.setString(4, dto.getIsbn());
		
		int result =  pstmt.executeUpdate();
		
		//자원제거
		pstmt.close();
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return result;
	}

	public int update(BookDto dto) throws Exception{
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		
		pstmt = conn.prepareStatement("update tbl_book set bookName=?,publisher=?,isbn=? where bookCode=?");
		pstmt.setString(1, dto.getBookName());
		pstmt.setString(2, dto.getPublisher());
		pstmt.setString(3, dto.getIsbn());
		pstmt.setLong(4,dto.getBookCode());
		
		int result =  pstmt.executeUpdate();
		
		//자원제거
		pstmt.close();
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return result;
		
	}
	
	public int delete(long bookCode) throws Exception {
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		
		pstmt = conn.prepareStatement("delete from tbl_book where bookCode=?");
		pstmt.setLong(1,bookCode);
		
		int result =  pstmt.executeUpdate();
		
		//자원제거
		pstmt.close();
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return result;
	}

	public List<BookDto> select(int offset, int amount) throws Exception{
		List<BookDto> list = new ArrayList();
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		pstmt = conn.prepareStatement("SELECT * FROM bookdb.tbl_book order by bookcode desc limit ?,?");
		pstmt.setInt(1, offset);
		pstmt.setInt(2, amount);
		
		rs = pstmt.executeQuery();
		BookDto dto=null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getLong("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				
				list.add(dto);
			}
		}
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return list;
	}
	
	public List<BookDto> select(int offset, int amount,String type, String keyword) throws Exception{
		List<BookDto> list = new ArrayList();
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		//pstmt = conn.prepareStatement("SELECT * FROM bookdb.tbl_book where "+type+" like '%"+keyword+"%' order by bookcode desc limit ?,?");

		if("all".equals(type)) {
			String sql = "select distinct * from tbl_book where"
					+ " bookCode like ? or "
					+ " bookname like ? or "
					+ " publisher like ? or "
					+ " isbn like ?" 
					+ " order by bookCode desc"
					+ " limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setString(4, "%"+keyword+"%");
			pstmt.setInt(5, offset);
			pstmt.setInt(6, amount);
			
		}else {
			pstmt = conn.prepareStatement("SELECT * FROM bookdb.tbl_book where "+type+" like ? order by bookcode desc limit ?,?");
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, offset);
			pstmt.setInt(3, amount);
		}
		
		

		
		rs = pstmt.executeQuery();
		BookDto dto=null;
		if(rs!=null) {
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getLong("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				
				list.add(dto);
			}
		}
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		return list;
	}
	
	
	
	public int count() throws Exception{
		
		//Connection Pool code
		connItem = connectionPool.getConnection();
		Connection conn = connItem.getConn();
		int cnt=0;
		pstmt = conn.prepareStatement("select count(*) from tbl_book");
		rs = pstmt.executeQuery();
		
		if(rs!=null) {	
			if(rs.next()) {
				cnt= rs.getInt(1);
			}	
		}
		//Connection Pool code
		connectionPool.releaseConnection(connItem);
		
		rs.close();
		pstmt.close();
		
		return cnt;
	}

	public int count(Criteria criteria) throws Exception{
			//Connection Pool code
			connItem = connectionPool.getConnection();
			Connection conn = connItem.getConn();
			int cnt=0;
			if("all".equals(criteria.getType())){
				String sql = "select distinct count(*) from tbl_book where"
						+ " bookCode like ? or "
						+ " bookname like ? or "
						+ " publisher like ? or "
						+ " isbn like ?" ;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+criteria.getKeyword()+"%");
				pstmt.setString(2, "%"+criteria.getKeyword()+"%");
				pstmt.setString(3, "%"+criteria.getKeyword()+"%");
				pstmt.setString(4, "%"+criteria.getKeyword()+"%");

			}else {
				pstmt = conn.prepareStatement("select count(*) from tbl_book where "+criteria.getType()+" like ?");
				pstmt.setString(1, "%"+criteria.getKeyword()+"%");
			}
			rs = pstmt.executeQuery();
			
			if(rs!=null) {	
				if(rs.next()) {
					cnt= rs.getInt(1);
				}	
			}
			//Connection Pool code
			connectionPool.releaseConnection(connItem);
			
			rs.close();
			pstmt.close();
			
			return cnt;
	}


	
}
