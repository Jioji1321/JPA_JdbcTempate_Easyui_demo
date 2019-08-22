package com.example.demo.entity;

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

@Entity
@Table(name = "CONTACT")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long contactId;

    @Column(name = "PHONE_NUMBER")
    @Getter
    @Setter
    private String phoneNumber;
    @Column(name = "EMAIL")
    @Getter
    @Setter
    private String email;

//    @OneToOne
//    @JoinColumn(name = "USER_ID", unique = true, referencedColumnName = "ID")
//    @JsonIgnore
//    private Student student;
}
