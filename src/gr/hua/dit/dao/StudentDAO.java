package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.Student;

public interface StudentDAO {
	List<Student> getStudents();
	int addStudent(Student student);
	Student getStudentById(int id);
}
