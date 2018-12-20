package gr.hua.dit.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User{
//	private int id;
	private String email;
	private String name;
	private String surname;
	//password
	private String birthdate;
	private String department;
	private String phone;
	private String address;
	private String createdAt;
	private String updatedAt;
	
	public User() {}
	
	public User( String email, String name, String surname, String birthdate, String department, String phone,
			String address, String createdAt, String updatedAt) {
//		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.department = department;
		this.phone = phone;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

//	public int getId() {
//		return id;
//	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", surname=" + surname + ", birthdate=" + birthdate
				+ ", department=" + department + ", phone=" + phone + ", address=" + address + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

	
	
}
