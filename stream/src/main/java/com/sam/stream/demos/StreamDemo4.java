package com.sam.stream.demos;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: huangxin
 * @Date: Created in 下午1:59 2018/5/8
 * @Description:
 */
public class StreamDemo4 {
    public static void main(String[] args) {

        String str = "my name is 007";

        //使用并行流
        str.chars().parallel().forEach(i -> System.out.println((char) i));
        System.out.println();
        //使用forEachOrdered保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.println((char) i));


        //收集到list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);


        //使用reduce拼接字符串
        Optional<String> letters = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(letters.orElse(""));

        //带初始化值的reduce
        String reduce = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce);


        //计算所有单词的总长度
        Integer length = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(length);

        //max的使用
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

        //使用findFirst短路操作
        OptionalInt findFirst = new Random().ints().findFirst();
        System.out.println(findFirst.getAsInt());

    }
}
