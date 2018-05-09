package com.sam.webfluxclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名可以随意，字段需要保持一致
 * @Author: huangxin
 * @Date: Created in 上午9:44 2018/5/9
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String name;

    private int age;
}
