package ru.hogwarts.schoolweb.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolweb.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private Long generatedStudentId = 1L;

    public Student createStudent(Student student) {
        student.setId(generatedStudentId);
        students.put(generatedStudentId, student);
        generatedStudentId++;
        return student;
    }

    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        students.put(studentId, student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
        return students.remove(studentId);
    }

    public Collection<Student> findStudentsWithAge(int age) {
        Collection<Student> studentsWithAge = students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
        return studentsWithAge;
    }
}
