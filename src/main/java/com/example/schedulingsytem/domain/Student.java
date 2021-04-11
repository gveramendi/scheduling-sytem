package com.example.schedulingsytem.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends BaseEntityAudit {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "students_courses",
      joinColumns = { @JoinColumn(name = "student_id") },
      inverseJoinColumns = { @JoinColumn(name = "course_id") }
  )
  private Set<Course> courses;

}
