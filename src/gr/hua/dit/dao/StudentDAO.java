package gr.hua.dit.dao;

import java.util.List;

import gr.hua.dit.entity.Student;

public interface StudentDAO {
	List<Student> getStudents();
	Student getStudentById(int id);
	int insertStudent(Student student);
}
