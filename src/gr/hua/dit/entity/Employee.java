package gr.hua.dit.entity;

public class Employee extends User {
	private int yearOfRecruitment;
	private boolean isForeman;
	private boolean isAdmin;
	
	public Employee(int id, String email, String name, String surname, String birthdate, String department,
			String phone, String address, String createdAt, String updatedAt, int yearOfRecruitment, boolean isForeman,
			boolean isAdmin) {
		super( email, name, surname, birthdate, department, phone, address, createdAt, updatedAt);
		this.yearOfRecruitment = yearOfRecruitment;
		this.isForeman = isForeman;
		this.isAdmin = isAdmin;
	}

	public int getYearOfRecruitment() {
		return yearOfRecruitment;
	}

	public boolean isForeman() {
		return isForeman;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	
}
