package gr.hua.dit.entity;

import gr.hua.dit.entity.SUser;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
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
public class Student extends SUser{
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "year_of_enrollment")
	private int yearOfEnrollment;
	
	@Column(name = "is_postgraduate")
	private boolean isPostgraduate;

	public Student(){super();}
	

	
	
	

	public Student(String email,int id, String name, String surname, String password, String birthdate, String department,
			String phone, String address, String createdAt, String updatedAt, int enabled, String createdBy, boolean isPostgraduate, int yearOfEnrollment) {
		super(id, name, surname,  birthdate, department, phone, address, createdAt, updatedAt, createdBy);
		this.email = email;
		this.isPostgraduate = isPostgraduate;
		this.yearOfEnrollment = yearOfEnrollment;
	}






	public int getYearOfEnrollment() {
		return yearOfEnrollment;
	}

	public boolean isPostgraduate() {
		return isPostgraduate;
	}

	



}
