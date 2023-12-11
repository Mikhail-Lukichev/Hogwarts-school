package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAll() {
//        //Data preparation
//        Faculty newFaculty1 = new Faculty();
//        newFaculty1.setId(1L);
//        newFaculty1.setName("name");
//        newFaculty1.setColor("color");
//
////        Faculty newFaculty2 = new Faculty();
////        newFaculty2.setId(2L);
////        newFaculty2.setName("name");
////        newFaculty2.setColor("color");
//        Faculty postedFaculty1 = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty1, Faculty.class);
////        this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty2, Faculty.class);
//
//
//
//        //Expected result preparation
////        Collection<Faculty> expectedResult = List.of(newFaculty1, newFaculty2);
////        Faculty expectedResult = newFaculty1;
////        Faculty expectedResult = new Faculty();
////        expectedResult.setId(1L);
////        expectedResult.setName("name");
////        expectedResult.setColor("color");
//
//        //Test execution
//        Faculty actualResult = this.restTemplate.getForObject("http://localhost:" + port + "/faculty",Faculty.class);
//        assertNotNull(actualResult);
////        assertEquals(expectedResult,actualResult);
    }

    @Test
    void get() {
        //Data preparation
        Faculty newFaculty1 = new Faculty();
        newFaculty1.setId(1L);
        newFaculty1.setName("name");
        newFaculty1.setColor("color");

        //Expected result preparation
        Faculty postedFaculty1 = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty1, Faculty.class);
        Long id = postedFaculty1.getId();

        //Test execution
//        ResponseEntity<Faculty> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
//        this.restTemplate.getForEntity("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
//        Faculty actualResult = this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + id+"/", Faculty.class);
        String requestUrl = "http://localhost:" + port + "/faculty/" + id+"/";
        ResponseEntity<Faculty> actualResult = this.restTemplate.getForEntity(requestUrl, Faculty.class);
        Faculty actualResultBody = actualResult.getBody();
//        assertNotNull(actualResult.getBody());
        assertEquals(postedFaculty1,actualResult);
    }

    @Test
    void getStudents() {
    }

    @Test
    void getByColor() {
    }

    @Test
    void getBySearchString() {
    }

    @Test
    void add() {
        //Data preparation
        Faculty newFaculty = new Faculty();
        newFaculty.setId(1L);
        newFaculty.setName("name");
        newFaculty.setColor("color");

        //Expected result preparation
        Faculty expectedResult = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("name");
        expectedResult.setColor("color");

        //Test execution
        Faculty actualResult = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}