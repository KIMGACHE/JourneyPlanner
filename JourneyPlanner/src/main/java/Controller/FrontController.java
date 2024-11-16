package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Planner.PlannerAddController;
import Controller.Planner.PlannerDeleteController;
import Controller.Planner.PlannerListController;
import Controller.Planner.PlannerReadController;
import Controller.Planner.PlannerUpdateController;
import Controller.User.UserJoinController;
import Controller.User.UserLoginController;
import Controller.User.UserLogoutController;
import Controller.User.UserMyInfoController;


public class FrontController extends HttpServlet {

	private Map<String, SubController> map = new HashMap();

	public FrontController() {
		System.out.println("[FC] FrontController()....");

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("[FC] init()....");

		String path = config.getServletContext().getContextPath();
		// HOME
		map.put(path + "/", new HomeController());

		// User
		map.put(path + "/user/join", new UserJoinController());
		map.put(path + "/user/login",new UserLoginController());
		map.put(path + "/user/logout",new UserLogoutController());
		map.put(path + "/user/myinfo", new UserMyInfoController());
		
		// Planner
		map.put(path + "/planner/add", new PlannerAddController());
		map.put(path + "/planner/update", new PlannerUpdateController());
		map.put(path + "/planner/list", new PlannerListController());
		map.put(path + "/planner/read", new PlannerReadController());
		map.put(path + "/planner/delete", new PlannerDeleteController());

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// endPoint
		System.out.println("[FC] execute()....");
		String endPoint = req.getRequestURI(); // 사용자의 요청EP를 확인(/book,/user)
		System.out.println("END POINT :" + endPoint);
		SubController controller = map.get(endPoint); // 요청사항을 처리할 SubController get
		controller.execute(req, resp);
	}

}