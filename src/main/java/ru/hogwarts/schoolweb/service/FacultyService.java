package ru.hogwarts.schoolweb.service;

import ru.hogwarts.schoolweb.model.Faculty;
import ru.hogwarts.schoolweb.model.Student;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);
    Faculty getFacultyById(Long facultyId);
    Faculty updateFaculty(Faculty faculty);
    void deleteFaculty(Long facultyId);
    Collection<Faculty> findFacultiesByParameter(String parameter);
    Collection<Student> getStudentsByFacultyId(Long facultyId);
}
