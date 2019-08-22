package com.example.demo.dao;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Course C WHERE C.id in (:ids)")
    void deleteCoursesByIds(@Param("ids") List<Long> ids);
}
