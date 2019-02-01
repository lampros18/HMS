package gr.hua.dit.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity(name="Employee")
@Table(name = "employee_profile")
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	

	
	@Column(name = "year_of_recruitment")
	private int yearOfRecruitment;
	
	@OneToOne
	@JoinColumn(name="username", referencedColumnName="username")
	private User user;
	
	public Employee() {
	}
	
	public Employee(JSONObject data) {
		super( data.getString("name"), data.getString("surname"), data.getString("birthdate"),
				data.getString("department"), data.getString("phone"), data.getString("address"), data.getString("createdAt"), data.getString("createdAt") , data.getString("createdBy"));
		this.yearOfRecruitment = Integer.parseInt(data.getString("yearOfRecruitment"));
	}


	public Employee(String email, int id, String name, String surname, String birthdate, int yearOfRecruitment,String department,
			String phone, String address, String createdAt, String updatedAt, String createdBy) {
		super(name, surname, birthdate, department, phone, address, createdAt, updatedAt, createdBy);
		this.yearOfRecruitment = yearOfRecruitment;
	}




	public int getYearOfRecruitment() {
		return yearOfRecruitment;
	}
	
	public JSONObject getJsonFromStudent() {
		
		JSONObject jsonStudent=new JSONObject();
		
		jsonStudent.put("name",this.getName());
		
		
		
		return null;
	}

	@Override
	public String toString() {
		return "Employee [ yearOfRecruitment=" + yearOfRecruitment
				+ ", getName()=" + getName() + ", getSurname()=" + getSurname() + ", getBirthdate()=" + getBirthdate()
				+ ", getDepartment()=" + getDepartment() + ", getPhone()=" + getPhone() + ", getAddress()="
				+ getAddress() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getCreatedBy()=" + getCreatedBy() + "]";
	}
	
}
