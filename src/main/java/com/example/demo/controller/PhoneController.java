package com.example.demo.controller;

import com.example.demo.entity.Phone;
import com.example.demo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/19 0019 17:45
 * @Version 1.0
 **/
@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping("/testSavePhone")
    @ResponseBody
    public Phone testSavePhone(){
        return phoneService.testSavePhone();
    }
}
