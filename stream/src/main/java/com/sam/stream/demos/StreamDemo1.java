package com.sam.stream.demos;

import java.util.stream.IntStream;

/**
 * @Author: huangxin
 * @Date: Created in 下午1:24 2018/5/8
 * @Description:
 */
public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        //外部迭代
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("结果为" + sum);

        //使用内部迭代
        //map就是中间操作（返回stream的操作）
        //sum就是终止操作
        int sum1 = IntStream.of(nums).map(StreamDemo1::doubleNum).parallel().sum();
        System.out.println("结果为" + sum1);

        System.out.println("惰性求值就是终止操作没有调用情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }


    public static int doubleNum(int i) {

        System.out.println("执行了乘以2");
        return i * 2;

    }

}
