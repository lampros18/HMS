package gr.hua.dit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.dao.StudentDAO;
import gr.hua.dit.entity.Student;

@Service
public class StudentServiceImplementation implements StudentService {
	
	//Dependency injection
	@Autowired
	StudentDAO studentDAO;
	
	//Service provider method
	@Override
	public List<Student> getStudents() { 
		return studentDAO.getStudents();
	}

}
