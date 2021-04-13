package com.example.schedulingsystem.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "students")
public class Student extends BaseEntityAudit {

  @NotNull
  @NotEmpty
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotNull
  @NotEmpty
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "students_courses",
      joinColumns = { @JoinColumn(name = "student_id") },
      inverseJoinColumns = { @JoinColumn(name = "course_id") }
  )
  private Set<Course> courses;

  public Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void addCourse(Course course) {
    if(this.getCourses() == null){
      this.courses = new HashSet<>();
    }

    this.courses.add(course);
  }

  public void removeCourse(Course course) {
    if(this.getCourses() == null){
      return;
    }

    this.courses.remove(course);
  }

  @Override
  public String toString() {
    return "Student{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", courses=" + courses +
        '}';
  }
}
