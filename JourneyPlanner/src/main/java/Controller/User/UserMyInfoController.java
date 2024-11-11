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

public class UserMyInfoController implements SubController {

	private UserServiceImpl userService;
	
	public UserMyInfoController() throws ServletException {
		try {
			this.userService = UserServiceImpl.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionHandler(e,null,null);
			//예외 처리 함수로 던지기
		}
	}

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
				System.out.println("[BC] GET /myinfo..");
				HttpSession session = req.getSession();
				String userid = (String)session.getAttribute("userid");
				UserDto userDto = userService.getUser(userid);
				req.setAttribute("userDto", userDto);
				req.getRequestDispatcher("/WEB-INF/view/user/myinfo.jsp").forward(req, resp);
				return ;
			}
			
			//Method==POST-> 도서 등록처리
				
			//파라미터 받기
			String username = req.getParameter("userid");
			String password = req.getParameter("password");
			String role = req.getParameter("role");
			Integer age = Integer.parseInt(req.getParameter("age"));
			String gender = req.getParameter("gender");

			// 유효성 확인
			if(!isValid(null)) {
				//
			}
			
			// 서비스 실행
			UserDto userDto = new UserDto(username,password,role,age,gender);
			Map<String,Object> rvalue = userService.userUpdate(userDto);
			String message = (String)rvalue.get("message");
			Boolean isUpdated = (Boolean)rvalue.get("isUpdated");
			
			if(isUpdated) {
				resp.sendRedirect(req.getContextPath() +"/user/myinfo?userid=${userid}" );
				return ;
			} else {
				req.setAttribute("message", message);
				req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
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
