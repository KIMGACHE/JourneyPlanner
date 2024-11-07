package Controller.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.PlannerDto;
import Domain.Common.Service.PlannerServiceImpl;

public class PlannerListController implements SubController{
	private PlannerServiceImpl plannerService;
	
	public PlannerListController() {
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
				System.out.println("[BC] GET /planner/list..");
				Map<String,Object> rvalue = plannerService.plannerList();
				req.setAttribute("list", rvalue.get("list"));
				req.getRequestDispatcher("/WEB-INF/view/planner/list.jsp").forward(req, resp);
				return ;
			}
			
//			// Method==POST
//			// 파라미터 받기
//			Integer areacode = Integer.parseInt(req.getParameter("areacode"));
//			Integer citycode = Integer.parseInt(req.getParameter("citycode"));
//			LocalDate startdate = LocalDate.parse(req.getParameter("startdate"),DateTimeFormatter.ISO_LOCAL_DATE);
//			LocalDate enddate = LocalDate.parse(req.getParameter("enddate"),DateTimeFormatter.ISO_LOCAL_DATE);
//			PlannerDto plannerDto = new PlannerDto(0,areacode,citycode,startdate,enddate);
//			System.out.println(plannerDto);
//			// 유효성검사
//			
//			// 서비스실행
//			Map<String,Object> rvalue = plannerService.plannerAdd(plannerDto);
//			Boolean isAdded = (Boolean)rvalue.get("isAdded");
//			if(isAdded!=null && isAdded) {
//				// 뷰로이동
//				System.out.println("뷰로이동");
//				resp.sendRedirect(req.getContextPath()+"/");
//			} else {
//				req.getRequestDispatcher("/WEB-INF/view/planner/add.jsp").forward(req, resp);
//			}
			
		} catch(Exception e) {
			try {
				ExceptionHandler(e, req, resp);
			} catch (ServletException e1) {
				try{  throw new ServletException(e1); }catch(Exception e2) {e2.printStackTrace();}
			}
		}
	}
}
