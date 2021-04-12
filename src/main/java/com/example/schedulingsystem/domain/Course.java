package com.example.schedulingsystem.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "courses")
public class Course extends BaseEntityAudit {

  @NotNull
  @Column(name = "code", unique = true)
  private String code;

  @NotNull
  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @ManyToMany(mappedBy = "courses")
  private Set<Student> students;

  public Course(String code, String title, String description) {
    this.code = code;
    this.title = title;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id='" + id + '\'' +
        "code='" + code + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
