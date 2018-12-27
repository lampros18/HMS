package gr.hua.dit.entity;


public class EmployeeJson {
	
	private String email;
	private int yearOfRecruitment;
	private int id;
	private String name;
	private String surname;
	private String password;
	private String birthdate;
	private String department;
	private String phone;
	private String address;
	private String createdAt;
	private String updatedAt;
	private int enabled;
	private String createdBy;
	private String authority;
	
	public EmployeeJson(String email, int yearOfRecruitment, int id, String name, String surname, String password,
			String birthdate, String department, String phone, String address, String createdAt, String updatedAt,
			int enabled, String createdBy, String authority) {
		this.email = email;
		this.yearOfRecruitment = yearOfRecruitment;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthdate = birthdate;
		this.department = department;
		this.phone = phone;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public int getYearOfRecruitment() {
		return yearOfRecruitment;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassword() {
		return password;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getDepartment() {
		return department;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public int getEnabled() {
		return enabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return "EmployeeJson [email=" + email + ", yearOfRecruitment=" + yearOfRecruitment + ", id=" + id + ", name="
				+ name + ", surname=" + surname + ", password=" + password + ", birthdate=" + birthdate
				+ ", department=" + department + ", phone=" + phone + ", address=" + address + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", enabled=" + enabled + ", createdBy=" + createdBy
				+ ", authority=" + authority + "]";
	}
	
	
}
