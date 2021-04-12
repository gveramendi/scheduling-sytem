package com.example.schedulingsystem.repository;

import com.example.schedulingsystem.domain.Student;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findAllByDeleted(boolean deleted, Pageable pageable);

}
