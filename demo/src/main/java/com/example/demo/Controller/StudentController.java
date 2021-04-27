package com.example.demo.Controller;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudentById(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
}
