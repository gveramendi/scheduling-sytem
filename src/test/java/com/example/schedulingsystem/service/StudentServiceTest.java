package com.example.schedulingsystem.service;

import com.example.schedulingsystem.domain.Student;
import com.example.schedulingsystem.exception.EntityFormatException;
import com.example.schedulingsystem.repository.StudentRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTest {

  @Autowired
  public StudentRepository studentRepository;

  @Autowired
  public StudentService studentService;

  @BeforeEach
  public void setUp() {
  }

  @AfterEach
  public void tearDown() {
    this.studentRepository.deleteAll();
  }

  @Test
  public void createStudentTest() {
    Student student = new Student("Juan", "Perez");
    Student createdStudent = this.studentService.create(student);

    Assertions.assertTrue(createdStudent.getId() > 0);
    Assertions.assertEquals(student.getFirstName(), createdStudent.getFirstName());
    Assertions.assertEquals(student.getLastName(), createdStudent.getLastName());
  }

  @Test
  public void createFailedStudentTest() {
    Exception exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.studentService.create(new Student(null, null)));
    Assertions.assertEquals("Invalid format on entity lastName must not be null.",
        exception.getMessage());

    exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.studentService.create(new Student("Juan", null)));
    Assertions.assertEquals("Invalid format on entity lastName must not be null.",
        exception.getMessage());

    exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.studentService.create(new Student(null, "Perez")));
    Assertions.assertEquals("Invalid format on entity firstName must not be null.",
        exception.getMessage());
  }

  @Test
  public void getStudentTest() {
    Student createdStudent = this.studentService.create(new Student("Juan", "Perez"));

    Student student = this.studentService.get(createdStudent.getId());
    Assertions.assertEquals(createdStudent.getId(), student.getId());
    Assertions.assertEquals(createdStudent.getFirstName(), student.getFirstName());
    Assertions.assertEquals(createdStudent.getLastName(), student.getLastName());
  }

  @Test
  public void failedGetStudentTest() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
        this.studentService.get(1L));
    Assertions.assertEquals("Student with id: 1 does not exits.", exception.getMessage());
  }

  @Test
  public void getStudentsTest() {
    Assertions.assertEquals(0, this.studentService.getAll(false, null).size());

    for (int i = 0; i < 10; i++) {
      Student student = new Student("firstName " + i, "lastName " + i);
      this.studentService.create(student);
    }

    List<Student> studentList = this.studentService.getAll(false, null);
    Assertions.assertEquals(10, studentList.size());

    int index = 0;
    for (Student student: this.studentService.getAll(false, null)) {
      Assertions.assertTrue(student.getId() > 0);
      Assertions.assertEquals("firstName " + index, student.getFirstName());
      Assertions.assertEquals("lastName " + index, student.getLastName());

      index++;
    }
  }

  @Test
  public void updateStudentTest() {
    Student createdStudent = this.studentService.create(new Student("Juan", "Perez"));

    Student modifiedStudent = new Student("Jose", "Perez");
    Student updatedStudent = this.studentService.update(createdStudent.getId(), modifiedStudent);

    Assertions.assertEquals(createdStudent.getId(), updatedStudent.getId());
    Assertions.assertEquals(modifiedStudent.getFirstName(), updatedStudent.getFirstName());
    Assertions.assertEquals(modifiedStudent.getLastName(), updatedStudent.getLastName());
  }

  @Test
  public void failedUpdateStudentTest() {
    Student createdStudent = this.studentService.create(new Student("Juan", "Perez"));

    Exception exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.studentService.update(createdStudent.getId(), new Student(null, null)));
    Assertions.assertEquals("Invalid format on entity firstName must not be null.",
        exception.getMessage());

    exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.studentService.update(createdStudent.getId(), new Student("Juan", null)));
    Assertions.assertEquals("Invalid format on entity lastName must not be null.",
        exception.getMessage());

    exception = Assertions.assertThrows(EntityFormatException.class, () ->
        this.studentService.update(createdStudent.getId(), new Student(null, "Perez")));
    Assertions.assertEquals("Invalid format on entity firstName must not be null.",
        exception.getMessage());
  }

  @Test
  public void deleteStudentTest() {
    Student createdStudent = this.studentService.create(new Student("Juan", "Perez"));

    Student deletedStudent = this.studentService.delete(createdStudent.getId());
    Assertions.assertEquals(createdStudent.getId(), deletedStudent.getId());
    Assertions.assertEquals(createdStudent.getFirstName(), deletedStudent.getFirstName());
    Assertions.assertEquals(createdStudent.getLastName(), deletedStudent.getLastName());
    Assertions.assertTrue(deletedStudent.isDeleted());
  }

  @Test
  public void failedDeleteStudentTest() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
        this.studentService.delete(1L));
    Assertions.assertEquals("Student with id: 1 does not exits.", exception.getMessage());
  }
}
