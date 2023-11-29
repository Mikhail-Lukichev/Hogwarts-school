package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {
    private final FacultyService service = new FacultyService();

    @Test
    void add() {
        //Data preparation
        Faculty testFaculty = new Faculty(0L,"name","color");

        //Expected result preparation
        Faculty expectedResult = testFaculty; //single addition
        Collection<Faculty> expectedResultFaculties = List.of(testFaculty,testFaculty); // multiple addition

        //Test execution
        Faculty actualResult = service.add(testFaculty);
        assertEquals(expectedResult,actualResult);
        service.add(testFaculty);
        Collection<Faculty> actualResultFaculties = service.getAll();
        assertIterableEquals(expectedResultFaculties,actualResultFaculties);
    }

    @Test
    void get() {
        //Data preparation
        Faculty testFaculty1 = new Faculty(0L,"111","color");
        service.add(testFaculty1);

        //Expected result preparation
        Faculty expectedResult = testFaculty1;

        //Test execution
        Faculty actualResult = service.get(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getByAge() {
        //Data preparation
        Faculty testFaculty1 = new Faculty(0L,"111","color");
        Faculty testFaculty2 = new Faculty(0L,"222","anotherColor");
        Faculty testFaculty3 = new Faculty(0L,"333","color");
        service.add(testFaculty1);
        service.add(testFaculty2);
        service.add(testFaculty3);

        //Expected result preparation
        Collection<Faculty> expectedResult = List.of(testFaculty1,testFaculty3); // multiple addition

        //Test execution
        Collection<Faculty> actualResult = service.getByColor("color");
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void getAll() {
        //Data preparation
        Faculty testFaculty1 = new Faculty(0L,"111","color");
        Faculty testFaculty2 = new Faculty(0L,"222","color");
        service.add(testFaculty1);
        service.add(testFaculty2);

        //Expected result preparation
        Collection<Faculty> expectedResult = List.of(testFaculty1,testFaculty2); // multiple addition

        //Test execution
        Collection<Faculty> actualResult = service.getAll();
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void update() {
        //Data preparation
        Faculty testFaculty1 = new Faculty(0L,"111","color");
        Faculty testFaculty2 = new Faculty(0L,"222","anotherColor");
        service.add(testFaculty1);

        //Expected result preparation
        Faculty expectedResult = testFaculty2;

        //Test execution
        Faculty actualResult = service.update(testFaculty2);
        assertEquals(expectedResult,actualResult);
        actualResult = service.get(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void update_withNullReturn() {
        //Data preparation
        Faculty testFaculty1 = new Faculty(0L,"111","color");

        //Expected result preparation

        //Test execution
        Faculty actualResult = service.update(testFaculty1);
        assertNull(actualResult);
    }

    @Test
    void delete() {
        //Data preparation
        Faculty testFaculty1 = new Faculty(0L,"111","color");
        service.add(testFaculty1);

        //Expected result preparation
        Faculty expectedResult = testFaculty1;

        //Test execution
        Faculty actualResult = service.delete(0L);
        assertEquals(expectedResult,actualResult);
        actualResult = service.get(0L);
        assertNull(actualResult);
    }

    @Test
    void delete_withNullReturn() {
        //Data preparation

        //Expected result preparation

        //Test execution
        Faculty actualResult = service.delete(0L);
        assertNull(actualResult);
    }
}