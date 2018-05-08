package com.sam.lambda.demo;


@FunctionalInterface
interface IMath {
    int add(int x ,int y);
}


@FunctionalInterface
interface IMath2 {
    int sub(int x ,int y);
}
/**
 * @Author: huangxin
 * @Date: Created in 上午11:25 2018/5/8
 * @Description:
 */
public class TypeDemo {

    public static void main(String[] args) {

        //变量定义
        IMath lambada = (x, y) -> x + y;

        //数组里
        IMath[] lambdas = {(x, y) -> x + y};

        //强转
        Object lambda2 = (IMath)(x, y) -> x + y;

        //通过返回类型
        IMath createLambda = createLambda();

        TypeDemo demo = new TypeDemo();
        //当有二重性的时候强转即可
        demo.test((IMath2)(x, y) -> x + y);
    }

    public void test(IMath math) {

    }
    public void test(IMath2 math) {

    }

    public static IMath createLambda() {
        return (x, y) -> x + y;
    }

}
