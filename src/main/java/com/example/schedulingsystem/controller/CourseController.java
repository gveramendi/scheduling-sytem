package com.example.schedulingsystem.controller;

import com.example.schedulingsystem.domain.Course;
import com.example.schedulingsystem.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @RequestMapping(value="/create", method=RequestMethod.GET)
  public ModelAndView showCreate() {
    ModelAndView model = new ModelAndView("/courses/create");
    model.addObject("course", new Course());

    return model;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView create(@ModelAttribute("course") Course course) {
    this.courseService.create(course);

    return new ModelAndView("redirect:/courses/list");
  }

  @RequestMapping(value="/list", method= RequestMethod.GET)
  public ModelAndView courses() {
    ModelAndView modelAndView = new ModelAndView("/courses/list");
    modelAndView.addObject("courses", this.courseService.getAll(false));

    return modelAndView;
  }

  @RequestMapping(value="/search", method= RequestMethod.GET)
  public ModelAndView search(@RequestParam("keyword") String keyword) {
    ModelAndView modelAndView = new ModelAndView("/courses/list");
    if (keyword != null && !keyword.isBlank()) {
      modelAndView.addObject("courses", this.courseService.search(keyword));
    } else {
      modelAndView.addObject("courses", this.courseService.getAll(false));
    }

    return modelAndView;
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public ModelAndView edit(@PathVariable long id) {
    ModelAndView model = new ModelAndView("/courses/update");
    model.addObject("course", this.courseService.get(id));

    return model;
  }

  @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
  public ModelAndView update(@PathVariable long id, @ModelAttribute("course") Course course) {
    this.courseService.update(id, course);

    return new ModelAndView("redirect:/courses/list");
  }

  @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
  public ModelAndView delete(@PathVariable("id") long id) {
    this.courseService.delete(id);

    return new ModelAndView("redirect:/courses/list");
  }
}
