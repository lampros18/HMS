package gr.hua.dit.service;

import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.Student;
import gr.hua.dit.entity.User;
public interface EmployeeService {
	int createEmployee(Employee employee);
	public void createStudent(Student student);
}
