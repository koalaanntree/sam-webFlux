package com.sam.lambda.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;


class Dog {
    private String name = "呵呵哒";

    /**
     * 默认10斤
     */
    private int food = 10;

    public Dog() {
    }

    /**
     * 带参数的构造函数
     * @param name
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * 狗叫，静态方法
     * @param dog
     */
    public static void bark(Dog dog) {
        System.out.println(dog +"叫了");
    }

    /**
     * 吃狗粮
     * JDK默认会把当前实例传入到非静态方法中，参数名为this,位置是第一个;
     * @param num
     * @return 还剩下多少斤
     */
    public int eat(//Dog this,
                   int num) {
        System.out.println("吃了" + num + "斤");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
/**
 * @Author: huangxin
 * @Date: Created in 上午10:56 2018/5/8
 * @Description:
 */
public class MethodRefrenceDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();

        dog.eat(3);

//        Consumer<String> consumer = s -> System.out.println(s);
        //方法引用
        Consumer<String> consumer = System.out::println;

        consumer.accept("接收的数据");

        //静态方法的方法引用
        Consumer<Dog> consumer1 = Dog::bark;
        consumer1.accept(dog);

        //非静态方法，使用对象实例来引用
//        Function<Integer, Integer> function = dog::eat;
//        UnaryOperator<Integer> function = dog::eat;
        IntUnaryOperator function = dog::eat;
        System.out.println("还剩下" +function.applyAsInt(2)+"斤");


        //使用类名来方法引用
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println("还剩下" +eatFunction.apply(dog,2)+"斤");

        //构造函数的方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象"+supplier.get());

        //带参数的构造函数的方法引用
        Function<String, Dog> function1 = Dog::new;
        System.out.println("创建了新对象"+function1.apply("旺财"));

        List<String> list = new ArrayList<>();
        test(list);

        System.err.println(list);

    }


    private static void test(List<String> list) {
        list = null;
    }
}
