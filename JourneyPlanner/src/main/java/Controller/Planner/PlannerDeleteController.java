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
				System.out.println("[PC] GET /planner/delete..");
				
				// session에 저장된 UserDto의 userid를 req에 담아서 read.jsp에 보내기 위함
				HttpSession session = req.getSession();
				UserDto userDto = (UserDto)session.getAttribute("userDto");
				int plannerid = Integer.parseInt(req.getParameter("plannerid"));
				if(userDto==null) {
					req.setAttribute("message", "로그인이 필요한 기능입니다.");
					resp.sendRedirect(req.getContextPath()+"/user/login");
					return ;
				}
				Map<String,Object> rvalue = plannerService.plannerSelect(plannerid);
				PlannerDto plannerDto = (PlannerDto)rvalue.get("dto");
				String userid = plannerDto.getUserid();
				
				if(!userid.equals(userDto.getUserid())) {
					req.setAttribute("message", "해당 플래너의 작성자가 아닙니다.");
					resp.sendRedirect(req.getContextPath()+"/user/list");
					return ;
				}
				System.out.println("delete쪽 plannerid뭐임? : " +plannerid);
				rvalue = plannerService.plannerDelete(plannerid);
				Boolean isDeleted = (Boolean)rvalue.get("isDeleted");

				if(isDeleted!=null && isDeleted) {
					// 뷰로이동
					System.out.println("뷰로이동");
					resp.sendRedirect(req.getContextPath()+"/planner/list");
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
