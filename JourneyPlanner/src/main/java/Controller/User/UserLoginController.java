package Controller.User;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Dto.UserDto;
import Domain.Common.Service.UserServiceImpl;

public class UserLoginController implements SubController{

	private UserServiceImpl userService;
	
	public UserLoginController() throws ServletException {
		
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
				System.out.println("[BC] GET /login..");
				req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
				return ;
			}
			
			//Method==POST-> 회원가입
			
			
			// 파라미터 받기
			String userid = req.getParameter("userid");
			String password = req.getParameter("password");
			
			
			// 서비스 실행
			HttpSession session = req.getSession();
			if(session.getAttribute("userDto")!=null) {
				resp.sendRedirect(req.getContextPath() +"/?message=" + URLEncoder.encode("이미 로그인된 상태입니다.","UTF-8"));
				return ;
			}
			Map<String,Object> rValue = userService.login(userid,password);
			
			boolean isLogined = (boolean)rValue.get("success");
			String message = (String)rValue.get("message");
			UserDto userDto = userService.getUser(userid);
			
			
			// 뷰로이동 (userDto session에 저장)
			if(isLogined) {
				session.setAttribute("userDto", userDto);
				resp.sendRedirect(req.getContextPath() +"/?message=" + URLEncoder.encode(message,"UTF-8"));
				return ;
			}else {
				req.setAttribute("message", message);
				req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
				return ;
			}
			
			
			
		}catch(Exception e) {
			
				try {
					ExceptionHandler(e,req,resp);
				
				} catch (ServletException e1) {
				
					 try{  throw new ServletException(e1); }catch(Exception e2) {e2.printStackTrace();}
				}

			System.out.println("[유저컨] Exception 발생.." + e);
		}
		
		
	}
	
	private boolean isValid(Object dto) {
		return true;
	}

}