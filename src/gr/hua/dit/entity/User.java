package gr.hua.dit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	        mappedBy = "user",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
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
		return username;
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

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
//				+ ", getAuthorities()=" + getAuthorities() + "]";
//	}

	


	
	
	
}
