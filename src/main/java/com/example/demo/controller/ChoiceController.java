package com.example.demo.controller;

import com.example.demo.service.ChoiceService;
import com.example.demo.vo.ChoiceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

/**
 * @ClassName ChoiceController
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/20 0020 15:55
 * @Version 1.0
 **/
@Controller
@RequestMapping("/choice")
public class ChoiceController {
    @Autowired
    private ChoiceService choiceService;

    @ResponseBody
    @RequestMapping("/selectChoiceById")
    public ModelAndView selectChoiceById(String id) {
        ModelAndView view = new ModelAndView();
        ChoiceVo choiceVo = choiceService.findChoiceById(Long.parseLong(id));
        view.addObject("choice", choiceVo);
        view.setViewName("choice/choice_edit");
        return view;
    }

    @ResponseBody
    @RequestMapping("/saveChoice")
    public Object saveChoice(ChoiceVo choiceVo){
        return choiceService.saveChoice(choiceVo);
    }

    @ResponseBody
    @RequestMapping("/getChoiceData")
    public Object getChoiceData(@RequestParam(value = "page", defaultValue = "1") String pageNo,
                                @RequestParam(value = "rows", defaultValue = "20") String pageSize,
                                String studentName, String courseName,String year){

        ChoiceVo choiceVo = new ChoiceVo();
        if(studentName != null && !"".equals(studentName)){
            choiceVo.setStudentName(studentName);
        }
        if(courseName != null && !"".equals(courseName)){
            choiceVo.setCourseName(courseName);
        }
        if(year != null && !"".equals(year)){
            choiceVo.setYear(year);
        }
        return choiceService.getChoiceData(Integer.parseInt(pageNo),Integer.parseInt(pageSize),choiceVo);
    }

//    @ResponseBody
//    @RequestMapping("/getCourseDataByStudentId")
//    public Object getCourseDataByStudent(Long id){
//        return choiceService.getCourseDataByStudentId(id);
//    }

    @ResponseBody
    @RequestMapping("/deleteChoicesByIds")
    @Transactional(rollbackOn = {Exception.class})
    public Object deleteChoicesByIds(@RequestParam(value = "ids") String ids){
        return choiceService.deleteChoicesByIds(ids);
    }

}
