package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.User.UserJoinController;


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

		
//		  map.put(path+"/book/add", new BookAddController());
//		  map.put(path+"/book/list", new BookListController());
//		  map.put(path+"/book/read", new BookReadController());
//		  map.put(path+"/book/update", new BookUpdateController());
//		  map.put(path+"/book/delete", new BookDeleteController());
//		  
//		  map.put(path+"/book/reply/add", new BookReplyAddController());
//		  map.put(path+"/book/reply/list", new BookReplyListController());
//		  
//		  //AUTH + USER map.put(path+"/join", new UserJoinController());
//		  map.put(path+"/login", new UserLoginController()); map.put(path+"/logout",
//		  new UserLogoutController()); map.put(path+"/user/myinfo", new
//		  UserMyinfoController()); map.put(path+"/user/profile/upload", new
//		  UserProfileUploadController()); map.put(path+"/profile/image", new
//		  UserProfilePathController());
		 

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