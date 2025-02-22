package gr.hua.dit.service;

import java.util.List;
import javax.transaction.Transactional;
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
	@Transactional
	public List<Student> getStudents() { 
		return studentDAO.getStudents();
	}
	
	@Override
	@Transactional
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }
	
	@Override
	@Transactional
	public int insertStudent(Student student) {
		return studentDAO.insertStudent(student);
	}
	
	@Override
	@Transactional
	public Student findStudentByUsername(String username) {
		return studentDAO.findStudentByUsername(username);
	}

}
