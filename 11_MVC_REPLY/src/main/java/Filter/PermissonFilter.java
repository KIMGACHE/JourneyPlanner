package Filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Type.ROLE;

public class PermissonFilter implements Filter {

	Map<String, ROLE> pageGradeMap = new HashMap(); // URL : Permession value
														// /user.do : 0 , /member.do : 1 , /admin.do : 2

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String projectPath = filterConfig.getServletContext().getContextPath();
		
		pageGradeMap.put(projectPath + "/book/add", ROLE.ROLE_MEMBER);	//1
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("[filter] perperp start");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		String myRole = (String)session.getAttribute("role");
		if(myRole==null) {
			resp.sendRedirect(req.getContextPath()+"/login?message=" + URLEncoder.encode("로그인이 필요한 서비스입니다","UTF-8"));
			return ;
		}

		// role 값이 존재하는 경우
		String url = req.getRequestURI(); // user.do , member.do , admin.do , etc ,,what's eta
		ROLE pageRole = pageGradeMap.get(url);

		System.out.println("URL : " + url + "" + pageRole + " " + myRole);

		ROLE myRoleVal = null;
		if("ROLE_ADMIN".equals(myRole)) {
			myRoleVal = ROLE.ROLE_ADMIN;
		}else if("ROLE_MEMBER".equals(myRole)) {
			myRoleVal = ROLE.ROLE_MEMBER;
		}else if("ROLE_USER".equals(myRole)){
			myRoleVal = ROLE.ROLE_USER;
		}else {
			;
		}
		
		// pageRole<=myRole = 허용
		if (pageRole.ordinal() > myRoleVal.ordinal()) { // 내 롤이 페이지 롤보다 작을 경우에는 권한이 부족 , 따라서 예외페이지로 이동
			throw new ServletException("해당 권한은 접근이 불가능한 페이지");
		}

		chain.doFilter(request, response); // 이렇게 체인을 하면 이어져서 밑에꺼가 실행되는듯 ?

		System.out.println("chain  end ... ");
	}

}
