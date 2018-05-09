package com.sam.webflux2.util;

import com.sam.webflux2.exception.CheckException;

import java.util.stream.Stream;

/**
 * @Author: huangxin
 * @Date: Created in 上午11:05 2018/5/9
 * @Description:
 */
public class CheckUtil {

    private static final String[] INVALID_NAMES={"admin","管理员"};

    /**
     * 校验名字，不成功抛出校验异常
     * @param value
     */
    public static void checkName(String value) {

        Stream.of(INVALID_NAMES).filter(name->name.equalsIgnoreCase(value))
                .findAny().ifPresent(name->{
            throw new CheckException("name", value);
        });

    }
}
