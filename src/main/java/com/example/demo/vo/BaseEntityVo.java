package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @ClassName BaseEntity
 * @Description TODO  公用实体类
 * @Author jioji
 * @Date 2019/08/17 0017 14:17
 * @Version 1.0
 **/
public class BaseEntityVo implements java.io.Serializable {
    @Getter
    @Setter
    private String createBy;
    @Getter
    @Setter
    private String createDate;
    @Getter
    @Setter
    private String lastModifiedBy;
    @Getter
    @Setter
    private String lastModifiedDate;
}
