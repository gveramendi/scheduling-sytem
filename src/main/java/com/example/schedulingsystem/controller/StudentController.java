package com.example.schedulingsystem.controller;

import com.example.schedulingsystem.domain.Student;
import com.example.schedulingsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/students")
public class StudentController {

  public final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
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
    modelAndView.addObject("students", this.studentService.getAll(false, null));

    return modelAndView;
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public ModelAndView edit(@PathVariable long id) {
    ModelAndView model = new ModelAndView("/students/update");
    model.addObject("student", this.studentService.get(id));

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

  @RequestMapping(value="/add_course/{studentId}/{courseId}", method=RequestMethod.POST)
  public ModelAndView addCourse(@PathVariable long studentId, @PathVariable long courseId) {
    this.studentService.addCourse(studentId, courseId);

    return new ModelAndView("redirect:/students/" + studentId);
  }

  @RequestMapping(value="/remove_course/{studentId}/{courseId}", method=RequestMethod.POST)
  public ModelAndView removeCourse(@PathVariable long studentId, @PathVariable long courseId) {
    this.studentService.removeCourse(studentId, courseId);

    return new ModelAndView("redirect:/students/" + studentId);
  }
}
