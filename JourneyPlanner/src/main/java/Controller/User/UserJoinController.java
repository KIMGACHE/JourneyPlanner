package Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.UserServiceImpl;

public class UserJoinController implements SubController{

	private UserServiceImpl userService;
	
	public UserJoinController() {
		this.userService = UserServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
	}
	
}
