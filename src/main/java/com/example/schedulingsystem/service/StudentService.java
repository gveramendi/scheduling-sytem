package com.example.schedulingsystem.service;

import com.example.schedulingsystem.domain.Student;
import com.example.schedulingsystem.exception.EntityFormatException;
import com.example.schedulingsystem.exception.ServiceException;
import com.example.schedulingsystem.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService extends BaseService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
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

  public List<Student> getAll(Boolean deleted, Pageable pageable) {
    return this.studentRepository.findAllByDeleted(deleted, pageable);
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
}
