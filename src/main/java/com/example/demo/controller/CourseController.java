package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import com.example.demo.vo.CourseVo;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/19 0019 16:42
 * @Version 1.0
 **/
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping({"/grid"})
    public ModelAndView goGridPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("course/course_grid");
        return view;
    }

    @RequestMapping({"/edit"})
    public ModelAndView goEditPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("course/course_edit");
        return view;
    }

    @RequestMapping({"/view"})
    public ModelAndView goViewPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("course/course_view");
        return view;
    }

    @ResponseBody
    @RequestMapping("/getCourseData")
    public Object getCourseData(@RequestParam(value = "page", defaultValue = "1") String pageNo,
                                @RequestParam(value = "rows", defaultValue = "20") String pageSize,
                                String id, String courseName, String year){
        CourseVo courseVo = new CourseVo();
        if(id != null && !"".equals(id)){
            courseVo.setId(Long.parseLong(id));
        }
        if(courseName != null && !"".equals(courseName)){
            courseVo.setCourseName(courseName);
        }
        if(year != null && !"".equals(year)){
            courseVo.setYear(year);
        }
        return courseService.getCourseData(Integer.parseInt(pageNo),Integer.parseInt(pageSize),courseVo);
    }

    @RequestMapping("/showCourseDetail")
    public ModelAndView showCourseDetail(String id) {
        if(id == null){
            return null;
        }
        ModelAndView view = new ModelAndView();
        CourseVo courseVo = courseService.findCourseById(Long.parseLong(id));
        view.addObject("course", courseVo);
        view.setViewName("course/course_view");
        return view;
    }

    @RequestMapping("/addCourse")
    public ModelAndView addCourse() {
        ModelAndView view = new ModelAndView();
        view.addObject("id",1);
        view.setViewName("course/course_edit");
        return view;
    }
    @ResponseBody
    @RequestMapping("/deleteCoursesByIds")
    @Transactional(rollbackOn = {Exception.class})
    public Object deleteCoursesByIds(@RequestParam(value = "ids") String ids) {
        return courseService.deleteCoursesByIds(ids);
    }

    @ResponseBody
    @RequestMapping("/saveCourse")
    public Map saveCourse(CourseVo courseVo) {
        Map retMap = new HashMap();
        Long id = courseService.saveCourse(courseVo);
        if (id != 0) {
            retMap.put("status", "200");
            retMap.put("id", id);
        }
        return retMap;
    }

    @ResponseBody
    @RequestMapping("/selectCourseById")
    public ModelAndView selectCourseById(String id) {
        if(id == null){
            return null;
        }
        ModelAndView view = new ModelAndView();
        CourseVo courseVo = courseService.findCourseById(Long.parseLong(id));
        view.addObject("course", courseVo);
        view.setViewName("course/course_edit");
        return view;
    }

    @ResponseBody
    @RequestMapping("/selectCourseByStudentId")
    public ModelAndView selectCourseByStudentId(String id) {
        if(id == null){
            return null;
        }
        ModelAndView view = new ModelAndView();
        CourseVo courseVo = courseService.findCourseById(Long.parseLong(id));
        view.addObject("course", courseVo);
        return view;
    }

    /*@ResponseBody
    @RequestMapping("/getCourseNameComboBoxData")
    public List getCourseNameComboBoxData(@RequestParam(value = "q", defaultValue = "") String q, String id){
        return courseService.getCourseNameComboBoxData(q,Long.parseLong(id));
    }*/

    @ResponseBody
    @RequestMapping("/getCourseNameComboBoxData")
    public List getCourseNameComboBoxData(){
        return courseService.getCourseNameComboBoxData();
    }

}
