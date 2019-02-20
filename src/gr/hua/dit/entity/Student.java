package gr.hua.dit.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.json.JSONObject;

@Entity(name="Student")
@Table(name = "student_profile")
@AttributeOverride(name = "name", column = @Column(name = "name"))
@AttributeOverride(name = "surname", column = @Column(name = "surname"))
@AttributeOverride(name = "birthdate", column = @Column(name = "birthdate"))
@AttributeOverride(name = "department", column = @Column(name = "department"))
@AttributeOverride(name = "phone", column = @Column(name = "phone"))
@AttributeOverride(name = "address", column = @Column(name = "address"))
@AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at"))
@AttributeOverride(name = "createdBy", column = @Column(name = "created_by"))
public class Student extends SUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "year_of_enrollment")
	private int yearOfEnrollment;

	@Column(name = "is_postgraduate")
	private boolean isPostgraduate;

	@OneToMany(
	        mappedBy = "student",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    @LazyCollection(LazyCollectionOption.FALSE)
	    private List<HousingApplication> housingApplications;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="username", referencedColumnName="username")
	private User user;
	
	public Student() {
		super();
	}
	/**
	 * Φτιάχνει έναν φοιτητή από ένα αντικείμενο JsonObject
	 * @param jsonObject 
	 */
	public Student(JSONObject jsonObject) {
		this(
					jsonObject.getString("name"),
					jsonObject.getString("surname"),					
					jsonObject.getString("birthDate"),
					jsonObject.getString("department"),
					jsonObject.getString("phone"),
					jsonObject.getString("address")
					,jsonObject.getString("createdAt"),
					jsonObject.getString("updatedAt"),
					jsonObject.getString("createdBy")
					,jsonObject.getBoolean("postGraduate"),
					jsonObject.getInt("yearOfEnrollment")
			   );
	}
	/**
	 * 
	 * @return Την αναπαράσταση του φοιτητή σε Json
	 */
	public JSONObject getJsonFromStudent() {
		
		JSONObject jsonStudent=new JSONObject();
		
		
		jsonStudent.put("email",this.getUser().getUsername());
		jsonStudent.put("name",this.getName());
		jsonStudent.put("surname", this.getSurname());
		jsonStudent.put("birthdate", this.getBirthdate());
		jsonStudent.put("department", this.getDepartment());
		jsonStudent.put("phone", this.getPhone());
		jsonStudent.put("address", this.getAddress());
		jsonStudent.put("createdAt", this.getCreatedAt());
		jsonStudent.put("updatedAt", this.getUpdatedAt());
		jsonStudent.put("yearOfEnrollment", this.getYearOfEnrollment());
		jsonStudent.put("postGraduate", this.isPostgraduate());
		jsonStudent.put("user", this.user.getUsername());
		
		
		return jsonStudent;
	}
	





	/**
	 * 
	 * @param jsonObject Το JsonObject που προκύπτει απο την εισαγωγή φοιτητή που
	 *                   κάνει ο Employee
	 * @return true αν οι τιμές που φτάνουν στο backend είναι αναμενόμενες με τους
	 *         ελέγχους που γίνονται στο front end false σε διαφορετική περίπτωση.
	 *         Πιθανό hacking detection
	 */
	public static boolean validStudent(JSONObject jsonObject) {

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

		
		if (!jsonObject.get("birthDate").toString().matches("^\\d{4}-\\d{2}-\\d{2}$")) {

			return false;
		}
		
		
		if (!jsonObject.get("yearOfEnrollment").toString().matches("^(\\d){4}$")) {

			return false;
		}

		
		// Άν έχει βάλει βλακείες ο χρήστης, θα σκάσει
		try {

			jsonObject.getBoolean("postGraduate");

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		
		if (!"Geography Home economics and ecology Informatics and telematics International master of sustainable tourism development Nutrition and dietics"
				.contains(jsonObject.get("department").toString())) {

			return false;
		}

		if (!jsonObject.get("phone").toString().matches("^(\\+){0,1}(\\d){10,15}$")) {

			return false;
		}

		if (!jsonObject.get("address").toString().matches("^(\\d|-|.){3,150}$")) {

			return false;
		}

		return true;
	}

	public Student(String name, String surname, String birthdate,
			String department, String phone, String address, String createdAt, String updatedAt,
			String createdBy, boolean isPostgraduate, int yearOfEnrollment) {
		super(name, surname, birthdate, department, phone, address, createdAt, updatedAt, createdBy);
		this.isPostgraduate = isPostgraduate;
		this.yearOfEnrollment = yearOfEnrollment;
	}

	public int getYearOfEnrollment() {
		return yearOfEnrollment;
	}

	public boolean isPostgraduate() {
		return isPostgraduate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setYearOfEnrollment(int yearOfEnrollment) {
		this.yearOfEnrollment = yearOfEnrollment;
	}

	public void setPostgraduate(boolean isPostgraduate) {
		this.isPostgraduate = isPostgraduate;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @param housingApplication 
	 * This method is used to insert housing applications. It also replaces applications of the same year.
	 */
	public void addHousingApplication(HousingApplication housingApplication) {	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		Date date;
		Calendar calendar = Calendar.getInstance();
		
		int curYear = calendar.get(Calendar.YEAR); 
		
		HousingApplication tmp = null;
		for(HousingApplication ha : this.housingApplications) {
			try {
				date = format.parse(ha.getCreated_at());
				calendar.setTime(date);
				if( calendar.get(Calendar.YEAR) == curYear ) {
					tmp = ha;
					break;
				}
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
		if(tmp != null)
			this.housingApplications.remove(tmp);
		this.housingApplications.add(housingApplication);
		housingApplication.setStudent(this);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Student other = (Student) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	public List<HousingApplication> getHousingApplications() {
		return housingApplications;
	}
	
	
	
}
