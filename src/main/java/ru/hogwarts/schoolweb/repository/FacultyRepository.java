package ru.hogwarts.schoolweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolweb.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);
}
