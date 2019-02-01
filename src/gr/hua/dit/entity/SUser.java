package gr.hua.dit.entity;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class SUser{
	
	private String name;
	private String surname;
	private String birthdate;
	private String department;
	private String phone;
	private String address;
	private String createdAt;
	private String updatedAt;
	private String createdBy;
	public SUser() {}
	
	public SUser( String name, String surname ,String birthdate, String department, String phone,
			String address, String createdAt, String updatedAt, String createdBy) {
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.department = department;
		this.phone = phone;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
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


	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", birthdate=" + birthdate
				+ ", department=" + department + ", phone=" + phone + ", address=" + address + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + "]";
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;

	}

	public void setPhone(String phone) {
		this.phone = phone;
	}





	
	
}
