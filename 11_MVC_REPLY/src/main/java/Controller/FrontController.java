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

import Controller.Book.BookAddController;
import Controller.Book.BookDeleteController;
import Controller.Book.BookListController;
import Controller.Book.BookReadController;
import Controller.Book.BookReplyAddController;
import Controller.Book.BookReplyListController;
import Controller.Book.BookUpdateController;
import Controller.User.UserJoinController;
import Controller.User.UserLoginController;
import Controller.User.UserLogoutController;
import Controller.User.UserMyinfoController;
import Controller.User.UserProfilePathController;
import Controller.User.UserProfileUploadController;


// 		/*	:	모든 요청 처리(모든경로(정적자원,/WEB-INF..)) 
// 		/	:	모든 요청 처리(프로젝트경로기준, 여기는 /09_MVC_INIT 을기준으로하는 요청만 처리)
//				정적 자원 요청은 x , /WEB-INF/ 경로도 x

@MultipartConfig
(
	fileSizeThreshold=1024*1024*10,	// 10MB 업로드 파일이 10Mb이상이 될때 임시경로(temp)에 저장
	maxFileSize=1024*1024*50,		// 50MB  업로드 파일의 최대 크기 
	maxRequestSize=1024*1024*100	// 100MB request전체의 크기	
)
public class FrontController extends HttpServlet {
	
	private Map<String,SubController> map = new HashMap();
	
	public FrontController(){
		System.out.println("[FC] FrontController()....");
	
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("[FC] init()....");
		
		String path = config.getServletContext().getContextPath();
		//HOME
		map.put(path+"/", new HomeController());	
		
		//BOOK
		map.put(path+"/book/add", new BookAddController());
		map.put(path+"/book/list", new BookListController());
		map.put(path+"/book/read", new BookReadController());
		map.put(path+"/book/update", new BookUpdateController());
		
		map.put(path+"/book/delete", new BookDeleteController());

		map.put(path+"/book/getAll", null);
		
		//AUTH + USER
		map.put(path+"/join", new UserJoinController());
		map.put(path+"/login", new UserLoginController());
		map.put(path+"/logout", new UserLogoutController());
		map.put(path+"/user/myinfo", new UserMyinfoController());
		map.put(path+"/user/profile/upload", new UserProfileUploadController());
		map.put(path+"/profile/image", new UserProfilePathController());
		
		
		map.put(path+"/book/reply/add", new BookReplyAddController());
		map.put(path+"/book/reply/list", new BookReplyListController());
		
	}

	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//endPoint
		System.out.println("[FC] execute()....");
		String endPoint =req.getRequestURI(); //사용자의 요청EP를 확인(/book,/user)
		System.out.println("END POINT :" + endPoint);
		SubController controller =  map.get(endPoint);	//요청사항을 처리할 SubController get
		controller.execute(req,resp);	
	}


	
}



