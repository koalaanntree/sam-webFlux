package com.sam.interfaces;

import com.sam.beans.MethodInfo;
import com.sam.beans.ServerInfo; /**
 * rest请求调用
 * @Author: huangxin
 * @Date: Created in 下午3:13 2018/5/9
 * @Description:
 */
public interface RestHandler {
    /**
     * 初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest请求，返回接口
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
