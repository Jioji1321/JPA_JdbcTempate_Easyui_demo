package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ChoiceRepository;
import com.example.demo.entity.Choice;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.vo.ChoiceVo;
import com.example.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChoiceService
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/20 0020 16:48
 * @Version 1.0
 **/
@Service
public class ChoiceService {

    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object saveChoice(ChoiceVo choiceVo){
        Student student = new Student();
        student.setId(choiceVo.getStudentId());
        Course course = new Course();
        course.setId(choiceVo.getCourseId());

        Choice choice = new Choice();
        choice.setId(choiceVo.getId());
        choice.setCourse(course);
        choice.setStudent(student);

        choice.setCreator("sa");
        choice.setLastUpdateUser("sa");

        choice = choiceRepository.saveAndFlush(choice);

        Map<String,Object> retMap = new HashMap<>();
        retMap.put("status","200");
        retMap.put("id",choice.getId());
        return retMap;
    }

    public Object getChoiceData(int currentPage, int rowsPerPage, ChoiceVo choiceVo){
        Map<String,Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT C1.ID, C2.COURSE_NAME,C2.YEAR,S.STUDENT_NAME FROM CHOICE C1 ")
                .append("LEFT JOIN COURSE C2 ON C1.COURSE_ID = C2.ID ")
                .append("LEFT JOIN STUDENT S ON C1.STUDENT_ID = S.ID ");
        if(choiceVo != null){
            sql.append("WHERE 1 = 1 ");
            if(choiceVo.getStudentName() != null){
                sql.append("AND STUDENT_NAME LIKE :STUDENT_NAME ");
                params.put("STUDENT_NAME","%" + choiceVo.getStudentName() + "%");
            }
            if(choiceVo.getCourseName() != null){
                sql.append("AND COURSE_NAME LIKE :COURSE_NAME ");
                params.put("COURSE_NAME","%" + choiceVo.getCourseName() + "%");
            }
            if(choiceVo.getYear() != null){
                sql.append("AND YEAR = :YEAR ");
                params.put("YEAR",choiceVo.getYear());
            }
        }

        sql.append("ORDER BY C1.ID");

        List list = jdbcOperations.query(sql.toString(), params, new BeanPropertyRowMapper(ChoiceVo.class));

        Map<String,Object> retMap = new HashMap<>();
        retMap.put("total",retMap.size());
        retMap.put("rows",list);
        return retMap;
    }

//    public Object getCourseDataByStudentId(Long id){
//        StringBuilder sql = new StringBuilder("SELECT C1.ID AS CHOICE_ID ,C2.* FROM CHOICE C1 ")
//                .append("LEFT JOIN COURSE C2 ON C1.COURSE_ID = C2.ID ")
//                .append("WHERE C1.STUDENT_ID = ?");
//
//        Object[] params = new Object[]{id};
//
////        Map<String,Object> params = new HashMap<>();
////        params.put("ID",id);
//
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(),params);
//
//        String courseId = "";
//        String courseName = "";
//        StringBuilder resultJsonStr = new StringBuilder("[");
//        for(Map map :list){
//            resultJsonStr.append("{");
//            resultJsonStr.append("\"id\":");
//            courseId = map.get("ID")+"";
//            resultJsonStr.append(courseId);
//            resultJsonStr.append(",\"courseName\":");
//            courseName = map.get("COURSE_NAME")+"";
//            resultJsonStr.append("\"" + courseName + "\"");
//            resultJsonStr.append("},");
//        }
//        resultJsonStr.substring(0, resultJsonStr.length() - 1);
//        resultJsonStr.append("]");
//
//        System.out.println(resultJsonStr.toString());
//
//        JSONObject object = JSONObject.parseObject(resultJsonStr.toString());
//
//        return object;
//
////        Map<String,Object> retMap = new HashMap<>();
////        retMap.put("total",list.size());
////        retMap.put("row",list);
////        return retMap;
//    }

    public Object deleteChoicesByIds(String ids){
        String[] idsString = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id : idsString) {
            list.add(Long.parseLong(id));
        }
        choiceRepository.deleteStudentsByIds(list);
        Map retMap = new HashMap();
        retMap.put("ids",ids.substring(0,ids.length() - 1));
        return retMap;
    }

    public ChoiceVo findChoiceById(Long id){
        Choice choice = choiceRepository.findById(id).get();
        ChoiceVo choiceVo = new ChoiceVo();
        choiceVo.setId(choice.getId());
        choiceVo.setStudentId(choice.getStudent().getId());
        choiceVo.setCourseId(choice.getCourse().getId());
        choiceVo.setStudentName(choice.getStudent().getStudentName());
        choiceVo.setCourseName(choice.getCourse().getCourseName());
        return choiceVo;
    }
}
