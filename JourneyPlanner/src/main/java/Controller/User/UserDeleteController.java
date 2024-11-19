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

public class UserDeleteController implements SubController{

	private UserServiceImpl userService;
	
	public UserDeleteController() {
		this.userService = UserServiceImpl.getInstance();
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
			String method = req.getMethod();
			if("POST".equals(method)) {
				// Method==POST
				// 파라미터 받기
				HttpSession session = req.getSession();
				
				String userid = req.getParameter("userid");
				String password = req.getParameter("password");
				Integer age = Integer.parseInt(req.getParameter("age"));
				String gender = req.getParameter("gender");
				UserDto userDto = new UserDto(userid,password,"ROLE_USER",age,gender);
				
				// 유효성검사
				if(session==null) {
					System.out.println("!");
					resp.sendRedirect(req.getContextPath() + "/user/login?message=" + URLEncoder.encode("로그인이 필요한 서비스입니다.","UTF-8"));
					return ;
				}
				UserDto SessionUserDto = (UserDto)session.getAttribute("userDto");
				if(!userid.equals(SessionUserDto.getUserid())) {
					System.out.println("?");
					resp.sendRedirect(req.getContextPath() + "/user/login?message=" + URLEncoder.encode("로그인이 필요한 서비스입니다.","UTF-8"));
					return ;
				}
				Map<String,Object> rValue = userService.userQuit(userDto);
				
				
				boolean isQuit = (boolean)rValue.get("isQuit");
				String message = (String)rValue.get("message");
				
				// 뷰로이동(내용전달 - ?)
				if(isQuit) {
					session.invalidate();
					resp.sendRedirect(req.getContextPath() +"/?message=" + URLEncoder.encode(message,"UTF-8"));
					return ;
				}else {
					req.setAttribute("message", message);
					req.getRequestDispatcher(req.getContextPath() +"/").forward(req, resp);
					return ;
				}
			}
		} catch(Exception e) {
			try {
				ExceptionHandler(e, req, resp);
			} catch (ServletException e1) {
				try{  throw new ServletException(e1); }catch(Exception e2) {e2.printStackTrace();}
			}
		}
	}
	
}
