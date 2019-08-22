package com.example.demo.service;

import com.example.demo.dao.PhoneRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName PhoneService
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/15 0015 16:30
 * @Version 1.0
 **/
@Service
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Phone> findAll(){
        return phoneRepository.findAll();
    }


    public Phone testSavePhone(){
        Phone phone = new Phone();
        phone.setPhoneName("book");
        phone.setPrice(10.00f);
        phone.setCreator("sa");
        phone.setLastUpdateUser("sa");
        phone.setCreateTime("2019年8月19日17:49:32");
        phone.setUpdateTime("2019年8月19日17:49:52");
        Student student = studentRepository.findAll().get(0);
        phone.setStudent(student);

        Phone phoneResult = phoneRepository.saveAndFlush(phone);
        return phoneResult;
    }
}
