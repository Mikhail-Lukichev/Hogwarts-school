package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private final StudentService service = new StudentService();

    @Test
    void add() {
        //Data preparation
        Student testStudent = new Student(0L,"name",20);

        //Expected result preparation
        Student expectedResult = testStudent; //single addition
        Collection<Student> expectedResultStudents = List.of(testStudent,testStudent); // multiple addition

        //Test execution
        Student actualResult = service.add(testStudent);
        assertEquals(expectedResult,actualResult);
        service.add(testStudent);
        Collection<Student> actualResultStudents = service.getAll();
        assertIterableEquals(expectedResultStudents,actualResultStudents);
    }

    @Test
    void get() {
        //Data preparation
        Student testStudent1 = new Student(0L,"111",20);
        service.add(testStudent1);

        //Expected result preparation
        Student expectedResult = testStudent1;

        //Test execution
        Student actualResult = service.get(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getByAge() {
        //Data preparation
        Student testStudent1 = new Student(0L,"111",20);
        Student testStudent2 = new Student(0L,"222",50);
        Student testStudent3 = new Student(0L,"333",20);
        service.add(testStudent1);
        service.add(testStudent2);
        service.add(testStudent3);

        //Expected result preparation
        Collection<Student> expectedResult = List.of(testStudent1,testStudent3); // multiple addition

        //Test execution
        Collection<Student> actualResult = service.getByAge(20);
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void getAll() {
        //Data preparation
        Student testStudent1 = new Student(0L,"111",20);
        Student testStudent2 = new Student(0L,"222",50);
        service.add(testStudent1);
        service.add(testStudent2);

        //Expected result preparation
        Collection<Student> expectedResult = List.of(testStudent1,testStudent2); // multiple addition

        //Test execution
        Collection<Student> actualResult = service.getAll();
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void update() {
        //Data preparation
        Student testStudent1 = new Student(0L,"111",20);
        Student testStudent2 = new Student(0L,"222",50);
        service.add(testStudent1);

        //Expected result preparation
        Student expectedResult = testStudent2;

        //Test execution
        Student actualResult = service.update(testStudent2);
        assertEquals(expectedResult,actualResult);
        actualResult = service.get(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void update_withNullReturn() {
        //Data preparation
        Student testStudent1 = new Student(0L,"111",20);

        //Expected result preparation

        //Test execution
        Student actualResult = service.update(testStudent1);
        assertNull(actualResult);
    }

    @Test
    void delete() {
        //Data preparation
        Student testStudent1 = new Student(0L,"111",20);
        service.add(testStudent1);

        //Expected result preparation
        Student expectedResult = testStudent1;

        //Test execution
        Student actualResult = service.delete(0L);
        assertEquals(expectedResult,actualResult);
        actualResult = service.get(0L);
        assertNull(actualResult);
    }

    @Test
    void delete_withNullReturn() {
        //Data preparation

        //Expected result preparation

        //Test execution
        Student actualResult = service.delete(0L);
        assertNull(actualResult);
    }
}