package Controller.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.UserDto;
import Domain.Common.Service.UserServiceImpl;

public class UserMyinfoController implements SubController{

	private UserServiceImpl userService;
	
	public UserMyinfoController() throws ServletException {
		
		try {
			this.userService = UserServiceImpl.getInstance();
		
		}catch(Exception e) {
			//예외핸들러로 전달
			ExceptionHandler(e,null,null);
		}
	
	}
	
	//예외처리함수
	public void ExceptionHandler(Exception e,HttpServletRequest req,HttpServletResponse resp) throws ServletException{
		try {
			req.setAttribute("exception", e);
			req.getRequestDispatcher("/WEB-INF/view/user/error.jsp").forward(req, resp);
		
		}catch(Exception ex) {
			throw new ServletException(ex);
		}

	}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			//Method==GET -> 페이지 표시(Forwarding)
			String method = req.getMethod();
			if("GET".equals(method)) {
				System.out.println("[BC] GET /user/myinfo..");
				req.getRequestDispatcher("/WEB-INF/view/user/myinfo.jsp").forward(req, resp);
				return ;
			}
			
			//Method==POST-> 회원가입
			
			
			// 파라미터 받기
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String role = req.getParameter("role");
			
			// 유효성 확인
			if(!isValid(null)) {
				//
			}
			
			// 서비스 실행
			UserDto userDto = new UserDto(username,password,role,false);
			boolean isJoined = 	userService.memberJoin(userDto);
			
			// 뷰로이동(내용전달 - ?)
			if(isJoined) {
				resp.sendRedirect(req.getContextPath() +"/login");
				return ;
			}else {
				req.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(req, resp);
				return ;
			}
			
			
			
		}catch(Exception e) {
			
				try {
					ExceptionHandler(e,req,resp);
				
				} catch (ServletException e1) {
				
					 try{  throw new ServletException(e1); }catch(Exception e2) {e2.printStackTrace();}
				}

			System.out.println("[BC] Exception 발생.." + e);
		}
		
		
	}
	
	private boolean isValid(Object dto) {
		return true;
	}

}
