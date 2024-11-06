package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import Domain.Common.Dao.BookDaoImpl;
import Domain.Common.Dao.ReplyDaoImpl;
import Domain.Common.Dao.UserDaoImpl;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.ReplyDto;
import Domain.Common.Dto.UserDto;

class DaoTests {

	@Test
	void bookDaoCRUDTests() throws Exception {
		
		BookDaoImpl dao = BookDaoImpl.getInstance();	
		
		dao.insert(new BookDto(112233,"도서도서","출판출판","12-12"));
	}
	
	@Test
	void bookDaoCRUDTests_2() throws Exception {
		
		BookDaoImpl dao = BookDaoImpl.getInstance();	
		List<BookDto> list = dao.select(40,10);
		list.forEach(System.out::println);
		
	}
	@Test
	void bookDaoCRUDTests_3() throws Exception {
		
		BookDaoImpl dao = BookDaoImpl.getInstance();	
		int cnt = dao.count();
		System.out.println("CNT : " + cnt);
		
	}
	
	@Test
	void bookDaoCRUDTests_4() throws Exception {
		
		BookDaoImpl dao = BookDaoImpl.getInstance();	
		List<BookDto> list = dao.select(0,10,"bookCode","11");
		list.forEach(System.out::println);
		
	}
	
	@Test
	void userDaoCRUDTests() throws Exception {
		
		UserDaoImpl dao = UserDaoImpl.getInstance();
		
		dao.insert(new UserDto("aaa","111","ROLE_USER",false));
		
	}
	
	@Test
	void replyDaoCRUDTests() throws Exception {
		ReplyDaoImpl dao = ReplyDaoImpl.getInstance();
		dao.insert(new ReplyDto(6,"efwef",30000002255346L,"TEST",null));
	}
	

}
