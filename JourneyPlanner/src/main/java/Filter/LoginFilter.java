package Filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Domain.Common.Dto.UserDto;

//@WebFilter("")
public class LoginFilter implements Filter {
	
	Map<String,Object> pageGradeMap = new HashMap();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리
		System.out.println("[Filter] PermissionFilter..Start");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		if(userDto==null) {
			System.out.println("권한이 없음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			resp.sendRedirect(req.getContextPath()+"/user/login?message=" + URLEncoder.encode("로그인이 필요한 서비스입니다","UTF-8"));
			return ;
		}
		chain.doFilter(request, response);
		
		// 후처리
		System.out.println("[Filter] PermissionFilter..End");
	}
	
}
