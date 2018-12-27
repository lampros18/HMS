package gr.hua.dit.entity;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class User{
	
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
	public User() {}
	
	public User( int id, String name, String surname,String password ,String birthdate, String department, String phone,
			String address, String createdAt, String updatedAt, int enabled, String createdBy) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", password=" + password + ", birthdate="
				+ birthdate + ", department=" + department + ", phone=" + phone + ", address=" + address
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", enabled=" + enabled + ", createdBy="
				+ createdBy + "]";
	}


	
	
}
