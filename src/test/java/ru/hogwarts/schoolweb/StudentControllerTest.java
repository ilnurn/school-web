package ru.hogwarts.schoolweb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.schoolweb.model.Student;
import ru.hogwarts.schoolweb.repository.StudentRepository;
import ru.hogwarts.schoolweb.service.StudentServiceImpl;
import ru.hogwarts.schoolweb.controller.StudentController;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private StudentServiceImpl studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp(){
        Student student = new Student();
        student.setAge(222);
        student.setName("Dracula");
        student.setId(333L);
        studentService.createStudent(student);
        when(studentRepository.findById(333L)).thenReturn(Optional.of(student));
    }

    @AfterEach
    void tearUp() {studentService.deleteStudent(333l);}

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testGetStudent() throws JSONException {
        String expected = "{id:333,name:\"Dracula\",age:222}";
        ResponseEntity<String> response = restTemplate.getForEntity("http//localhost:" + port + "/student/" + 333L, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void testCreateStudent() throws JsonProcessingException,JSONException{
        Student student = new Student();
        student.setId(51L);
        student.setName("Artur");
        student.setAge(12);
        studentService.createStudent(student);
        String expected = mapper.writeValueAsString(student);

        ResponseEntity<String> response = restTemplate.postForEntity("/student",student,String.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }

    @Test
    void testUpdateStudent() throws Exception{
        Student student = new Student();
        student.setId(51L);
        student.setName("Rutra");
        student.setAge(21);

        when(studentRepository.save(any())).thenReturn(student);
        HttpEntity<Student> entity = new HttpEntity<>(student);

        ResponseEntity<Student> response = this.restTemplate.exchange("/student",HttpMethod.PUT,entity,Student.class);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void deleteStudent(){
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("/student/333",HttpMethod.DELETE,entity,String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testFindByAge() throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty/age", String.class))
                .isNotNull();
    }

    @Test
    public void testFindStudentFaculty() throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty", String.class))
                .isNotNull();
    }
}
