package com.sam.webfluxclient.controller;

import com.sam.webfluxclient.api.IUserApi;
import com.sam.webfluxclient.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:28 2018/5/9
 * @Description:
 */
@RestController
public class TestController {

    //直接注入定义的接口
    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test() {
        //测试信息提取
        //不订阅不会实际发出请求，但会进入我们的代理类
        userApi.getAllUser();
        userApi.getUserById("1111111");
        userApi.deleteUserById("2222222");
        userApi.createUser(Mono.just(User.builder().name("sam").age(26).build()));


        //直接调用 实现调用rest接口的效果
//        Flux<User> users = userApi.getAllUser();
//        users.subscribe(System.out::println);
    }

}
