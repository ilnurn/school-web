package ru.hogwarts.schoolweb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SchoolWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolWebApplication.class, args);
    }

}
