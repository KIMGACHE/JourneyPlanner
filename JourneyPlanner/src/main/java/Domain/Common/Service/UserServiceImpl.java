package Domain.Common.Service;

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
	public void userJoin(UserDto userDto) {
		
	}
}
