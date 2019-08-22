package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ChoiceVo
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/20 0020 17:03
 * @Version 1.0
 **/
public class ChoiceVo extends BaseEntityVo {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Long studentId;
    @Getter @Setter
    private Long courseId;
    @Getter @Setter
    private String studentName;
    @Getter @Setter
    private String courseName;
    @Getter @Setter
    private String year;
}
