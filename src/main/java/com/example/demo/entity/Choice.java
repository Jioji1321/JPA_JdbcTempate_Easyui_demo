package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @ClassName Choice
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/20 0020 16:54
 * @Version 1.0
 **/
@Entity
@Table(name = "CHOICE")
public class Choice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID",referencedColumnName = "ID")
    @Getter @Setter
    private Student student;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID",referencedColumnName = "ID")
    @Getter @Setter
    private Course course;
}
