package Domain.Common.Dto;

import java.time.LocalDateTime;

public class ReplyDto {
	private int replyId;
	private String username;
	private long bookCode;
	private String contents;
	private LocalDateTime createAt;
	//toString
	//getter and setter
	//생성자(디폴트,모든인자)
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getBookCode() {
		return bookCode;
	}
	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	@Override
	public String toString() {
		return "ReplyDto [replyId=" + replyId + ", username=" + username + ", bookCode=" + bookCode + ", contents="
				+ contents + ", createAt=" + createAt + "]";
	}
	public ReplyDto(int replyId, String username, long bookCode, String contents, LocalDateTime createAt) {
		super();
		this.replyId = replyId;
		this.username = username;
		this.bookCode = bookCode;
		this.contents = contents;
		this.createAt = createAt;
	}
	public ReplyDto() {}
	
}
