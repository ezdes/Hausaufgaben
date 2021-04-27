package com.example.demo.Service;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        return studentRepository.findById(id)
                .map(newStudent -> {
                    newStudent.setName(student.getName());
                    newStudent.setAge(student.getAge());
                    newStudent.setEmail(student.getEmail());
                    newStudent.setGender(student.getGender());
                    newStudent.setPhoneNumber(student.getPhoneNumber());
                    return studentRepository.save(newStudent);
                }).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
}
