package gr.hua.dit.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@AttributeOverride(name = "id", column = @Column(name = "id"))
@AttributeOverride(name = "email", column = @Column(name = "email"))
@AttributeOverride(name = "name", column = @Column(name = "name"))
@AttributeOverride(name = "surname", column = @Column(name = "surname"))
@AttributeOverride(name = "password", column = @Column(name = "password"))
@AttributeOverride(name = "birthdate", column = @Column(name = "birthdate"))
@AttributeOverride(name = "department", column = @Column(name = "department"))
@AttributeOverride(name = "phone", column = @Column(name = "phone"))
@AttributeOverride(name = "address", column = @Column(name = "address"))
@AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
@AttributeOverride(name = "enabled", column = @Column(name = "enabled"))
@AttributeOverride(name = "createdBy", column = @Column(name = "created_by"))
public class Employee extends User {
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "year_of_recruitment")
	private int yearOfRecruitment;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="email")
	private List<EmployeeAuthority> authorities;
	
	public Employee() {
		super();
	}
	
	public Employee(Map<String, String> data) {
		super(Integer.parseInt(data.get("id")), data.get("name"), data.get("surname"), data.get("password"), data.get("birthdate"),
				data.get("department"), data.get("phone"), data.get("address"), "2018-1-1 10:10:10", "2018-1-1 10:10:10", Integer.parseInt(data.get("enabled")), data.get("createdBy"));
		this.email = data.get("email");
		this.yearOfRecruitment = Integer.parseInt(data.get("yearOfRecruitment"));
	}


	public Employee(String email, int id, String name, String surname, String password, String birthdate, int yearOfRecruitment,String department,
			String phone, String address, String createdAt, String updatedAt, int enabled, String createdBy) {
		super(id, name, surname, password, birthdate, department, phone, address, createdAt, updatedAt, enabled, createdBy);
		this.email = email;
		this.yearOfRecruitment = yearOfRecruitment;
	}


	public String getEmail() {
		return email;
	}


	public int getYearOfRecruitment() {
		return yearOfRecruitment;
	}
	
	   // add a convenience method
	   public void addAuthority(EmployeeAuthority employeeAuthority) {
	           if (authorities == null) {
	                   authorities = new ArrayList<>();
	           }
	           authorities.add(employeeAuthority);
	   }

	public List<EmployeeAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String toString() {
		return "Employee [email=" + email + ", yearOfRecruitment=" + yearOfRecruitment + ", authorities=" + authorities
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getSurname()=" + getSurname()
				+ ", getBirthdate()=" + getBirthdate() + ", getDepartment()=" + getDepartment() + ", getPhone()="
				+ getPhone() + ", getAddress()=" + getAddress() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + ", getEnabled()=" + getEnabled() + ", getCreatedBy()="
				+ getCreatedBy() + "]";
	}

	
	
}
