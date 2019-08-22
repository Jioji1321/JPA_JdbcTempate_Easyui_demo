package com.example.demo.dao;

import com.example.demo.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Choice C WHERE C.id IN (:ids)")
    void deleteStudentsByIds(@Param("ids") List<Long> ids);
}
