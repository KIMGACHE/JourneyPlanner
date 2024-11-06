package Controller.Book;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Dto.ReplyDto;
import Domain.Common.Service.BookServiceImpl;

public class BookReplyAddController implements SubController {
	
	private BookServiceImpl bookService;
	
	public BookReplyAddController() {
		
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
			
			
			
			//Method==POST-> 도서 등록처리
			
			// 파라미터 받기
			Long bookCode = Long.parseLong( req.getParameter("bookCode") );			
			String contents = req.getParameter("contents");
			
			ReplyDto replyDto = new ReplyDto(0,null,bookCode,contents,null);
			
			// 유효성 확인
			if(!isValid(replyDto)) {
				//
			}
			
			// 서비스 실행
		
			HttpSession session = req.getSession();
			bookService.replyAdd(replyDto, session);
			
			
			// 뷰로이동(내용전달 - ?)

			
			
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
