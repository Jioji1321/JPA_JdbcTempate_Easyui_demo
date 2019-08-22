package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ApplicationController
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/20 0020 14:45
 * @Version 1.0
 **/
@Controller
public class ApplicationController {

    @RequestMapping({"/","/main"})
    public ModelAndView goMainPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("main");
        return view;
    }

    @RequestMapping("/student")
    public ModelAndView goStudentGrid(){
        ModelAndView view = new ModelAndView();
        view.setViewName("student/student_grid");
        return view;
    }

    @RequestMapping("/course")
    public ModelAndView goCourseGrid(){
        ModelAndView view = new ModelAndView();
        view.setViewName("course/course_grid");
        return view;
    }

    @RequestMapping("/choice")
    public ModelAndView goChoiceGrid(){
        ModelAndView view = new ModelAndView();
        view.setViewName("choice/choice_grid");
        return view;
    }
}
