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

public class PlannerUpdateController implements SubController{
	private PlannerServiceImpl plannerService;
	
	public PlannerUpdateController() {
		this.plannerService = PlannerServiceImpl.getInstance();
	}
	
	//예외처리함수
	public void ExceptionHandler(Exception e,HttpServletRequest req,HttpServletResponse resp) throws ServletException{
		try {
			req.setAttribute("exception", e);
			req.getRequestDispatcher("/WEB-INF/view/planner/error.jsp").forward(req, resp);
	
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
				System.out.println("[BC] GET /planner/update..");
				HttpSession session = req.getSession();		//세션 정보 받아옴
				UserDto userDto = (UserDto)session.getAttribute("userDto");	//session에 있는 userDto 정보 받아옴
				if(userDto==null) {
					req.setAttribute("message", "로그인이 필요한 기능입니다.");	//userDto가 존재하지 않다면 로그인 필요 메시지 띄움
					resp.sendRedirect(req.getContextPath()+"/user/login"); //다시 로그인창으로 리다이렉트
					return ;
				}
				Integer plannerid = Integer.parseInt(req.getParameter("plannerid"));	//plannerid 파싱
				System.out.println("planner ID : 제발->"+plannerid);		
				Map<String,Object> rvalue = plannerService.plannerSelect(plannerid);	//특정 plannerid dto 가져옴	
				PlannerDto plannerDto = (PlannerDto)rvalue.get("dto");			//plannerDto 의 가져옴
				String userid = (String)plannerDto.getUserid();		//특정 plannerDto 의 userId 가져옴
				if(!userid.equals(userDto.getUserid())) {		//세션에 있는 로그인된 userId 와 특정 plannerDto 의 userId가 같지 않다면
					req.setAttribute("message", "해당 플래너의 작성자가 아닙니다.");
					resp.sendRedirect(req.getContextPath()+"/user/read?plannerid=");	
					return ;
				}
				
				req.setAttribute("plannerDto", plannerDto);
				req.getRequestDispatcher("/WEB-INF/view/planner/update.jsp").forward(req, resp);	//작성자가 맞을시 update페이지로 이동
				return ;
			}
			
			// Method==POST
			// 파라미터 받기
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto)session.getAttribute("userDto");		//POST 일 시에  session 에서 userDto 받아옴
			if(userDto==null) {
				req.setAttribute("message", "로그인이 필요한 기능입니다.");	//session 에 정보가 없다면 로그인 페이지로 이동
				resp.sendRedirect(req.getContextPath()+"/user/login");
				return ;
			}
			String userid = userDto.getUserid();
			Integer plannerid = Integer.parseInt(req.getParameter("plannerid"));
			Integer areacode = Integer.parseInt(req.getParameter("areacode"));
			Integer citycode = Integer.parseInt(req.getParameter("citycode"));
			LocalDate startdate = LocalDate.parse(req.getParameter("startdate"),DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDate enddate = LocalDate.parse(req.getParameter("enddate"),DateTimeFormatter.ISO_LOCAL_DATE);
			PlannerDto plannerDto = new PlannerDto(plannerid,areacode,citycode,startdate,enddate,userid);
			// 유효성검사
			
			// 서비스실행
			Map<String,Object> rvalue = plannerService.plannerUpdate(plannerDto);	
			Boolean isUpdated = (Boolean)rvalue.get("isUpdated");	//비즈니스 로직 실행
			if(isUpdated!=null && isUpdated) {	//업데이트가 실행되었을 시에는
				// 뷰로이동
				System.out.println("뷰로이동");	//다시 정보창으로 리다이렉트
				resp.sendRedirect(req.getContextPath()+"/planner/read?plannerid="+plannerid); // 나중에는 유저정보->본인의 플래너 ->해당플래너->수정->해당플래너 이런식
			} else {
				req.getRequestDispatcher("/WEB-INF/view/planner/update.jsp").forward(req, resp);
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
