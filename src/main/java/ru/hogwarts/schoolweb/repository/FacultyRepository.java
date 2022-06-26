package ru.hogwarts.schoolweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolweb.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
