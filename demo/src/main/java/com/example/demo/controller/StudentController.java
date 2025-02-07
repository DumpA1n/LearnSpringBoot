package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "success to add student " + student.getName();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudent(id);
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return "success to update student " + student.getName();
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return "success to delete student " + id;
    }

    @GetMapping
    public Map<String, Student> getAllStudents() {
        Map<String, Student> students = studentService.getAllStudents();
        return students;
    }
}
