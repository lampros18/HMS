package gr.hua.dit.entity;

import gr.hua.dit.entity.User;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Student")
//@AttributeOverride(name = "id", column = @Column(name = "id"))
@AttributeOverride(name = "email", column = @Column(name = "email"))
@AttributeOverride(name = "name", column = @Column(name = "name"))
@AttributeOverride(name = "surname", column = @Column(name = "surname"))
@AttributeOverride(name = "birthdate", column = @Column(name = "birthdate"))
@AttributeOverride(name = "department", column = @Column(name = "department"))
@AttributeOverride(name = "phone", column = @Column(name = "phone"))
@AttributeOverride(name = "address", column = @Column(name = "address"))
@AttributeOverride(name = "createdAt", column = @Column(name = "createdAt"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updatedAt"))
public class Student extends User{
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "year_of_enrollment")
	private int yearOfEnrollment;
	
	@Column(name = "isPostgraduate")
	private boolean isPostgraduate;
	
//	@Column(name = "employeeId")
//	private int employeeId;
	
	public Student(){super();}
	
	public Student(int id, String email, String name, String surname, String birthdate, String department, String phone,
			String address, String createdAt, String updatedAt, int yearOfEnrollment, boolean isPostgraduate
			) {
		super( email, name, surname, birthdate, department, phone, address, createdAt, updatedAt);
		this.yearOfEnrollment = yearOfEnrollment;
		this.isPostgraduate = isPostgraduate;
//		this.employeeId = employeeId;
		this.id = id;
	}
	
	
	
//	@Override
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	
	public int getYearOfEnrollment() {
		return yearOfEnrollment;
	}

	public boolean isPostgraduate() {
		return isPostgraduate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", yearOfEnrollment=" + yearOfEnrollment + ", isPostgraduate=" + isPostgraduate
				+ ", getEmail()=" + getEmail() + ", getName()=" + getName() + ", getSurname()=" + getSurname()
				+ ", getBirthdate()=" + getBirthdate() + ", getDepartment()=" + getDepartment() + ", getPhone()="
				+ getPhone() + ", getAddress()=" + getAddress() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + "]";
	}
	

//	public int getEmployeeId() {
//		return employeeId;
//	}


}
