package com.sam.webflux.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @Author: huangxin
 * @Date: Created in 上午9:44 2018/5/9
 * @Description:
 */
@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 10,max = 100)
    private int age;
}
