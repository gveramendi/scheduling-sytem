package com.example.schedulingsytem.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course extends BaseEntityAudit {

  @Column(name = "code")
  private String code;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @ManyToMany(mappedBy = "courses")
  private Set<Student> students;

}
