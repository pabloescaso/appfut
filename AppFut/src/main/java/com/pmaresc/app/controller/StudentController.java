package com.dynamicUpdate.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/student/{studentId}")
    ResponseEntity<Student> getStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(service.findStudent(studentId));
    }

    @PostMapping("/student")
    ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.addStudent(student));
    }

    @PutMapping("/student")
    ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        if (student.getStudentId() == null) throw new RuntimeException("ID is a must");
        return ResponseEntity.ok(service.updateStudent(student));
    }

}
