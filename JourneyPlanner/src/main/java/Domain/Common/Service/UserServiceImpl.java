package Domain.Common.Service;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.User;

import Domain.Common.Dao.UserDaoImpl;
import Domain.Common.Dto.UserDto;


public class UserServiceImpl {
	private static UserDaoImpl userDaoImpl;
	private static UserServiceImpl instance;
	
	public UserServiceImpl() {
		userDaoImpl = UserDaoImpl.getInstance();
	}
	
	public static UserServiceImpl getInstance() {
		if(instance==null) {
			instance = new UserServiceImpl();
		}
		
		return instance;
	}

	
	// CRUD
	// (1) 회원가입
	public boolean userJoin(UserDto dto) throws Exception {
		
		return userDaoImpl.insert(dto) > 0;
	}
	
	// 회원 조회
	public UserDto getUser(int userid) throws Exception {
		
		return userDaoImpl.select(userid);
	}
	// 회원 전체 조회
	public List<UserDto> getUsers() throws Exception{
		
		return userDaoImpl.select();
	}
	// 회원 삭제
	public boolean userExit(UserDto dto) throws Exception {
		
		return userDaoImpl.delete(dto) > 0;
	}
	//
}
