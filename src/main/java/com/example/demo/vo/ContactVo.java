package com.example.demo.vo;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName Contact
 * @Description TODO 联系方式类
 * @Author jioji
 * @Date 2019/08/15 0015 16:02
 * @Version 1.0
 **/
public class ContactVo extends BaseEntityVo {
    @Getter
    @Setter
    private Long contactId;
    @Getter
    @Setter
    private String phoneNumber;
    @Getter
    @Setter
    private String email;

}
