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
			// 메서드가 포스트인지 확인
			if("POST".equals(method)) {

				// 요청 파라미터 받기
				String userid = req.getParameter("userid");
				String password = req.getParameter("password");
				Integer age = Integer.parseInt(req.getParameter("age"));
				String gender = req.getParameter("gender");
				
				// 파라미터로 Dto 객체 생성
				UserDto userDto = new UserDto(userid,password,"ROLE_USER",age,gender);
				
				// UserLoginController에서 로그인 성공 시 session에 데이터를 담는대 그걸 다시 들고옴
				HttpSession session = req.getSession();
				
				// session으로 로그인 유무 확인
				// session정보가 없으면 로그인 페이지로 redirect
				if(session==null) {
					System.out.println("!");
					resp.sendRedirect(req.getContextPath() + "/user/login?message=" + URLEncoder.encode("로그인이 필요한 서비스입니다.","UTF-8"));
					return ;
				}
				// session에 담긴 userdto를 불러옴
				UserDto SessionUserDto = (UserDto)session.getAttribute("userDto");
				// session에 담긴 userdto의 id와 파라미터상의 id를 비교해서 다르면 redirect
				if(!userid.equals(SessionUserDto.getUserid())) {
					System.out.println("?");
					resp.sendRedirect(req.getContextPath() + "/user/login?message=" + URLEncoder.encode("로그인이 필요한 서비스입니다.","UTF-8"));
					return ;
				}
				
				// 삭제 메서드 실행 후 결과를 rValue에 저장
				// rValue => [{ 'isQuit' : boolean },{ 'message' : String }]
				Map<String,Object> rValue = userService.userQuit(userDto);
				
				
				boolean isQuit = (boolean)rValue.get("isQuit");
				String message = (String)rValue.get("message");
				
				// isQuit로 성공유무 확인후 리다이렉트
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