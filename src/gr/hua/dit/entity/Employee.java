package gr.hua.dit.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity(name="Employee")
@Table(name = "employee")
@AttributeOverride(name = "id", column = @Column(name = "id"))
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "hid")
	private int hid;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "year_of_recruitment")
	private int yearOfRecruitment;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="employee_email")
	private List<EmployeeAuthority> authorities;
	
	public Employee() {
		super();
	}
	
	public Employee(JSONObject data) {
		super(Integer.parseInt(data.getString("id")), data.getString("name"), data.getString("surname"), data.getString("password"), data.getString("birthdate"),
				data.getString("department"), data.getString("phone"), data.getString("address"), "2018-1-1 10:10:10", "2018-1-1 10:10:10", Integer.parseInt(data.getString("enabled")), data.getString("createdBy"));
		this.email = data.getString("email");
		System.out.println("debug" +data.getString("email") );
		this.yearOfRecruitment = Integer.parseInt(data.getString("yearOfRecruitment"));
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
