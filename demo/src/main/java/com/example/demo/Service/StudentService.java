package com.example.demo.Service;
import com.example.demo.Entity.Student;
import java.util.List;
public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudentById(Long id, Student student);
    void deleteStudentById(Long id);
    Student createStudent(Student student);
}
