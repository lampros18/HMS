package gr.hua.dit.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity(name="Employee")
@Table(name = "employee_profile")

@AttributeOverride(name = "id", column = @Column(name = "id"))
@AttributeOverride(name = "name", column = @Column(name = "name"))
@AttributeOverride(name = "surname", column = @Column(name = "surname"))
@AttributeOverride(name = "birthdate", column = @Column(name = "birthdate"))
@AttributeOverride(name = "department", column = @Column(name = "department"))
@AttributeOverride(name = "phone", column = @Column(name = "phone"))
@AttributeOverride(name = "address", column = @Column(name = "address"))
@AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
@AttributeOverride(name = "createdBy", column = @Column(name = "created_by"))
public class Employee extends SUser {
	
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "year_of_recruitment")
	private int yearOfRecruitment;


	
	
	public Employee() {
	}
	
	public Employee(JSONObject data) {
		super(Integer.parseInt(data.getString("id")), data.getString("name"), data.getString("surname"), data.getString("birthdate"),
				data.getString("department"), data.getString("phone"), data.getString("address"), data.getString("createdAt"), data.getString("createdAt") , data.getString("createdBy"));
		this.email = data.getString("email");
		this.yearOfRecruitment = Integer.parseInt(data.getString("yearOfRecruitment"));
	}


	public Employee(String email, int id, String name, String surname, String birthdate, int yearOfRecruitment,String department,
			String phone, String address, String createdAt, String updatedAt, String createdBy) {
		super(id, name, surname, birthdate, department, phone, address, createdAt, updatedAt, createdBy);
		this.email = email;
		this.yearOfRecruitment = yearOfRecruitment;
	}


	public String getEmail() {
		return email;
	}


	public int getYearOfRecruitment() {
		return yearOfRecruitment;
	}

	@Override
	public String toString() {
		return "Employee [email=" + email + ", yearOfRecruitment=" + yearOfRecruitment + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getBirthdate()=" + getBirthdate()
				+ ", getDepartment()=" + getDepartment() + ", getPhone()=" + getPhone() + ", getAddress()="
				+ getAddress() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getCreatedBy()=" + getCreatedBy() + "]";
	}
	



	
	
}
