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
				HttpSession session = req.getSession();
				UserDto userDto = (UserDto)session.getAttribute("userDto");
				if(userDto==null) {
					req.setAttribute("message", "로그인이 필요한 기능입니다.");
					resp.sendRedirect(req.getContextPath()+"/user/login");
					return ;
				}
				Integer plannerid = Integer.parseInt(req.getParameter("plannerid"));
				System.out.println("planner ID : 제발->"+plannerid);
				Map<String,Object> rvalue = plannerService.plannerSelect(plannerid);
				PlannerDto plannerDto = (PlannerDto)rvalue.get("dto");
				String userid = (String)plannerDto.getUserid();
				if(!userid.equals(userDto.getUserid())) {
					req.setAttribute("message", "해당 플래너의 작성자가 아닙니다.");
					resp.sendRedirect(req.getContextPath()+"/user/read?plannerid=");
					return ;
				}
				
				req.setAttribute("plannerDto", plannerDto);
				req.getRequestDispatcher("/WEB-INF/view/planner/update.jsp").forward(req, resp);
				return ;
			}
			
			// Method==POST
			// 파라미터 받기
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto)session.getAttribute("userDto");
			if(userDto==null) {
				req.setAttribute("message", "로그인이 필요한 기능입니다.");
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
			Boolean isUpdated = (Boolean)rvalue.get("isUpdated");
			if(isUpdated!=null && isUpdated) {
				// 뷰로이동
				System.out.println("뷰로이동");
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
