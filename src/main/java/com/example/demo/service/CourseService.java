package com.example.demo.service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.entity.ComboBox;
import com.example.demo.entity.Course;
import com.example.demo.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseService
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/20 0020 9:49
 * @Version 1.0
 **/
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private NamedParameterJdbcTemplate jdbcOperations;

    public Object getCourseData(int currentPage, int rowsPerPage, CourseVo courseVo){
        StringBuilder sql = new StringBuilder("select a.* from course a ");
        Map<String,Object> paramsMap = new HashMap<>();
        if(courseVo != null){
            sql.append("where 1 = 1 ");
            if(courseVo.getId() != null){
                sql.append("and id = :id ");
                paramsMap.put("id",courseVo.getId());
            }
            if(courseVo.getCourseName() != null){
                sql.append("and course_name like :courseName ");
                paramsMap.put("courseName","%" + courseVo.getCourseName() + "%");
            }
            if (courseVo.getYear() != null){
                sql.append("and year = :year ");
                paramsMap.put("year",courseVo.getYear());
            }
        }
        sql.append("order by a.id");
        List list = jdbcOperations.query(sql.toString(), paramsMap, new BeanPropertyRowMapper(CourseVo.class));

        Map<String,Object> retMap = new HashMap<>();
        retMap.put("total",list.size());
        retMap.put("rows",list);
        return retMap;
    }

    public Long saveCourse(CourseVo courseVo) {
        Course course = new Course();
        BeanCopier copier = BeanCopier.create(CourseVo.class, Course.class, false); //拷贝属性，一一匹配
        copier.copy(courseVo, course, null);
        if(course.getCreator() == null){
            course.setCreator("sa");
        }
        course.setLastUpdateUser("sa");
        Course resultCourse = courseRepository.saveAndFlush(course);
        return resultCourse.getId();
    }

    public CourseVo findCourseById(Long id){
        Course course = courseRepository.findById(id).get();
        CourseVo courseVo = new CourseVo();
        BeanCopier copier = BeanCopier.create(Course.class, CourseVo.class, false); //拷贝属性，一一匹配
        copier.copy(course, courseVo, null);
        return courseVo;
    }

    public Object deleteCoursesByIds(String ids){
        String[] idsString = ids.split(",");
        List<Long> list = new ArrayList<>();
        for (String id : idsString) {
            list.add(Long.parseLong(id));
        }
        courseRepository.deleteCoursesByIds(list);
        Map retMap = new HashMap();
        retMap.put("ids",ids.substring(0,ids.length() - 1));
        return retMap;
    }

    /*public List getCourseNameComboBoxData(String keyword, Long id){
        Map<String,Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT C1.ID AS id, C2.COURSE_NAME AS text FROM CHOICE C1 ")
                .append("LEFT JOIN COURSE C2 ON C2.ID = C1.COURSE_ID ")
                .append("WHERE C1.ID = :ID ");

        if(keyword != null && !"".equals(keyword)){
            String keyWord = "%" + keyword.trim() + "%";
            sql.append("AND C2.COURSE_NAME LIKE :COURSE_NAME ");
            params.put("COURSE_NAME",keyWord);
        }

        sql.append("ORDER BY C1.ID");
        params.put("ID",id);

        List list = jdbcOperations.query(sql.toString(), params, new BeanPropertyRowMapper(ComboBox.class));
        return list;
    }*/

    public List getCourseNameComboBoxData(){
        Map<String,Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT C1.ID AS id, C1.COURSE_NAME AS text FROM COURSE C1 ")
                .append("ORDER BY C1.ID");
        List list = jdbcOperations.query(sql.toString(), new BeanPropertyRowMapper(ComboBox.class));
        return list;
    }
}
