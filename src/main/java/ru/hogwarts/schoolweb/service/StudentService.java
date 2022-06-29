package ru.hogwarts.schoolweb.service;

import ru.hogwarts.schoolweb.model.Faculty;
import ru.hogwarts.schoolweb.model.Student;

import java.util.Collection;

public interface StudentService {

    Student createStudent(Student student);
    Student getStudentById(Long studentId);
    Student updateStudent(Student student);
    void deleteStudent(Long studentId);
    Collection<Student> findStudentsWithAgeBetween(int min, int max);
    Faculty findStudentFacultyById(Long studentsId);
}
