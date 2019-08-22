package com.example.demo.dao;

import com.example.demo.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhoneRepository extends JpaRepository<Phone,Long> {
}
