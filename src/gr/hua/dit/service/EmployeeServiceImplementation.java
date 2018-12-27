package gr.hua.dit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.EmployeeDAO;
import gr.hua.dit.entity.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	
	@Override
	@Transactional
	public int createEmployee(Employee employee) {
		return employeeDAO.insertEmployee(employee);
	}

}
