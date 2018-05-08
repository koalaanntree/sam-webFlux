package com.sam.lambda.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 *
 * @Author: huangxin
 * @Date: Created in 上午11:34 2018/5/8
 * @Description:
 */
public class VarDemo {

    public static void main(String[] args) {
        final String str = "我们的时间";

        List<String> list = new ArrayList<>();

        //匿名类里面引用变量需要final
        Consumer<String> consumer = s -> System.out.println(s + list);
        consumer.accept("1211");


    }

}
