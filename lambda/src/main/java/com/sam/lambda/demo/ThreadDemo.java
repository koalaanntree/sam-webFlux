package com.sam.lambda.demo;

/**
 * @Author: huangxin
 * @Date: Created in 上午10:13 2018/5/8
 * @Description:
 */
public class ThreadDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("OK");

            }
        }).start();

        //jdk8 lambda
        new Thread(() -> System.out.println("OK")).start();


    }
}
