package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName Product
 * @Description TODO  产品（手机）类
 * @Author jioji
 * @Date 2019/08/15 0015 16:10
 * @Version 1.0
 **/


@Entity
@ToString
@Table(name = "PHONE")
public class Phone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long phoneId;

    @Column(name = "PHONE_NAME")
    @Getter
    @Setter
    private String phoneName;
    @Column(name = "PRICE")
    @Getter
    @Setter
    private Float price;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    @Getter
    @Setter
    private Student student;
}
