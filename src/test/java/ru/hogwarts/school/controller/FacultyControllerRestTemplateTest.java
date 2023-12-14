package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

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
    void add() {
        //Data preparation
        Faculty newFaculty = new Faculty("name","color");

        //Expected result preparation
        Faculty expectedResult = new Faculty("name","color");
        expectedResult.setId(1L);

        //Test execution
        Faculty actualResult = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAll() {
        //Data preparation
        Faculty newFaculty1 = new Faculty("name1","color1");
        Faculty newFaculty2 = new Faculty("name2","color2");

        //Expected result preparation
        Faculty postedFaculty1 = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty1, Faculty.class);
        Faculty postedFaculty2 = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty2, Faculty.class);
        List<Faculty> expectedResult = List.of(postedFaculty1,postedFaculty2);

        //Test execution
        ResponseEntity<Faculty[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty", Faculty[].class);
        List<Faculty> actualResult = List.of(response.getBody());
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void get() {
        //Data preparation
        Faculty newFaculty = new Faculty("name","color");

        //Expected result preparation
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        Long id = postedFaculty.getId();

        //Test execution
        ResponseEntity<Faculty> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/{id}", Faculty.class,id);
        assertEquals(postedFaculty,actualResult.getBody());
    }

    @Test
    void getByColor() {
        //Data preparation
        Faculty newFaculty = new Faculty("name","getByColor");

        //Expected result preparation
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        String color = postedFaculty.getColor();
        List<Faculty> expectedResult = List.of(postedFaculty);

        //Test execution
        ResponseEntity<Faculty[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/color/{color}", Faculty[].class,color);
        List<Faculty> actualResult = List.of(response.getBody());
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getBySearchString() {
        //Data preparation
        Faculty newFaculty = new Faculty("name","getBySearchString");

        //Expected result preparation
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        String color = postedFaculty.getColor();
        List<Faculty> expectedResult = List.of(postedFaculty);

        //Test execution
        ResponseEntity<Faculty[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/search/{color}", Faculty[].class,color);
        List<Faculty> actualResult = List.of(response.getBody());
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void update() {
        //Data preparation
        Faculty newFaculty = new Faculty("name","color");

        Faculty updatedFaculty = new Faculty("newName","newColor");

        //Expected result preparation
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        Long id = postedFaculty.getId();
        updatedFaculty.setId(id);

        //Test execution
        this.restTemplate.put("http://localhost:" + port + "/faculty", updatedFaculty);
        ResponseEntity<Faculty> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/{id}", Faculty.class,id);
        assertEquals(updatedFaculty,actualResult.getBody());
    }

    @Test
    void delete() {
        //Data preparation
        Faculty newFaculty = new Faculty("name","color");

        //Expected result preparation
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", newFaculty, Faculty.class);
        Long id = postedFaculty.getId();

        //Test execution
        this.restTemplate.delete("http://localhost:" + port + "/faculty/{id}",id);
        ResponseEntity<Faculty> actualResult = this.restTemplate.getForEntity("http://localhost:" + port + "/faculty/{id}", Faculty.class,id);
        assertNull(actualResult.getBody());
    }
}