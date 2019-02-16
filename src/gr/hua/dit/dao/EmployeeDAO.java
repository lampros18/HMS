package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.Employee;

public interface EmployeeDAO {
	int insertEmployee(Employee employee);

	List<Employee> getAllEmployees();

}
