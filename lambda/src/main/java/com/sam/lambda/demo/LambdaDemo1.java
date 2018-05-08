package com.sam.lambda.demo;

/**
 * @Author: huangxin
 * @Date: Created in 上午10:24 2018/5/8
 * @Description:
 */
@FunctionalInterface //建议加这个注解代表函数式编程---->单一责任制（一个接口做一个事情）
interface Interface1 {
    int doubleNum(int i);

    /**
     * 默认实现方法
     * @param x
     * @param y
     * @return
     */
    default int add(int x, int y) {
        this.doubleNum(x);
        return x + y;
    }
}

@FunctionalInterface //建议加这个注解代表函数式编程---->单一责任制（一个接口做一个事情）
interface Interface2 {
    int doubleNum(int i);

    /**
     * 默认实现方法
     * @param x
     * @param y
     * @return
     */
    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface //建议加这个注解代表函数式编程---->单一责任制（一个接口做一个事情）
interface Interface3 extends Interface2,Interface1{
    /**
     * 默认实现方法
     * @param x
     * @param y
     * @return
     */
    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x,y);
    }
}

public class LambdaDemo1 {

    /**
     * 接口只有一个方法要实现才能用lambda表达式
     * @param args
     */
    public static void main(String[] args) {


        Interface1 i1 = (i) -> i * 2;

        System.out.println(i1.add(3,7));
        System.out.println(i1.doubleNum(20));
        //最常见写法
        Interface1 i2 = i -> i * 2;

        Interface1 i3 = (int i) -> i * 2;
        Interface1 i4 = (int i) -> {
            System.out.println("-----多行大括号执行代码-----");
            return i * 2;
        };


    }

}
