package com.example.demo.vo;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName Product
 * @Description TODO  产品类
 * @Author jioji
 * @Date 2019/08/15 0015 16:10
 * @Version 1.0
 **/
public class ProductVo extends BaseEntityVo {
    @Getter
    @Setter
    private Long productId;
    @Getter
    @Setter
    private String productName;
    @Getter
    @Setter
    private Float price;
}
