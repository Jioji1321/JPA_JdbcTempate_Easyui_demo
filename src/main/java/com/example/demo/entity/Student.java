package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Student
 * @Description TODO  用户类
 * @Author jioji
 * @Date 2019/08/15 0015 16:02
 * @Version 1.0
 **/

@Entity
@Table(name = "STUDENT")
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long id;

    @Column(name = "STUDENT_NAME")
    @Getter
    @Setter
    private String studentName;
    @Getter
    @Setter
    @Column(name = "AGE")
    private String age;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "student")
//    //@JsonIgnoreProperties({"contact"})
//    @JsonIgnore
//    private Contact contact;

//    // 一对多
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "student", fetch = FetchType.LAZY)
//    private Set<Phone> phones = new HashSet<Phone>(0);

    //@ManyToMany注释表示Teacher是多对多关系的一端。
    //@JoinTable描述了多对多关系的数据表关系。name属性指定中间表名称，joinColumns定义中间表与student表的外键关系。
    //中间表students_courses的course_id列是course表的主键列对应的外键列，inverseJoinColumns属性定义了中间表与另外一端(student)的外键关系。
//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    @JoinTable(name = "students_courses",
//            joinColumns = { @JoinColumn(name = "course_id", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id") })
//    @Getter
//    @Setter
//    private Set<Course> courses = new HashSet<Course>(0);

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Choice> choices = new HashSet<>(0);
}
