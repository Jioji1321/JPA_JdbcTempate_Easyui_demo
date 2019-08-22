package com.example.demo.vo;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName StudentVo
 * @Description TODO  用户类
 * @Author jioji
 * @Date 2019/08/15 0015 16:02
 * @Version 1.0
 **/
public class StudentVo extends BaseEntityVo {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String studentName;
    @Getter
    @Setter
    private String age;
}
