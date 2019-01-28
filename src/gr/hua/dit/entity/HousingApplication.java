package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "HousingApplication")
@Table(name = "housing_application")
public class HousingApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "personal_income")
	private double personalIncome;

	@Column(name = "unemployed_parents")
	private int unemployedParents;

	@Column(name = "family_income")
	private double familyIncome;

	@Column(name = "number_of_student_siblings")
	private int numberOfStudentSiblings;

	@Column(name = "is_local_resident")
	private int isLocalResident;

	@Column(name = "grade")
	private int grade;

	@Column(name = "verified")
	private int verified;

	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	private String updatedAt;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file")
	private byte[] file;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by_student", referencedColumnName = "username")
	private Student student;

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return this.student;
	}

	public HousingApplication() {
	}

	public HousingApplication(double personalIncome, int unemployedParents, double familyIncome,
			int numberOfStudentSiblings, int isLocalResident, int grade, String createdAt, String updatedAt,
			String fileType, byte[] file) {
		this.personalIncome = personalIncome;
		this.unemployedParents = unemployedParents;
		this.familyIncome = familyIncome;
		this.numberOfStudentSiblings = numberOfStudentSiblings;
		this.isLocalResident = isLocalResident;
		this.grade = grade;
		this.verified = 0;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.fileType = fileType;
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPersonalIncome() {
		return personalIncome;
	}

	public void setPersonalIncome(float personalIncome) {
		this.personalIncome = personalIncome;
	}

	public int getUnemployedParents() {
		return unemployedParents;
	}

	public void setUnemployedParents(int unemployedParents) {
		this.unemployedParents = unemployedParents;
	}

	public double getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}

	public int getNumberOfStudentSiblings() {
		return numberOfStudentSiblings;
	}

	public void setNumberOfStudentSiblings(int numberOfStudentSiblings) {
		this.numberOfStudentSiblings = numberOfStudentSiblings;
	}

	public int isLocalResident() {
		return isLocalResident;
	}

	public void setMainResidenceCity(int isLocalResident) {
		this.isLocalResident = isLocalResident;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public String getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdated_at(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
