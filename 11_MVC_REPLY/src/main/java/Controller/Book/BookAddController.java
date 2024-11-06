package Controller.Book;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.BookDto;
import Domain.Common.Service.BookServiceImpl;

public class BookAddController implements SubController {
	
	private BookServiceImpl bookService;
	
	public BookAddController() {
		
		try {
			bookService = BookServiceImpl.getInstance();	
		} catch (Exception e) {
			//ExceptionHandler 로 전달..
		}
		
	}
	
	//예외처리함수
	public void ExceptionHandler(Exception e,HttpServletRequest req,HttpServletResponse resp) throws ServletException{
		try {
			req.setAttribute("exception", e);
			req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
		}catch(Exception ex) {
			throw new ServletException(ex);
		}

	}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			//Method==GET -> 페이지 표시(Forwarding)
			String method = req.getMethod();
			if("GET".equals(method)) {
				System.out.println("[BC] GET /book/add..");
				req.getRequestDispatcher("/WEB-INF/view/book/add.jsp").forward(req, resp);
				return ;
			}
			
			//Method==POST-> 도서 등록처리
			
			// 파라미터 받기
			Integer bookCode = Integer.parseInt( req.getParameter("bookCode") );
			String bookName = req.getParameter("bookName");
			String publisher = req.getParameter("publisher");
			String isbn = req.getParameter("isbn");
			BookDto bookDto = new BookDto(bookCode,bookName,publisher,isbn);
			System.out.println("bookDto : " + bookDto);

			// 유효성 확인
			if(!isValid(bookDto)) {
				//
			}
			
			// 서비스 실행
			Map<String,Object> returnVal=  bookService.bookRegistration(bookDto);
			
			boolean isAdded = (boolean)returnVal.get("success");
			String message = (String)returnVal.get("message");
			
			
			// 뷰로이동(내용전달 - ?)
			if(isAdded) {
				resp.sendRedirect(req.getContextPath()+ "/?message="+URLEncoder.encode(message,"UTF-8"));
				return ;
			}else {
				req.getRequestDispatcher("/WEB-INF/view/book/add.jsp").forward(req, resp);
				return ;
			}
			
			
			
		}catch(Exception e) {
			
				try {
					ExceptionHandler(e,req,resp);
				
				} catch (ServletException e1) {
				
					 try{  throw new ServletException(e1); }catch(Exception e2) {e2.printStackTrace();}
				}

			System.out.println("[BC] Exception 발생.." + e);
		}
		
		
	}
	
	private boolean isValid(Object dto) {
		return true;
	}
	
	

}
