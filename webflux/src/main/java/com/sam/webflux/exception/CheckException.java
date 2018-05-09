package com.sam.webflux.exception;

import lombok.Data;

/**
 * @Author: huangxin
 * @Date: Created in 上午11:06 2018/5/9
 * @Description:
 */
@Data
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = -8538742911732249724L;

    /**
     * 出错字段名字
     */
    private String fieldName;

    /**
     * 出错字段的值
     */
    private String fieldValue;

    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckException(String fieldName, String fieldValue) {
        super();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }



}
