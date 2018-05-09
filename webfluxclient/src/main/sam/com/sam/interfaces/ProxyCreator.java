package com.sam.interfaces;

/**
 * 创建代理类接口
 * @Author: huangxin
 * @Date: Created in 下午3:01 2018/5/9
 * @Description:
 */
public interface ProxyCreator {

    /**
     * 创建代理类
     * @param type
     * @return
     */
    Object createProxy(Class<?> type) ;
}
