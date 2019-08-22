package com.example.demo.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Course
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/19 0019 16:28
 * @Version 1.0
 **/
public class CourseVo extends BaseEntityVo {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String courseName;
    @Getter
    @Setter
    private String year;
}
