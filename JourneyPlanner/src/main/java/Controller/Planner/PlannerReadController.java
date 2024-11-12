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

public class PlannerReadController implements SubController{
	private PlannerServiceImpl plannerService;
	
	public PlannerReadController() {
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
				System.out.println("[BC] GET /planner/read..");
				Integer plannerid = Integer.parseInt(req.getParameter("plannerid"));
				System.out.println("plannerid" + plannerid);
				Map<String,Object> rvalue = plannerService.plannerSelect(plannerid);
				PlannerDto plannerDto = (PlannerDto)rvalue.get("dto");
				req.setAttribute("plannerDto", plannerDto);
				req.getRequestDispatcher("/WEB-INF/view/planner/read.jsp").forward(req, resp);
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
