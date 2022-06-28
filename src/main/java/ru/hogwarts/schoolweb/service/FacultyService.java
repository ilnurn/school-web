package ru.hogwarts.schoolweb.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.schoolweb.model.Faculty;
import ru.hogwarts.schoolweb.model.Student;
import ru.hogwarts.schoolweb.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(() -> new NotFoundException("Такого факультета нет"));
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> findFacultiesByParameter(String parameter) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(parameter, parameter);
    }

//    public Collection<Student> getStudentsByFacultyId(Long facultyId){
//        return facultyRepository.getStudents();
//    }
}
