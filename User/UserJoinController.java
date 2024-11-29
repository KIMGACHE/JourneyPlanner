package Controller.User;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.UserDto;
import Domain.Common.Service.UserServiceImpl;

public class UserJoinController implements SubController{

	private UserServiceImpl userService;
	
	public UserJoinController() {
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
			// Method==GET
			String method = req.getMethod();
			if("GET".equals(method)) {
				System.out.println("[BC] GET /join..");
				req.getRequestDispatcher("/WEB-INF/view/user/join.jsp").forward(req, resp);
				return ;
			}
			
			// Method==POST
			// 파라미터 받기
			String userid = req.getParameter("userid");
			String password = req.getParameter("password");
			Integer age = Integer.parseInt(req.getParameter("age"));
			String gender = req.getParameter("gender");
			UserDto userDto = new UserDto(userid,password,"ROLE_USER",age,gender);
			
			// 유효성검사
			
			Map<String,Object> rValue = userService.userJoin(userDto);
			
			
			boolean isJoined = (boolean)rValue.get("isJoined");
			String message = (String)rValue.get("message");
			
			// 뷰로이동(내용전달)
			if(isJoined) {
				resp.sendRedirect(req.getContextPath() +"/?message=" + URLEncoder.encode(message,"UTF-8"));
				return ;
			}else {
				req.setAttribute("message", message);
				req.getRequestDispatcher("/WEB-INF/view/user/join.jsp").forward(req, resp);
				return ;
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
