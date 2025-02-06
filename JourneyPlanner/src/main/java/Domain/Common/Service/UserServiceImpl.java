package Domain.Common.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import Domain.Common.Dao.UserDaoImpl;
import Domain.Common.Dto.UserDto;
import Utils.DBConnect;

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
	public UserDto getUser(String userid) throws Exception {
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
	public Map<String,Object> userQuit(UserDto dto) throws Exception {
		Map<String,Object> returnVal = new HashMap();
		
		try {
			
			int result = userDaoImpl.delete(dto);
			if(result>0) {
				returnVal.put("isQuit",true );
				returnVal.put("message", "회원탈퇴 완료");
			}else {
				returnVal.put("isQuit", false);
				returnVal.put("message", "회원탈퇴 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}

	
	// 회원 정보 수정
	public Map<String,Object> userUpdate(UserDto dto){
		Map<String,Object> returnVal = new HashMap();
		
		try {
			int result = userDaoImpl.update(dto);
			if(result >0) {
				returnVal.put("isUpdated", true);
				returnVal.put("message", "회원 정보수정 완료");
			}else {
				returnVal.put("isUpdated", false);
				returnVal.put("message","회원 정보수정 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnVal;
	}
	
	//로그인
		public Map<String, Object> login(String userid, String password) throws Exception {
			//TX Start
			Map<String,Object> returnValue=null;
			try {
					returnValue = new HashMap();
					
					//요청한 username 과 동일한 계정이 있는지확인(tbl_user)
					UserDto dbUserDto = userDaoImpl.select(userid);
					if(dbUserDto==null) {
						returnValue.put("success", false);
						returnValue.put("message", "계정이 존재하지 않습니다.");
						return returnValue;	
					}
					
					//요청한 password 가 db에 저장된 password와 동일한지 확인
					String dbPw = dbUserDto.getPassword();		//en
					if(!password.equals(dbPw)) {
						returnValue.put("success", false);
						returnValue.put("message", "패스워드가 일치하지 않습니다.");
						return returnValue;		
					}		
					
					//sessionId를 반환
					returnValue.put("success", true);
					returnValue.put("message", "로그인 성공!");
					
			}catch(Exception e) {
				throw e;
			}
			return returnValue;
		}
	
}
