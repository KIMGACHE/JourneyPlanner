package Controller.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Dto.PlannerDto;
import Domain.Common.Dto.UserDto;
import Domain.Common.Service.PlannerServiceImpl;

public class PlannerDeleteController implements SubController{
	private PlannerServiceImpl plannerService;
	
	public PlannerDeleteController() {
		this.plannerService = PlannerServiceImpl.getInstance();
	}
	
	//예외처리함수
	public void ExceptionHandler(Exception e,HttpServletRequest req,HttpServletResponse resp) throws ServletException{
		try {
			req.setAttribute("exception", e);
			req.getRequestDispatcher("/WEB-INF/view/planner/error.jsp").forward(req, resp);		//예외 발생시 에러페이지로 이동
	
		}catch(Exception ex) {
			throw new ServletException(ex);
		}

	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// Method==GET
			String method = req.getMethod();	//요청 메소드를 확인
			if("GET".equals(method)) {			//메소드가 GET 이라면 실행
				System.out.println("[PC] GET /planner/delete..");
				
				// session에 저장된 UserDto의 userid를 req에 담아서 read.jsp에 보내기 위함
				HttpSession session = req.getSession();		// 로그인을 했을 시에 request 객체에서 session 값을 가져옴
				UserDto userDto = (UserDto)session.getAttribute("userDto");		//session 객체에 userDto를 할당
				int plannerid = Integer.parseInt(req.getParameter("plannerid"));	//삭제 요청을 받은 plannerid 값을 가져온 다음 정수형으로 바꿈
				if(userDto==null) {											//user정보가 있지 않다면 로그인이 되어있는 상태가 아니므로 
					req.setAttribute("message", "로그인이 필요한 기능입니다.");	//로그인 필요 메시지를 띄우고 다시 로그인 페이지로 넘어간다
					resp.sendRedirect(req.getContextPath()+"/user/login");	
					return ;
				}
				Map<String,Object> rvalue = plannerService.plannerSelect(plannerid); 	//plannerid 의 값을 받아옴
				PlannerDto plannerDto = (PlannerDto)rvalue.get("dto");		//비즈니스 로직에서 실행했을 때 받은 planner dto 값을 호출
				
				String userid = plannerDto.getUserid();	// planner 테이블의 userid 값을 가져온다.
				
				if(!userid.equals(userDto.getUserid())) {		//현재 로그인된 userid 와 테이블에 존재하는 userid 를 비교
					req.setAttribute("message", "해당 플래너의 작성자가 아닙니다.");
					resp.sendRedirect(req.getContextPath()+"/planner/list");	//일치하지 않으면 다시  목록으로 이동
					return ;
				}
				System.out.println("delete쪽 plannerid뭐임? : " +plannerid);
				rvalue = plannerService.plannerDelete(plannerid);		//플래너 삭제 비즈니스 로직 실행
				Boolean isDeleted = (Boolean)rvalue.get("isDeleted");	//실행여부 반환받음

				if(isDeleted!=null && isDeleted) {
					// 뷰로이동
					System.out.println("뷰로이동");
					resp.sendRedirect(req.getContextPath()+"/planner/list");	//플래너 삭제에 성공했다면 리스트로 리다이렉트
				} else {
					req.setAttribute("message", "플래너 삭제에 실패하였습니다.");
					req.getRequestDispatcher("/WEB-INF/view/planner/read?plannerid="+plannerid).forward(req, resp);
				}
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