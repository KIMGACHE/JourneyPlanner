package Controller.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.BookDto;
import Domain.Common.Service.BookServiceImpl;

public class BookUpdateController implements SubController {

	private BookServiceImpl bookService;

	public BookUpdateController() {

		try {
			bookService = BookServiceImpl.getInstance();
		} catch (Exception e) {
			// ExceptionHandler 로 전달..
		}

	}

	// 예외처리함수
	public void ExceptionHandler(Exception e, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		try {
			req.setAttribute("exception", e);
			req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			String method = req.getMethod();
			if("GET".equals(method)) {
				//파라미터
				Long bookCode = Long.parseLong(req.getParameter("bookCode"));
				//유효성
				
				//서비스 실행
				BookDto bookDto =  bookService.getBook(bookCode);
				//뷰
				req.setAttribute("bookDto", bookDto);
				System.out.println("[BC] GET /book/update..");
				req.getRequestDispatcher("/WEB-INF/view/book/update.jsp").forward(req, resp);
			}
			
			//파라미터
			Long bookCode = Long.parseLong(req.getParameter("bookCode"));
			String bookname = req.getParameter("bookName");
			String publisher = req.getParameter("publisher");
			String isbn = req.getParameter("isbn");

			
			//유효성체크
			
			
			//서비스
			BookDto bookDto = new BookDto(bookCode,bookname,publisher,isbn);
			boolean isUpdate =  bookService.bookUpdate(bookDto);
			
			if(isUpdate) {
				resp.sendRedirect(req.getContextPath()+"/book/read?bookCode="+bookCode);	
				return ;
			}else {
				req.setAttribute("message", "Update...");
				req.getRequestDispatcher("/WEB-INF/view/book/update.jsp").forward(req, resp);
			}


		} catch (Exception e) {

			try {
				ExceptionHandler(e, req, resp);

			} catch (ServletException e1) {

				try {
					throw new ServletException(e1);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			System.out.println("[BC] Exception 발생.." + e);
		}

	}

	private boolean isValid(Object dto) {
		return true;
	}

}
