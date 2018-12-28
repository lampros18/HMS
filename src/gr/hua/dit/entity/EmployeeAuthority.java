package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="EmployeeAuthority")
@Table(name="employee_authorities")
public class EmployeeAuthority {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="authority")
	private String authority;

	
	public EmployeeAuthority() {
		
	}
	
	public EmployeeAuthority(String email, String authority) {
		this.authority = authority;
	}

//	public String getEmail() {
//		return email;
//	}

	public String getAuthority() {
		return authority;
	}

//	@Override
//	public String toString() {
//		return "EmployeeAuthority [email=" + email + ", authority=" + authority + "]";
//	}
	
	
	
}
