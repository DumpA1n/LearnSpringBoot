package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StudentService {
    private final HashOperations<String, String, Student> hashOperations;

    @Autowired
    public StudentService(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void addStudent(Student student) {
        hashOperations.put("Student", student.getId(), student);
    }

    public void deleteStudent(String id) {
        hashOperations.delete("Student", id);
    }

    public void updateStudent(Student student) {
        addStudent(student);
    }

    public Student getStudent(String id) {
        return hashOperations.get("Student", id);
    }

    public Map<String, Student> getAllStudents() {
        return hashOperations.entries("Student");
    }
}
