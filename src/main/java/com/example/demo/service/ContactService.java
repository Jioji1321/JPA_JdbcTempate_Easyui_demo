package com.example.demo.service;

import com.example.demo.dao.ContactRepository;
import com.example.demo.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ContectService
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/15 0015 16:30
 * @Version 1.0
 **/
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public void save(){

    }
}
