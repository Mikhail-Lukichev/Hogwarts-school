package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {
    private final FacultyRepository facultyRepository = mock(FacultyRepository.class);
    private final FacultyService service = new FacultyService(facultyRepository);

    @Test
    void add() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.save(any(Faculty.class))).thenReturn(testFaculty);

        //Expected result preparation
        Faculty expectedResult = testFaculty; //single addition

        //Test execution
        Faculty actualResult = service.add(testFaculty);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void get() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(testFaculty));

        //Expected result preparation
        Faculty expectedResult = testFaculty;

        //Test execution
        Faculty actualResult = service.get(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void getByColor() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.findByColor(anyString())).thenReturn(List.of(testFaculty,testFaculty));

        //Expected result preparation
        Collection<Faculty> expectedResult = List.of(testFaculty,testFaculty); // multiple addition

        //Test execution
        Collection<Faculty> actualResult = service.getByColor("color");
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void getAll() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.findAll()).thenReturn(List.of(testFaculty,testFaculty));

        //Expected result preparation
        Collection<Faculty> expectedResult = List.of(testFaculty,testFaculty); // multiple addition

        //Test execution
        Collection<Faculty> actualResult = service.getAll();
        assertIterableEquals(expectedResult,actualResult);
    }

    @Test
    void update() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.existsById(any())).thenReturn(true);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(testFaculty);

        //Expected result preparation
        Faculty expectedResult = testFaculty;

        //Test execution
        Faculty actualResult = service.update(testFaculty);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void update_withNullReturn() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.existsById(any())).thenReturn(false);

        //Expected result preparation

        //Test execution
        Faculty actualResult = service.update(testFaculty);
        assertNull(actualResult);
    }

    @Test
    void delete() {
        //Data preparation
        Faculty testFaculty = new Faculty();
        testFaculty.setName("name");
        testFaculty.setColor("color");
        when(facultyRepository.existsById(any())).thenReturn(true);
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(testFaculty));

        //Expected result preparation
        Faculty expectedResult = testFaculty;

        //Test execution
        Faculty actualResult = service.delete(0L);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void delete_withNullReturn() {
        //Data preparation
        when(facultyRepository.existsById(any())).thenReturn(false);

        //Expected result preparation

        //Test execution
        Faculty actualResult = service.delete(0L);
        assertNull(actualResult);
    }
}