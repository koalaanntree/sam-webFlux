package com.sam.lambda.demo;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @Author: huangxin
 * @Date: Created in 上午10:51 2018/5/8
 * @Description:
 */
public class FunctionDemo {

    public static void main(String[] args) {
        //断言函数接口
//        Predicate<Integer> predicate = i -> i > 0;
        IntPredicate predicate = i -> i > 0;
        System.out.println(predicate.test(1));

        //IntConsumer
        //消费函数接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");

    }

}
