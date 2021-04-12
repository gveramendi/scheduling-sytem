package com.example.schedulingsystem.bootstrap;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.service.CourseService;
import com.github.javafaker.Faker;
import java.util.Locale;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class CourseLoader implements CommandLineRunner {

  private final CourseService courseService;

  public CourseLoader(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  public void run(String... args) {
    Faker faker = new Faker();
    faker.code().isbnRegistrant();

    for (int i=0; i<10; i++) {
      this.courseService.create(
          new Course(faker.code().isbnRegistrant(), faker.name().title(), faker.lorem().paragraph())
      );
    }
  }
}
