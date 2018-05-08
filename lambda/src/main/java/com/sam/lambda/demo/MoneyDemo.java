package com.sam.lambda.demo;

import java.text.DecimalFormat;
import java.util.function.Function;


class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat) {
        System.out.println("我的存款：" + moneyFormat.apply(this.money));

    }
}

/**
 * @Author: huangxin
 * @Date: Created in 上午10:37 2018/5/8
 * @Description:
 */
public class MoneyDemo {

    public static void main(String[] args) {
        MyMoney me = new MyMoney(9999999);

        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###").format(i);
        //函数接口链式操作
        me.printMoney(moneyFormat.andThen(s -> "人民币 " + s));
    }
}
