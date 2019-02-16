package gr.hua.dit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity(name="User")
@Table(name="user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	//Hibernate uses the id to map the foreign key
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private String enabled;
	
	@OneToMany(
			fetch = FetchType.EAGER,
	        mappedBy = "user",
	        cascade = CascadeType.ALL,
	        orphanRemoval = false
	    )
	    private List<Authorities> authorities;
	

	
	
	public User() {}
	
	public User(String username, String password, String enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = new ArrayList<>();
	}
	
	public User(JSONObject json) {
		this.username = json.getString("email");
		this.password = json.getString("password");
		this.enabled = json.getString("enabled");
		this.authorities = new ArrayList<>();
	}

	public String getUsername() {
		return this.username;
	}

	
	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getEnabled() {
		return enabled;
	}

	public List<Authorities> getAuthorities() {
		return authorities;
	}
	
	public void addAuthority(Authorities authority) {
		authorities.add(authority);
		authority.setUser(this);
	}
	
	public void clearAuthorities() {
		authorities.clear();
	}

	public boolean isStudent() {
		for(Authorities authority : this.authorities) {
			if(authority.getAuthority().equals("ROLE_STUDENT"))
				return true;
		}
		return false;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", authorities=" + authorities + "]";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	
}
