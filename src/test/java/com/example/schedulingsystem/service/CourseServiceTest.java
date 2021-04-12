package com.example.schedulingsystem.service;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.exception.EntityFormatException;
import com.example.schedulingsystem.repository.CourseRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTest {

  @Autowired
  public CourseRepository courseRepository;

  @Autowired
  public CourseService courseService;

  @BeforeEach
  public void setUp() {
  }

  @AfterEach
  public void tearDown() {
    this.courseRepository.deleteAll();
  }

  @Test
  public void createCourseTest() {
    Course course = new Course("CO-001", "Course One", "Course One description");
    Course createdCourse = this.courseService.create(course);

    Assertions.assertTrue(createdCourse.getId() > 0);
    Assertions.assertEquals(course.getCode(), createdCourse.getCode());
    Assertions.assertEquals(course.getTitle(), createdCourse.getTitle());
    Assertions.assertEquals(course.getDescription(), createdCourse.getDescription());

    course = new Course("CO-002", "Course Two", "Course Two description");
    createdCourse = this.courseService.create(course);

    Assertions.assertTrue(createdCourse.getId() > 0);
    Assertions.assertEquals(course.getCode(), createdCourse.getCode());
    Assertions.assertEquals(course.getTitle(), createdCourse.getTitle());
    Assertions.assertEquals(course.getDescription(), createdCourse.getDescription());
  }

  @Test
  public void createFailedCourseTest() {
    Exception exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.courseService.create(new Course("CO-001", null, null)));
    Assertions.assertEquals("Invalid format on entity title must not be null.",
        exception.getMessage());

    exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.courseService.create(new Course(null, "Course Two", null)));
    Assertions.assertEquals("Invalid format on entity code must not be null.",
        exception.getMessage());

    Course c1 = this.courseService.create(new Course("CO-001", "Course One", "Course One description"));
    System.out.println("c1.getId() = " + c1.getId());

    exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.courseService.create(new Course("CO-001", "Course One", "Course One description")));
    Assertions.assertEquals("Invalid format on entity Course with code: CO-001 already exits.",
        exception.getMessage());
  }

  @Test
  public void getCourseTest() {
    Course createdCourse = this.courseService.create(
        new Course("CO-001", "Course One", "Course One description"));

    Course course = this.courseService.get(createdCourse.getId());
    Assertions.assertEquals(createdCourse.getId(), course.getId());
    Assertions.assertEquals(createdCourse.getCode(), course.getCode());
    Assertions.assertEquals(createdCourse.getTitle(), course.getTitle());
    Assertions.assertEquals(createdCourse.getDescription(), course.getDescription());
  }

  @Test
  public void failedGetCourseTest() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
        this.courseService.get(1L));
    Assertions.assertEquals("Course with id: 1 does not exits.", exception.getMessage());
  }

  @Test
  public void getCoursesTest() {
    Assertions.assertEquals(0, this.courseService.getCourses(false, null).size());

    for (int i = 0; i < 10; i++) {
      Course course = new Course("CO-00" + i, "Course " + i, "Course description " + i);
      this.courseService.create(course);
    }

    List<Course> courseList = this.courseService.getCourses(false, null);
    Assertions.assertEquals(10, courseList.size());

    int index = 0;
    for (Course course: this.courseService.getCourses(false, null)) {
      Assertions.assertTrue(course.getId() > 0);
      Assertions.assertEquals("CO-00" + index, course.getCode());
      Assertions.assertEquals("Course " + index, course.getTitle());
      Assertions.assertEquals("Course description " + index, course.getDescription());

      index++;
    }
  }

  @Test
  public void updateCourseTest() {
    Course createdCourse = this.courseService.create(
        new Course("CO-001", "Course One", "Course One description"));

    Course modifiedCourse = new Course("CO-001", "Course One modified", "Course One modified description");
    Course updatedCourse = this.courseService.update(createdCourse.getId(), modifiedCourse);

    Assertions.assertEquals(createdCourse.getId(), updatedCourse.getId());
    Assertions.assertEquals(modifiedCourse.getCode(), updatedCourse.getCode());
    Assertions.assertEquals(modifiedCourse.getTitle(), updatedCourse.getTitle());
    Assertions.assertEquals(modifiedCourse.getDescription(), updatedCourse.getDescription());
  }

  @Test
  public void failedUpdateCourseTest() {
    this.courseService.create(
        new Course("CO-001", "Course One", "Course One description"));

    Course course = this.courseService.create(
        new Course("CO-002", "Course Two", "Course Two description"));

    Exception exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.courseService.update(course.getId(),
            new Course("CO-001", "Course Two modified", "Course Two modified description")));
    Assertions.assertEquals("Invalid format on entity Course with code: CO-001 already exits.", exception.getMessage());
  }

  @Test
  public void deleteCourseTest() {
    Course createdCourse = this.courseService.create(
        new Course("CO-001", "Course One", "Course One description"));

    Course deletedCourse = this.courseService.delete(createdCourse.getId());
    Assertions.assertEquals(createdCourse.getId(), deletedCourse.getId());
    Assertions.assertEquals(createdCourse.getCode(), deletedCourse.getCode());
    Assertions.assertEquals(createdCourse.getTitle(), deletedCourse.getTitle());
    Assertions.assertEquals(createdCourse.getDescription(), deletedCourse.getDescription());
    Assertions.assertTrue(deletedCourse.isDeleted());
  }

  @Test
  public void failedDeleteCourseTest() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
        this.courseService.delete(1L));
    Assertions.assertEquals("Course with id: 1 does not exits.", exception.getMessage());
  }
}
