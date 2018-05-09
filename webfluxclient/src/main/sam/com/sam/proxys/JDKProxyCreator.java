package com.sam.proxys;

import com.sam.ApiServer;
import com.sam.beans.MethodInfo;
import com.sam.beans.ServerInfo;
import com.sam.interfaces.ProxyCreator;
import com.sam.interfaces.RestHandler;
import com.sam.resthandlers.WebClientRestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用JDK动态代理实现代理类
 *
 * @Author: huangxin
 * @Date: Created in 下午3:03 2018/5/9
 * @Description:
 */
@Slf4j
public class JDKProxyCreator implements ProxyCreator {

    @Override
    public Object createProxy(Class<?> type) {
        log.info("createProxy:" + type);

        //根据接口得到API服务器信息
        ServerInfo serverInfo = extractServerInfo(type);

        log.info("serverInfo:" + serverInfo);

        //给每一个代理类一个实现
        RestHandler handler = new WebClientRestHandler();

        //初始化服务器信息（初始化webclient）
        handler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        //根据方法和参数得到调用信息
                        MethodInfo methodInfo = extractMethodInfo(method, args);
                        log.info("methodInfo:" + methodInfo);
                        //调用rest
                        return handler.invokeRest(methodInfo);
                    }

                    /**
                     * 根据方法定义和调用参数得到调用的相关信息
                     * @param method
                     * @param args
                     * @return
                     */
                    private MethodInfo extractMethodInfo(Method method, Object[] args) {
                        MethodInfo methodInfo = new MethodInfo();
                        extractUrlAndMethod(method, methodInfo);
                        extractRequestParamAndBody(method, args, methodInfo);


                        return methodInfo;
                    }

                    /**
                     * 得到请求的param和body
                     * @param method
                     * @param args
                     * @param methodInfo
                     */
                    private void extractRequestParamAndBody(Method method, Object[] args, MethodInfo methodInfo) {
                        //得到调用的参数和body
                        Parameter[] parameters = method.getParameters();

                        //参数和值对应的map
                        Map<String, Object> params = new LinkedHashMap<>();
                        methodInfo.setParams(params);

                        for (int i = 0; i < parameters.length; i++) {
                            //是否带@PathVariable
                            PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);

                            if (annoPath != null) {
                                params.put(annoPath.value(), args[i]);
                            }

                            //是否带了requestBody
                            RequestBody annoBody = parameters[i].getAnnotation(RequestBody.class);

                            if (annoBody != null) {
                                methodInfo.setBody((Mono<?>) args[i]);
                            }
                        }
                    }

                    /**
                     * 得到请求的URL和方法
                     * @param method
                     * @param methodInfo
                     */
                    private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {
                        //得到请求URL和请求方法
                        Annotation[] annotations = method.getAnnotations();

                        for (Annotation annotation : annotations) {
                            //GET
                            if (annotation instanceof GetMapping) {
                                GetMapping a = (GetMapping) annotation;

                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.GET);
                            }
                            //POST
                            else if (annotation instanceof PostMapping) {
                                PostMapping a = (PostMapping) annotation;

                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.POST);
                            }
                            //DELETE
                            else if (annotation instanceof DeleteMapping) {
                                DeleteMapping a = (DeleteMapping) annotation;

                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.DELETE);
                            }
                        }
                    }
                });
    }


    /**
     * 提取服务器信息
     *
     * @param type
     * @return
     */
    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();

        ApiServer anno = type.getAnnotation(ApiServer.class);

        serverInfo.setUrl(anno.value());
        return serverInfo;
    }
}
