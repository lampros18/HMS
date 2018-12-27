package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_authorities")
public class EmployeeAuthority {
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="authority")
	private String authority;

	public EmployeeAuthority(String email, String authority) {
		this.email = email;
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return "EmployeeAuthority [email=" + email + ", authority=" + authority + "]";
	}
	
	
	
}
