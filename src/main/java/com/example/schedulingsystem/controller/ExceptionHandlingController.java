package com.example.schedulingsystem.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandlingController {

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
    System.out.println("req.getRequestURL() = " + req.getRequestURL());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", ex.getMessage());
    modelAndView.addObject("url", req.getRequestURL());
    modelAndView.setViewName("/error");

    return modelAndView;
  }
}
