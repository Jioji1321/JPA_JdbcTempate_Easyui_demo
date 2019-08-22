package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.TemporalType;

/**
 * @ClassName BaseEntity
 * @Description TODO  公用实体类
 * @Author jioji
 * @Date 2019/08/17 0017 14:17
 * @Version 1.0
 **/

@MappedSuperclass
/**
 * 使用环境：
 * 1.@MappedSuperclass注解使用在父类上面，是用来标识父类的
 * 2.@MappedSuperclass标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 * 3.@MappedSuperclass标识之后不能再有@Entity或@Table注解
 */
@EntityListeners(AuditingEntityListener.class) //是用于监听实体类添加、修改或者删除操作的。
public class BaseEntity implements java.io.Serializable {

    @Column(name = "CREATOR", nullable = false, updatable = false)
    @CreatedBy
    @Getter
    @Setter
    private String creator;
    @Column(name = "CREATE_TIME", nullable = false, updatable = false)
    @CreatedDate
    @Getter
    @Setter
    private String createTime;
    @Column(name = "LAST_UPDATE_USER")
    @LastModifiedBy
    @Getter
    @Setter
    private String lastUpdateUser;
    @Column(name = "UPDATE_TIME")
    @LastModifiedDate
    @Getter
    @Setter
    private String updateTime;
}
