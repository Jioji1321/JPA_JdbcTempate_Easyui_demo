package com.example.demo.controller;

import com.example.demo.service.ContactService;
import com.example.demo.service.PhoneService;
import com.example.demo.service.StudentService;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/15 0015 16:31
 * @Version 1.0
 **/
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private PhoneService phoneService;

    @RequestMapping({"/grid"})
    public ModelAndView goGridPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("student/student_grid");
        return view;
    }

    @RequestMapping({"/edit"})
    public ModelAndView goEditPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("student/student_edit");
        return view;
    }

    @RequestMapping({"/view"})
    public ModelAndView goViewPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("student/student_view");
        return view;
    }

    @ResponseBody
    @RequestMapping("/getStudentData")
    public Object getStudentData(@RequestParam(value = "page", defaultValue = "1") String pageNo,
                              @RequestParam(value = "rows", defaultValue = "20") String pageSize,
                              String searchId, String searchStudentName, String searchAge) {
        StudentVo studentVo = new StudentVo();
        if (searchId != null && !"".equals(searchId)){
            studentVo.setId(Long.parseLong(searchId));
        }
        if (searchStudentName != null && !"".equals(searchStudentName)){
            studentVo.setStudentName(searchStudentName);
        }
        if (searchAge != null && !"".equals(searchAge)){
            studentVo.setAge(searchAge);
        }
        return studentService.getStudentData(studentVo, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
    }

    @ResponseBody
    @RequestMapping("/saveStudent")
    public Map saveStudent(StudentVo studentvo) {
        Map retMap = new HashMap();
        Long id = studentService.saveStudent(studentvo);
        if (id != 0) {
            retMap.put("status", "200");
            retMap.put("id", id);
        }
        return retMap;
    }

    @RequestMapping("/showStudentDetail")
    public ModelAndView showStudentDetail(String id) {
        if(id == null){
            return null;
        }
        ModelAndView view = new ModelAndView();
        StudentVo studentvo = studentService.findStudentById(Long.parseLong(id));
        view.addObject("student", studentvo);
        view.setViewName("student/student_view");
        return view;
    }

    @RequestMapping("/addNewStudent")
    public ModelAndView addNewStudent() {
        ModelAndView view = new ModelAndView();
        view.setViewName("student/student_edit");
        return view;
    }

    @ResponseBody
    @RequestMapping("/deleteStudentsByIds")
    @Transactional(rollbackOn = {Exception.class})
    public Object deleteStudentsByIds(@RequestParam(value = "ids") String ids) {
        return studentService.deleteStudentsByIds(ids);
    }

    @ResponseBody
    @RequestMapping("/selectStudentById")
    public ModelAndView selectStudentById(String id) {
        if(id == null){
            return null;
        }
        ModelAndView view = new ModelAndView();
        StudentVo studentVo = studentService.findStudentById(Long.parseLong(id));
        view.addObject("student", studentVo);
        view.setViewName("student/student_edit");
        return view;
    }

}
