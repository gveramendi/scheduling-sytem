package com.example.schedulingsytem.repository;

import com.example.schedulingsytem.domain.Course;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findByCode(String code);

  List<Course> findAllByDeleted(boolean deleted, Pageable pageable);

}