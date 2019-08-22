package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Student S WHERE S.id in (:ids)")
    void deleteStudentsByIds(@Param("ids") List<Long> ids);



}
