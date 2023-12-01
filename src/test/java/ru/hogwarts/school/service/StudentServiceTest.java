package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    private final StudentRepository studentRepository = mock(StudentRepository.class);
    private final StudentService service = new StudentService(studentRepository);

    @Test
    void add() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        //Expected result preparation
        Student expectedResult = testStudent; //single addition

        //Test execution
        Student actualResult = service.add(testStudent);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void get() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(testStudent));

        //Expected result preparation
        Student expectedResult = testStudent;

        //Test execution
        Student actualResult = service.get(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getByAge() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.findByAge(anyInt())).thenReturn(List.of(testStudent,testStudent));

        //Expected result preparation
        Collection<Student> expectedResult = List.of(testStudent,testStudent); // multiple addition

        //Test execution
        Collection<Student> actualResult = service.getByAge(20);
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void getAll() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.findAll()).thenReturn(List.of(testStudent,testStudent));

        //Expected result preparation
        Collection<Student> expectedResult = List.of(testStudent,testStudent); // multiple addition

        //Test execution
        Collection<Student> actualResult = service.getAll();
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void update() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.existsById(any())).thenReturn(true);
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        //Expected result preparation
        Student expectedResult = testStudent;

        //Test execution
        Student actualResult = service.update(testStudent);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void update_withNullReturn() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.existsById(any())).thenReturn(false);

        //Expected result preparation

        //Test execution
        Student actualResult = service.update(testStudent);
        assertNull(actualResult);
    }

    @Test
    void delete() {
        //Data preparation
        Student testStudent = new Student();
        testStudent.setName("name");
        testStudent.setAge(20);
        when(studentRepository.existsById(any())).thenReturn(true);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(testStudent));

        //Expected result preparation
        Student expectedResult = testStudent;

        //Test execution
        Student actualResult = service.delete(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void delete_withNullReturn() {
        //Data preparation
        when(studentRepository.existsById(any())).thenReturn(false);

        //Expected result preparation

        //Test execution
        Student actualResult = service.delete(0L);
        assertNull(actualResult);
    }
}