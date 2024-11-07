package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.PlannerDto;

public class PlannerDaoImpl {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String url="jdbc:mysql://localhost:3306/plandb";;
	private String id="root";
	private String pw="1234";
	private Connection conn;
	
	private static PlannerDaoImpl instance = null;
	public static PlannerDaoImpl getInstance() {
		if(instance==null)
			instance = new PlannerDaoImpl();
		return instance;
	}
	
	public void init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		conn = DriverManager.getConnection(url,id,pw);
	}
	
	// CRUD
	// Insert
	public boolean insert(PlannerDto plannerDto) throws ClassNotFoundException, SQLException {
		init();
		pstmt = conn.prepareStatement("insert into tbl_planner values(null,?,?,?,?)");
		pstmt.setInt(1, plannerDto.getAreacode());
		pstmt.setInt(2, plannerDto.getCitycode());
		pstmt.setDate(3,Date.valueOf(plannerDto.getStartdate()));
		pstmt.setDate(4,Date.valueOf(plannerDto.getEnddate()));
		
		int result = pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
		
		return result > 0;
	}
	// Update
	public boolean update(PlannerDto plannerDto) throws ClassNotFoundException, SQLException {
		init();
		pstmt = conn.prepareStatement("update tbl_planner set areacode=?, citycode=?,startdate=?,enddate=? where plannerid=?");
		pstmt.setInt(1, plannerDto.getAreacode());
		pstmt.setInt(2, plannerDto.getCitycode());
		pstmt.setDate(3,Date.valueOf(plannerDto.getStartdate()));
		pstmt.setDate(4,Date.valueOf(plannerDto.getEnddate()));
		pstmt.setInt(5, plannerDto.getPlannerid());
		
		int result = pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
		
		return result > 0;
	}
	// Delete
	public boolean delete(int plannerid) throws ClassNotFoundException, SQLException {
		init();
		pstmt = conn.prepareStatement("delete from tbl_planner where plannerid=?");
		pstmt.setInt(1, plannerid);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result > 0;
	}
	// Select
	public PlannerDto select(int plannerid) throws ClassNotFoundException, SQLException {
		init();
		PlannerDto dto = null;
		pstmt = conn.prepareStatement("select * from tbl_planner where plannerid=?");
		pstmt.setInt(1, plannerid);
		
		rs = pstmt.executeQuery();
		
		if(rs!=null) {
			if(rs.next()) {
				dto = new PlannerDto();
				dto.setPlannerid(plannerid);
				dto.setAreacode(rs.getInt("areacode"));
				dto.setCitycode(rs.getInt("areacode"));
				dto.setStartdate(rs.getDate("startdate").toLocalDate());
				dto.setEnddate(rs.getDate("enddate").toLocalDate());
			}
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return dto;
	}
	// SelectAll
	public List<PlannerDto> selectAll() throws ClassNotFoundException, SQLException {
		init();
		List<PlannerDto> list = new ArrayList();
		PlannerDto dto = null;
		pstmt = conn.prepareStatement("select * from tbl_planner");
		
		rs = pstmt.executeQuery();
		
		if(rs!=null) {
			while(rs.next()) {
				dto = new PlannerDto();
				dto.setPlannerid(rs.getInt("plannerid"));
				dto.setAreacode(rs.getInt("areacode"));
				dto.setCitycode(rs.getInt("areacode"));
				dto.setStartdate(rs.getDate("startdate").toLocalDate());
				dto.setEnddate(rs.getDate("enddate").toLocalDate());
				list.add(dto);
			}
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
}
