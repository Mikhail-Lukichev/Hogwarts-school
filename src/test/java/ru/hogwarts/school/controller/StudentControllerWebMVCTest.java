package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerWebMVCTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentService studentService;
    @MockBean
    private FacultyService facultyService;
    @InjectMocks
    private StudentController studentController;

    @Test
    void add() throws Exception {
        //Data preparation
        String name = "name";
        int age = 20;
        Student newStudent = new Student(name,age);

        String request = objectMapper.writeValueAsString(newStudent);

        //Expected data preparation
        long id = 1L;
        Student createdStudent = new Student(name,age);
        createdStudent.setId(id);
        when(studentService.add(any(Student.class))).thenReturn(createdStudent);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/student")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age))
                .andReturn();
    }

    @Test
    void getAll() throws Exception {
        //Data preparation
        String name = "name";
        int age = 20;

        //Expected data preparation
        long id1 = 1L;
        long id2 = 1L;
        Student expectedStudent1 = new Student(name,age);
        expectedStudent1.setId(id1);

        Student expectedStudent2 = new Student(name,age);
        expectedStudent2.setId(id2);

        Collection<Student> expectedStudentList = List.of(expectedStudent1, expectedStudent2);

        when(studentService.getAll()).thenReturn(expectedStudentList);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/student"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(age))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(age))
                .andReturn();
    }

    @Test
    void get() throws Exception {
        //Data preparation
        String name = "name";
        int age = 20;

        //Expected data preparation
        long id = 1L;
        Student expectedStudent = new Student(name,age);
        expectedStudent.setId(id);
        when(studentService.get(id)).thenReturn(expectedStudent);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/student/" + String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age))
                .andReturn();
    }

    @Test
    void getByAge() throws Exception {
        //Data preparation
        String name = "name";
        int age = 20;

        //Expected data preparation
        long id1 = 1L;
        Student expectedStudent1 = new Student(name,age);
        expectedStudent1.setId(id1);

        Collection<Student> expectedStudentList = List.of(expectedStudent1);

        when(studentService.getByAge(age)).thenReturn(expectedStudentList);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/student/age/" + age))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(age))
                .andReturn();
    }

    @Test
    void getByMinMaxAge() throws Exception {
        //Data preparation
        String name = "name";
        int age1 = 20;
        int age2 = 20;

        //Expected data preparation
        long id1 = 1L;
        long id2 = 2L;
        Student expectedStudent1 = new Student(name,age1);
        expectedStudent1.setId(id1);

        Student expectedStudent2 = new Student(name,age2);
        expectedStudent2.setId(id2);

        Collection<Student> expectedStudentList = List.of(expectedStudent1, expectedStudent2);

        when(studentService.getByMinMaxAge(age1, age2)).thenReturn(expectedStudentList);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/student/age/" + age1 + "/" + age2))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(age1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(age2))
                .andReturn();
    }



    @Test
    void update() throws Exception {
        //Data preparation
        long id = 1L;
        String name = "name";
        int age = 20;
        Student updateStudent = new Student(name,age);
        updateStudent.setId(id);

        String request = objectMapper.writeValueAsString(updateStudent);

        //Expected data preparation
        Student updatedStudent = new Student(name,age);
        updatedStudent.setId(id);
        when(studentService.update(any(Student.class))).thenReturn(updatedStudent);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/student")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age))
                .andReturn();
    }

    @Test
    void delete() throws Exception {
        //Data preparation
        String name = "name";
        int age = 20;

        //Expected data preparation
        long id = 1L;
        Student expectedStudent = new Student(name,age);
        expectedStudent.setId(id);
        when(studentService.get(id)).thenReturn(expectedStudent);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/student/"+String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(age))
                .andReturn();
    }
}