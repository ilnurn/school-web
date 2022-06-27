package ru.hogwarts.schoolweb.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.schoolweb.model.Faculty;
import ru.hogwarts.schoolweb.model.Student;
import ru.hogwarts.schoolweb.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Такого студента нет"));
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Collection<Student> findStudentsWithAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentsWithAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Faculty findStudentsFacultyByName(String studentsName) {
        Student foundStudent = studentRepository.findStudentByName(studentsName);
        return foundStudent.getFaculty();
    }

    public Collection<Student> findStudentsByFacultyId(Long id) {
        return studentRepository.findByFaculty_Id(id);
    }
}
