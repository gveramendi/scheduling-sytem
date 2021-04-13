package com.example.schedulingsystem.controller;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.domain.Student;
import com.example.schedulingsystem.service.CourseService;
import com.example.schedulingsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/students")
public class StudentController {

  public final StudentService studentService;

  private final CourseService courseService;

  public StudentController(StudentService studentService, CourseService courseService) {
    this.studentService = studentService;
    this.courseService = courseService;
  }

  @RequestMapping(value="/create", method= RequestMethod.GET)
  public ModelAndView showCreate() {
    ModelAndView model = new ModelAndView("/students/create");
    model.addObject("student", new Student());

    return model;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView create(@ModelAttribute("student") Student student) {
    this.studentService.create(student);

    return new ModelAndView("redirect:/students/list");
  }

  @RequestMapping(value="/list", method= RequestMethod.GET)
  public ModelAndView students() {
    ModelAndView modelAndView = new ModelAndView("/students/list");
    modelAndView.addObject("students", this.studentService.getAll(false));

    return modelAndView;
  }

  @RequestMapping(value="/search", method= RequestMethod.GET)
  public ModelAndView search(@RequestParam("keyword") String keyword) {
    ModelAndView modelAndView = new ModelAndView("/students/list");
    if (keyword != null && !keyword.isBlank()) {
      modelAndView.addObject("students", this.studentService.search(keyword));
    } else {
      modelAndView.addObject("students", this.studentService.getAll(false));
    }

    return modelAndView;
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public ModelAndView edit(@PathVariable long id) {
    ModelAndView model = new ModelAndView("/students/update");
    model.addObject("student", this.studentService.get(id));
    model.addObject("courses", this.courseService.getAll(id, false));

    model.addObject("selectedCourse", new Course());
    model.addObject("courseList", this.courseService.getAll(false));

    return model;
  }

  @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
  public ModelAndView update(@PathVariable long id, @ModelAttribute("student") Student student) {
    this.studentService.update(id, student);

    return new ModelAndView("redirect:/students/list");
  }

  @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
  public ModelAndView delete(@PathVariable("id") long id) {
    this.studentService.delete(id);

    return new ModelAndView("redirect:/students/list");
  }

  @RequestMapping(value="/add_course/{studentId}", method=RequestMethod.GET)
  public ModelAndView addCourse(@PathVariable long studentId, Course course) {
    this.studentService.addCourse(studentId, course.getId());

    return new ModelAndView("redirect:/students/" + studentId);
  }

  @RequestMapping(value="/remove_course/{studentId}/{courseId}", method=RequestMethod.GET)
  public ModelAndView removeCourse(@PathVariable long studentId, @PathVariable long courseId) {
    this.studentService.removeCourse(studentId, courseId);

    return new ModelAndView("redirect:/students/" + studentId);
  }
}
