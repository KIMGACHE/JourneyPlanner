package Domain.Common.Dto;

public class BookDto {

	private long bookCode;
	private String bookName;
	private String publisher;
	private String isbn;
	//디폴트생성자
	//모든인자 생성자
	//getter and setter
	//toString 재정의
	public BookDto() {}
	public BookDto(long bookCode, String bookName, String publisher, String isbn) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.publisher = publisher;
		this.isbn = isbn;
	}
	public long getBookCode() {
		return bookCode;
	}
	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		return "BookDto [bookCode=" + bookCode + ", bookName=" + bookName + ", publisher=" + publisher + ", isbn="
				+ isbn + "]";
	};
	
	
	
}
