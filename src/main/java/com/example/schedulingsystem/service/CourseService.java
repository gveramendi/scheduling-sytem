package com.example.schedulingsystem.service;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.domain.Student;
import com.example.schedulingsystem.exception.EntityFormatException;
import com.example.schedulingsystem.exception.ServiceException;
import com.example.schedulingsystem.repository.CourseRepository;
import com.example.schedulingsystem.specification.CourseSearchSpecification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseService extends BaseService {

  private final CourseRepository courseRepository;

  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, @Lazy StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public Course create(Course course) {
    validate(course);

    if (this.courseRepository.findByCode(course.getCode()).isPresent()) {
      throw new EntityFormatException("Course with code: " + course.getCode() + " already exits.");
    }

    Course createdCourse;
    try {
      createdCourse = this.courseRepository.save(course);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Creation course exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new ServiceException(errorMessage);
    }

    log.info("Created course: " + createdCourse);
    return createdCourse;
  }

  public Course get(Long id) {
    Optional<Course> course = this.courseRepository.findById(id);

    if (course.isPresent()) {
      return course.get();
    }

    throw new EntityNotFoundException("Course with id: " + id + " does not exits.");
  }

  public List<Course> getAll(Boolean deleted) {
    return this.courseRepository.findAllByDeleted(deleted);
  }

  public List<Course> search(String keyword) {
    return this.courseRepository.findAll(new CourseSearchSpecification(keyword));
  }

  public List<Course> getAll(Long studentId, boolean deleted) {
    Student student = this.studentService.get(studentId);
    List<Student> students = new ArrayList<>(Collections.singletonList(student));

    return this.courseRepository.findAllByStudentsInAndDeleted(students, deleted);
  }

  public Course update(Long courseId, Course modifiedCourse) {
    validate(modifiedCourse);

    Course course = this.get(courseId);
    Optional<Course> courseByName = this.courseRepository.findByCode(modifiedCourse.getCode());
    if (courseByName.isPresent() && !course.getId().equals(courseByName.get().getId())) {
      throw new EntityFormatException(
          "Course with code: " + courseByName.get().getCode() + " already exits.");
    }

    Course updatedCourse;
    try {
      course.setCode(modifiedCourse.getCode());
      course.setTitle(modifiedCourse.getTitle());
      course.setDescription(modifiedCourse.getDescription());

      updatedCourse = this.courseRepository.save(course);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Update course exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }

    log.info("Updated course: " + updatedCourse);
    return updatedCourse;
  }

  public Course delete(Long courseId) {
    Course course = this.get(courseId);

    Course deletedCourse;
    try {
      course.setDeleted(true);

      deletedCourse = this.courseRepository.save(course);
    } catch (DataIntegrityViolationException dive) {
      String errorMessage = "Update course exception: " + dive.getMessage();
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }

    log.info("Deleted course: " + deletedCourse);
    return deletedCourse;
  }
}
