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
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMVCTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentService studentService;
    @MockBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    void getAll() throws Exception {
        //Data preparation
        String name = "name";
        String color = "color";

        //Expected data preparation
        long id1 = 1L;
        long id2 = 1L;
        Faculty expectedFaculty1 = new Faculty();
        expectedFaculty1.setName(name);
        expectedFaculty1.setColor(color);
        expectedFaculty1.setId(id1);

        Faculty expectedFaculty2 = new Faculty();
        expectedFaculty2.setName(name);
        expectedFaculty2.setColor(color);
        expectedFaculty2.setId(id2);

        Collection<Faculty> expectedFacultyList = List.of(expectedFaculty1, expectedFaculty2);

        when(facultyService.getAll()).thenReturn(expectedFacultyList);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(color))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(color))
                .andReturn();
    }

    @Test
    void get() throws Exception {
        //Data preparation
        String name = "name";
        String color = "color";

        //Expected data preparation
        long id = 1L;
        Faculty expectedFaculty = new Faculty();
        expectedFaculty.setName(name);
        expectedFaculty.setColor(color);
        expectedFaculty.setId(id);
        when(facultyService.get(id)).thenReturn(expectedFaculty);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/"+String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color))
                .andReturn();
    }

    @Test
    void getByColor() throws Exception {
        //Data preparation
        String name = "name";
        String color = "color";

        //Expected data preparation
        long id1 = 1L;
        Faculty expectedFaculty1 = new Faculty();
        expectedFaculty1.setName(name);
        expectedFaculty1.setColor(color);
        expectedFaculty1.setId(id1);


        Collection<Faculty> expectedFacultyList = List.of(expectedFaculty1);

        when(facultyService.getByColor(color)).thenReturn(expectedFacultyList);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/color/"+ color))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(color))
                .andReturn();
    }

    @Test
    void getBySearchString() throws Exception {
        //Data preparation
        String name = "name";
        String color = "color";

        //Expected data preparation
        long id1 = 1L;
        Faculty expectedFaculty1 = new Faculty();
        expectedFaculty1.setName(name);
        expectedFaculty1.setColor(color);
        expectedFaculty1.setId(id1);


        Collection<Faculty> expectedFacultyList = List.of(expectedFaculty1);

        when(facultyService.getBySearchString(color)).thenReturn(expectedFacultyList);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/faculty/search/"+ color))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(color))
                .andReturn();
    }

    @Test
    void add() throws Exception {
        //Data preparation
        String name = "name";
        String color = "color";
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);

        String request = objectMapper.writeValueAsString(newFaculty);

        //Expected data preparation
        long id = 1L;
        Faculty createdFaculty = new Faculty();
        createdFaculty.setName(name);
        createdFaculty.setColor(color);
        createdFaculty.setId(id);
        when(facultyService.add(any(Faculty.class))).thenReturn(createdFaculty);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/faculty")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color))
                .andReturn();
    }

    @Test
    void update() throws Exception {
        //Data preparation
        long id = 1L;
        String name = "name";
        String color = "color";
        Faculty updateFaculty = new Faculty();
        updateFaculty.setId(id);
        updateFaculty.setName(name);
        updateFaculty.setColor(color);

        String request = objectMapper.writeValueAsString(updateFaculty);

        //Expected data preparation
        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setName(name);
        updatedFaculty.setColor(color);
        updatedFaculty.setId(id);
        when(facultyService.update(any(Faculty.class))).thenReturn(updatedFaculty);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/faculty")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color))
                .andReturn();
    }

    @Test
    void delete() throws Exception {
        //Data preparation
        String name = "name";
        String color = "color";

        //Expected data preparation
        long id = 1L;
        Faculty expectedFaculty = new Faculty();
        expectedFaculty.setName(name);
        expectedFaculty.setColor(color);
        expectedFaculty.setId(id);
        when(facultyService.get(id)).thenReturn(expectedFaculty);

        //Test execution
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/faculty/"+String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(color))
                .andReturn();
    }
}