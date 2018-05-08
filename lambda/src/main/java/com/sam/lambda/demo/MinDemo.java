package com.sam.lambda.demo;

import java.util.stream.IntStream;

/**
 * @Author: huangxin
 * @Date: Created in 上午10:07 2018/5/8
 * @Description:
 */
public class MinDemo {

    public static void main(String[] args) {
        int[] nums = {33, 55, -55, 66, 90, -666, 90};

        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        System.out.println(min);


        //jdk8
        int min2 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min2);


    }
}
