package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return facultyRepository.save(faculty);
    }

    public Faculty get(Long id) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        Faculty dbResponse = facultyRepository.findById(id).orElse(null);
        if (dbResponse == null) {
            logger.warn("Faculty with id {} is not found", id);
        }
        return dbResponse;
    }

    public Collection<Faculty> getByColor(String color) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getBySearchString(String search) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return facultyRepository.findByNameOrColorIgnoreCase(search, search);
    }

    public Collection<Faculty> getAll() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return facultyRepository.findAll();
    }

    public Faculty update(Faculty faculty) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        if (facultyRepository.existsById(faculty.getId())) {
            facultyRepository.save(faculty);
            return faculty;
        } else {
            logger.warn("Faculty with id {} is not found. Cannot update the faculty", faculty.getId());
            return null;
        }
    }

    public Faculty delete(Long id) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        if (facultyRepository.existsById(id)) {
            Faculty returnFaculty = facultyRepository.findById(id).orElse(null);
            facultyRepository.deleteById(id);
            return returnFaculty;
        } else {
            logger.warn("Faculty with id {} is not found. Cannot delete the faculty", id);
            return null;
        }
    }

    public String getLongestName() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        Comparator<Faculty> nameComparator = (f1, f2) -> f1.getName().compareTo(f2.getName());
        Optional<Faculty> output = facultyRepository.findAll().stream().max(nameComparator);
        return output.map(Faculty::getName).orElse(null);
    }
}
