package gr.hua.dit.entity;

import gr.hua.dit.entity.SUser;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

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
public class Student extends SUser {

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "year_of_enrollment")
	private int yearOfEnrollment;

	@Column(name = "is_postgraduate")
	private boolean isPostgraduate;

	public Student() {
		super();
	}

	public Student(String email, int id, String name, String surname, String password, String birthdate,
			String department, String phone, String address, String createdAt, String updatedAt, int enabled,
			String createdBy, boolean isPostgraduate, int yearOfEnrollment) {
		super(id, name, surname, birthdate, department, phone, address, createdAt, updatedAt, createdBy);
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

	// Validation Για όλα τα πεδία
	/**
	 * 
	 * @param jsonObject Το JsonObject που προκύπτει απο την εισαγωγή φοιτητή που κάνει ο Employee
	 * @return true αν οι τιμές που φτάνουν στο backend είναι αναμενόμενες με τους ελέγχους που γίνονται στο front end
	 * false σε διαφορετική περίπτωση. Πιθανό hacking detection
	 */
	public static boolean validStudent(JSONObject jsonObject ) {

	
		// Email
		if (!jsonObject.get("email").toString().matches(
				"^(([^<>()\\[\\]\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {

			return false;
		}

		// Name
	if (!jsonObject.get("name").toString().matches("^(.){3,20}$")) {

		return false;
		}

		// Επώνυμο
		if (!jsonObject.get("surname").toString().matches("^(.){3,20}$")) {

			return false;
		}
	

		if (!jsonObject.get("birthDate").toString().matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {

			return false;
		}
		
		if (!jsonObject.get("yearOfEnrollment").toString().matches("^(\\d){4}$")) {

			return false;
		}
		
		//Άν έχει βάλει βλακείες ο χρήστης, θα σκάσει
		try {
		
			jsonObject.getBoolean("postGraduate");
			
		}catch (Exception e) {
			
			return false;
		}
		
		if (!"Geography Home economics and ecology Informatics and telematics International master of sustainable tourism development Nutrition and dietics".contains(jsonObject.get("department").toString())) {

			return false;
		}
		
		if (!jsonObject.get("phone").toString().matches("^(\\+){0,1}(\\d){10,15}$")) {

			return false;
		}		
		
		if (!jsonObject.get("address").toString().matches("^(\\d|-|.){10,150}$")) {

			return false;
		}	
	
	
		
		

		return true;
	}

	@Override
	public String toString() {
		return "Student [email=" + email + ", yearOfEnrollment=" + yearOfEnrollment + ", isPostgraduate="
				+ isPostgraduate + ", getId()=" + getId() + ", getName()=" + getName() + ", getSurname()="
				+ getSurname() + ", getBirthdate()=" + getBirthdate() + ", getDepartment()=" + getDepartment()
				+ ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedAt()=" + getUpdatedAt() + ", getCreatedBy()=" + getCreatedBy() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}



}
