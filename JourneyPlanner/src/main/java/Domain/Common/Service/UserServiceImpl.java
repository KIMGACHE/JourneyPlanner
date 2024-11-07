package Domain.Common.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Common.Dao.UserDaoImpl;
import Domain.Common.Dto.UserDto;

public class UserServiceImpl {
	private static UserDaoImpl userDaoImpl;
	private static UserServiceImpl instance;

	public UserServiceImpl() {
		userDaoImpl = UserDaoImpl.getInstance();
	}

	public static UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}

		return instance;
	}

	// CRUD
	// (1) 회원가입
	public Map<String, Object> userJoin(UserDto dto) throws Exception {
		Map<String, Object> returnVal = new HashMap();
		int result = userDaoImpl.insert(dto);

		try {
			if (result > 0) {
				returnVal.put("isJoined", true);
				returnVal.put("message", "회원가입 완료");

			} else {
				returnVal.put("isJoined", false);
				returnVal.put("message", "회원가입 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	

	// 회원 조회
	public UserDto getUser(int userid) throws Exception {
		UserDto result = null;
		try {
			result = userDaoImpl.select(userid);

			if (result == null) {
				throw new Exception("지정하신 회원이 존재하지 않습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
	// 회원 전체 조회
	public List<UserDto> getUsers() throws Exception {
		List<UserDto> userList = userDaoImpl.select();
		try {
			if (userList != null && !userList.isEmpty()) {
				for (UserDto list : userList)
					System.out.println("UserList : " + list);
			} else {
				throw new Exception("회원이 하나도 존재하지 않습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}
	

	// 회원 삭제
	public Map<String,Object> userExit(UserDto dto) throws Exception {
		Map<String,Object> returnVal = new HashMap();
		int result = userDaoImpl.delete(dto);
		
		try {
			returnVal.put("isDelete", );
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	//
}
