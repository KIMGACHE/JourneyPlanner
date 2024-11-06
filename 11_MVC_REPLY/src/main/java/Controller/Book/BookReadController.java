package Controller.Book;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.BookDto;
import Domain.Common.Service.BookServiceImpl;
import properties.UploadProperties;

public class BookReadController implements SubController {

	private BookServiceImpl bookService;

	public BookReadController() {

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
			//파라미터
			Long bookCode = Long.parseLong(req.getParameter("bookCode"));
			
			//유효성
			
			//서비스
			BookDto bookDto =  bookService.getBook(bookCode);
			
					
			//뷰
			req.setAttribute("bookDto", bookDto);
			
			String dirPath = UploadProperties.uploadPath
					+ File.separator
					+ UploadProperties.profilePath
					+ File.separator;
					
			req.setAttribute("profilePath", dirPath);
			System.out.println("[BC] GET /book/read..");
			req.getRequestDispatcher("/WEB-INF/view/book/read.jsp").forward(req, resp);
			
			
			
			return;

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
