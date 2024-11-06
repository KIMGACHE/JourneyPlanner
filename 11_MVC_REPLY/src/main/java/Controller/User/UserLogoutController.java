package Controller.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Service.UserServiceImpl;

public class UserLogoutController implements SubController{

	private UserServiceImpl userService;
	
	public UserLogoutController() throws ServletException {
		
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
				System.out.println("[BC] GET /logout..");
			
				HttpSession session = req.getSession();
				//session.removeAttribute("role");
				//session.removeAttribute("username");
				session.invalidate();
				
				resp.sendRedirect(req.getContextPath() + "/");
				return ;
			}
			
			//Method==POST-> 회원가입
			
			
			
			
			
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
