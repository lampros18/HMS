package gr.hua.dit.dao;

import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.Student;

public interface EmployeeDAO {
	int insertEmployee(Employee employee);	
	void insertStudent(Student student);
}
