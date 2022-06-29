package ru.hogwarts.schoolweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolweb.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int min, int max);
}
