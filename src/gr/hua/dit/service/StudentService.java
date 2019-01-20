package gr.hua.dit.service;

import java.util.List;

import gr.hua.dit.entity.Student;

public interface StudentService {
	List<Student> getStudents();

	Student getStudentById(int id);
	
}
