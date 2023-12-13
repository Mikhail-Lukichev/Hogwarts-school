package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() {
        //Data preparation
        Student newStudent1 = new Student();
        newStudent1.setId(1L);
        newStudent1.setName("name1");
        newStudent1.setAge(20);

        Student newStudent2 = new Student();
        newStudent2.setId(2L);
        newStudent2.setName("name2");
        newStudent2.setAge(40);

        //Expected result preparation
        Student postedStudent1 = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent1, Student.class);
        Student postedStudent2 = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent2, Student.class);
        List<Student> expectedResult = List.of(newStudent1, newStudent2);

        //Test execution
        ResponseEntity<Student[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/student", Student[].class);
        List<Student> actualResult = List.of(response.getBody());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void get() {
        //Data preparation
        Student newStudent = new Student();
        newStudent.setId(1L);
        newStudent.setName("name");
        newStudent.setAge(20);

        //Expected result preparation
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent, Student.class);
        Long id = postedStudent.getId();

        //Test execution
        ResponseEntity<Student> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/student/{id}", Student.class, id);
        assertEquals(postedStudent, actualResult.getBody());
    }

    @Test
    void getByAge() {
        //Data preparation
        Student newStudent = new Student();
        newStudent.setId(1L);
        newStudent.setName("name");
        newStudent.setAge(20);

        //Expected result preparation
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent, Student.class);
        Integer age = postedStudent.getAge();
        List<Student> expectedResult = List.of(postedStudent);

        //Test execution
        ResponseEntity<Student[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/student/age/{age}", Student[].class, age);
        List<Student> actualResult = List.of(response.getBody());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getByMinMaxAge() {
        //Data preparation
        Student newStudent1 = new Student();
        newStudent1.setId(1L);
        newStudent1.setName("name1");
        newStudent1.setAge(20);

        Student newStudent2 = new Student();
        newStudent2.setId(2L);
        newStudent2.setName("name2");
        newStudent2.setAge(40);

        Student newStudent3 = new Student();
        newStudent3.setId(3L);
        newStudent3.setName("name3");
        newStudent3.setAge(60);

        //Expected result preparation
        Student postedStudent1 = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent1, Student.class);
        Student postedStudent2 = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent2, Student.class);
        Student postedStudent3 = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent3, Student.class);
        List<Student> expectedResult = List.of(newStudent1, newStudent2);

        //Test execution
        ResponseEntity<Student[]> response = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student/age/{minAge}/{maxAge}",
                Student[].class,
                postedStudent1.getAge(),
                postedStudent2.getAge()
        );
        List<Student> actualResult = List.of(response.getBody());
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void add() {
        //Data preparation
        Student newStudent = new Student();
        newStudent.setId(1L);
        newStudent.setName("name");
        newStudent.setAge(20);

        //Expected result preparation
        Student expectedResult = new Student();
        expectedResult.setId(1L);
        expectedResult.setName("name");
        expectedResult.setAge(20);

        //Test execution
        Student actualResult = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent, Student.class);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void update() {
        //Data preparation
        Student newStudent = new Student();
        newStudent.setId(1L);
        newStudent.setName("name");
        newStudent.setAge(20);

        Student updatedStudent = new Student();
        updatedStudent.setName("newName");
        updatedStudent.setAge(40);

        //Expected result preparation
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent, Student.class);
        Long id = postedStudent.getId();
        updatedStudent.setId(id);

        //Test execution
        this.restTemplate.put("http://localhost:" + port + "/student", updatedStudent);
        ResponseEntity<Student> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/student/{id}", Student.class,id);
        assertEquals(updatedStudent,actualResult.getBody());
    }

    @Test
    void delete() {
        //Data preparation
        Student newStudent = new Student();
        newStudent.setId(1L);
        newStudent.setName("name");
        newStudent.setAge(20);

        //Expected result preparation
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", newStudent, Student.class);
        Long id = postedStudent.getId();

        //Test execution
        this.restTemplate.delete("http://localhost:" + port + "/student/{id}",id);
        ResponseEntity<Student> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/student/{id}", Student.class,id);
        assertNull(actualResult.getBody());
    }
}