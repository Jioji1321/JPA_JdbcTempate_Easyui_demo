package com.example.demo.service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/15 0015 16:29
 * @Version 1.0
 **/
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcOperations;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Long saveStudent(StudentVo studentVo) {
        Student student = new Student();
        BeanCopier copier = BeanCopier.create(StudentVo.class, Student.class, false); //拷贝属性，一一匹配
        copier.copy(studentVo, student, null);
        if(student.getCreator() == null){
            student.setCreator("sa");
        }
        student.setLastUpdateUser("sa");
        Student resultStudent = studentRepository.saveAndFlush(student);
        return resultStudent.getId();
    }

    public Object getStudentData(StudentVo studentVo, int currentPage, int rowsPerPage){
        StringBuilder sql = new StringBuilder("SELECT U.* FROM STUDENT U ");
        Map<String,Object> paramsMap = new HashMap<>();
        if(studentVo != null){
            sql.append("WHERE 1=1 ");
            if(studentVo.getId() != null){
                sql.append("AND U.ID = :ID ");
                paramsMap.put("ID",studentVo.getId());
            }
            if(studentVo.getStudentName() != null){
                sql.append("AND U.USERNAME LIKE :USERNAME ");
                paramsMap.put("USERNAME","%" + studentVo.getStudentName() + "%");
            }
            if(studentVo.getAge() != null){
                sql.append("AND U.AGE = :AGE ");
                paramsMap.put("AGE",studentVo.getAge());
            }
        }
        sql.append("ORDER BY U.ID");
        List list = jdbcOperations.query(sql.toString(), paramsMap, new BeanPropertyRowMapper(StudentVo.class));

        Map gridMap = new HashMap<>();
        gridMap.put("total",list.size());
        gridMap.put("rows",list);
        return gridMap;
    }

    public StudentVo findStudentById(Long id){
        Student student = studentRepository.findById(id).get();
        StudentVo studentVo = new StudentVo();
        BeanCopier copier = BeanCopier.create(Student.class, StudentVo.class, false); //拷贝属性，一一匹配
        copier.copy(student, studentVo, null);
        return studentVo;
    }

    public Object deleteStudentsByIds(String ids){
        String[] idsString = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id : idsString) {
            list.add(Long.parseLong(id));
        }
        studentRepository.deleteStudentsByIds(list);
        Map retMap = new HashMap();
        retMap.put("ids",ids.substring(0,ids.length() - 1));
        return retMap;
    }


}
