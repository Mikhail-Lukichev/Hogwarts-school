package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.save(student);
    }

    public Student get(Long id) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        Student dbResponse = studentRepository.findById(id).orElse(null);
        if (dbResponse == null) {
            logger.warn("Student with id {} is not found", id);
        }
        return dbResponse;
    }

    public Faculty getFaculty(Long id) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            logger.warn("Student with id {} is not found", id);
        }

        assert student != null;
        return student.getFaculty();
    }

    public Collection<Student> getByFacultyId(Long id) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.findByFaculty_id(id);
    }

    public Collection<Student> getByAge(int age) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.findByAge(age);
    }

    public Collection<Student> getByMinMaxAge(int minAge, int maxAge) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.findByAgeBetween(minAge,maxAge);
    }

    public Collection<Student> getAll() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.findAll();
    }

    public Student update(Student student) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
            return student;
        } else {
            logger.warn("Student with id {} is not found. Cannot update the student", student.getId());
            return null;
        }
    }

    public Student delete(Long id) {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        if (studentRepository.existsById(id)) {
            Student returnStudent = studentRepository.findById(id).orElse(null);
            studentRepository.deleteById(id);
            return returnStudent;
        } else {
            logger.warn("Student with id {} is not found. Cannot update the student", id);
            return null;
        }
    }

    public Integer count() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.getCount();
    }

    public Double getAverageAge() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.getAverageAge();
    }

    public Collection<Student> getLastFiveStudents() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        return studentRepository.getLastFive();
    }

    public Collection<Student> getWithNameStartA() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        Comparator<Student> nameComparator = (s1,s2) -> s1.getName().compareTo(s2.getName());
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().startsWith("A"))
                .sorted(nameComparator)
                .peek(student -> student.setName(student.getName().toUpperCase()))
                .toList();
    }

    public Double getAverageAgeStream() {
        String className = this.getClass().getSimpleName();
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        logger.info("Call " + className + " " + methodName);

        List<Student> students = studentRepository.findAll();
        Double sumAge = students.stream().mapToDouble(Student::getAge).sum();
        Long count = (long) students.size();

        return sumAge / count;
    }
}
