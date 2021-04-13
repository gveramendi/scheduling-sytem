package com.example.schedulingsystem.service;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.domain.Student;
import com.example.schedulingsystem.exception.EntityFormatException;
import com.example.schedulingsystem.exception.ServiceException;
import com.example.schedulingsystem.repository.StudentRepository;
import com.example.schedulingsystem.specification.StudentSearchSpecification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService extends BaseService {

  private final StudentRepository studentRepository;

  private final CourseService courseService;

  public StudentService(StudentRepository studentRepository, @Lazy CourseService courseService) {
    this.studentRepository = studentRepository;
    this.courseService = courseService;
  }

  public Student create(Student student) {
    validate(student);

    Student createdStudent;
    try {
      createdStudent = this.studentRepository.save(student);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Creation student exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new ServiceException(errorMessage);
    }

    log.info("Created student: " + createdStudent);
    return createdStudent;
  }

  public Student get(Long id) {
    Optional<Student> student = this.studentRepository.findById(id);

    if (student.isPresent()) {
      return student.get();
    }

    throw new EntityNotFoundException("Student with id: " + id + " does not exits.");
  }

  public List<Student> getAll(Boolean deleted) {
    return this.studentRepository.findAllByDeleted(deleted);
  }

  public List<Student> getAll(Long courseId, boolean deleted) {
    Course course = this.courseService.get(courseId);
    List<Course> courses = new ArrayList<>(Collections.singletonList(course));

    return this.studentRepository.findAllByCoursesInAndDeleted(courses, deleted);
  }

  public List<Student> search(String keyword) {
    return this.studentRepository.findAll(new StudentSearchSpecification(keyword));
  }

  public Student update(Long studentId, Student modifiedStudent) {
    validate(modifiedStudent);
    Student student = this.get(studentId);

    Student updatedStudent;
    try {
      student.setFirstName(modifiedStudent.getFirstName());
      student.setLastName(modifiedStudent.getLastName());

      updatedStudent = this.studentRepository.save(student);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Update student exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }

    log.info("Updated student: " + updatedStudent);
    return updatedStudent;
  }

  public Student delete(Long studentId) {
    Student student = this.get(studentId);

    Student deletedStudent;
    try {
      student.setDeleted(true);

      deletedStudent = this.studentRepository.save(student);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Update student exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }

    log.info("Deleted student: " + deletedStudent);
    return deletedStudent;
  }

  public Student addCourse(Long studentId, Long courseId) {
    Student student = this.get(studentId);
    Course course = this.courseService.get(courseId);
    this.loadCourse(student);

    if (student.getCourses().contains(course)) {
      throw new EntityFormatException(
          "Course with id: " + course.getId() + " already belong to student with id: " + student.getId() + ".");
    }

    Student updatedStudent;
    try {
      student.addCourse(course);

      updatedStudent = this.studentRepository.save(student);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Update student exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }

    log.info("Add course: " + course + " to student: " + updatedStudent);
    return updatedStudent;
  }

  public Student removeCourse(Long studentId, Long courseId) {
    Student student = this.get(studentId);
    Course course = this.courseService.get(courseId);
    this.loadCourse(student);

    if (!student.getCourses().contains(course)) {
      throw new EntityFormatException(
          "Course with id: " + course.getId() + " does not belong to student with id: " + student.getId() + ".");
    }

    Student updatedStudent;
    try {
      student.removeCourse(course);

      updatedStudent = this.studentRepository.save(student);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Update student exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }

    log.info("Remove course: " + course + " to student: " + updatedStudent);
    return updatedStudent;
  }

  private void loadCourse(Student student) {
    List<Course> courses = this.courseService.getAll(student.getId(), false);

    student.setCourses(new HashSet<>(courses));
  }
}
