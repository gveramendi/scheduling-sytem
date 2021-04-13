package com.example.schedulingsystem.repository;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.domain.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<Course, Long>,
    JpaSpecificationExecutor<Course> {

  Optional<Course> findByCode(String code);

  List<Course> findAllByDeleted(boolean deleted);

  List<Course> findAllByStudentsInAndDeleted(List<Student> students, boolean deleted);

}
