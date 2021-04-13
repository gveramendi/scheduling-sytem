package com.example.schedulingsystem.repository;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.domain.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>,
    JpaSpecificationExecutor<Student> {

  List<Student> findAllByDeleted(boolean deleted);

  List<Student> findAllByCoursesInAndDeleted(List<Course> courses, boolean deleted);

}
