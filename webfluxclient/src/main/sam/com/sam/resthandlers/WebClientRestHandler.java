package com.sam.resthandlers;

import com.sam.beans.MethodInfo;
import com.sam.beans.ServerInfo;
import com.sam.interfaces.RestHandler;

/**
 * @Author: huangxin
 * @Date: Created in 下午3:40 2018/5/9
 * @Description:
 */
public class WebClientRestHandler implements RestHandler {
    @Override
    public void init(ServerInfo serverInfo) {

    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        return null;
    }
}
