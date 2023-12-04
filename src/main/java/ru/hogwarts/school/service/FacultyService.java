package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Collection<Faculty> getByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getBySearchString(String search) {
        return facultyRepository.findByNameOrColorIgnoreCase(search, search);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Faculty update(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            facultyRepository.save(faculty);
            return faculty;
        } else {
            return null;
        }
    }

    public Faculty delete(Long id) {
        if (facultyRepository.existsById(id)) {
            Faculty returnFaculty = facultyRepository.findById(id).orElse(null);
            facultyRepository.deleteById(id);
            return returnFaculty;
        } else {
            return null;
        }
    }
}
