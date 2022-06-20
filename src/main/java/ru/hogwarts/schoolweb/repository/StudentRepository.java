package ru.hogwarts.schoolweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolweb.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
