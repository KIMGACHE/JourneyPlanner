package Controller.Book;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.Criteria;
import Domain.Common.Dto.PageDto;
import Domain.Common.Service.BookServiceImpl;

public class BookListController implements SubController {
	
	private BookServiceImpl bookService;
	
	public BookListController() {
		
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
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
		}catch(Exception ex) {
			//throw new ServletExcesption(ex);
		}

	}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			//Method==GET -> 페이지 표시(Forwarding)
			String method = req.getMethod();
			System.out.println("[BC] GET /book/list..");
			
			//파라미터 ???? 처음 - 전체조회 , 키워드 조회 , 페이지번호
			String pageno = req.getParameter("pageno");
			String amount = req.getParameter("amount");
			String type = req.getParameter("type");
			String keyword = req.getParameter("keyword");
			Criteria criteria = null;
			System.out.println("pageno : " + pageno);
			
			if(pageno==null) {
				criteria = new Criteria(); // pageno=1 , amount=10, type=null, keyword=null	
			}else {
				criteria = new Criteria(pageno,10,type,keyword); // pageno=pageno , amount=10, type=type, keyword=keyword	
			}
				
			// 유효성 확인
//			if(!isValid(bookDto)) {
//				//
//			}
			
			//서비스
			Map<String,Object> rValue =  bookService.getBooks(criteria);
			List<BookDto> list = (List<BookDto>)rValue.get("list");
			PageDto pageDto = (PageDto)rValue.get("pageDto");
			
			
			//뷰
			req.setAttribute("list", list);
			req.setAttribute("pageDto", pageDto);
			req.getRequestDispatcher("/WEB-INF/view/book/list.jsp").forward(req, resp);
				
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
