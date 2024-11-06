package Domain.Common.Dto;

public class UserDto {
	private String userid;
	private String password;
	private String role;
	private int age;
	private String gender;
	
	public UserDto() {}

	public UserDto(String userid, String password, String role, int age, String gender) {
		super();
		this.userid = userid;
		this.password = password;
		this.role = role;
		this.age = age;
		this.gender = gender;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", password=" + password + ", role=" + role + ", age=" + age + ", gender="
				+ gender + "]";
	}
}
