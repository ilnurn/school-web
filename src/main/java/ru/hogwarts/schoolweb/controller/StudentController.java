package ru.hogwarts.schoolweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolweb.model.Faculty;
import ru.hogwarts.schoolweb.model.Student;
import ru.hogwarts.schoolweb.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age")
    public ResponseEntity<Collection<Student>> findByAge(@RequestParam int min, @RequestParam int max) {
        return ResponseEntity.ok(studentService.findStudentsWithAgeBetween(min,max));
    }

    @GetMapping("/faculty")
    public ResponseEntity<Faculty> findStudentsFaculty(@RequestParam String name) {
        return ResponseEntity.ok(studentService.findStudentsFacultyByName(name));
    }

    @GetMapping("/students")
    public ResponseEntity<Collection<Student>> findStudentsByFacultyId(@RequestParam Long id) {
        return ResponseEntity.ok(studentService.findStudentsByFacultyId(id));
    }
}
