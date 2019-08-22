package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Course
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/19 0019 16:28
 * @Version 1.0
 **/

@Entity
@Table(name = "COURSE")
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID", nullable = false, unique = true)
    @Getter
    @Setter
    private Long id;
    @Column(name = "COURSE_NAME")
    @Getter
    @Setter
    private String courseName;
    @Getter
    @Setter
    @Column(name = "YEAR")
    private String year;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "courses")
//    Set<Student> students = new HashSet<Student>(0);

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Choice> choices = new HashSet<>(0);
}
