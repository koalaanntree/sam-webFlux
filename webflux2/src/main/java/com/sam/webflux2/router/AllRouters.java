package com.sam.webflux2.router;

import com.sam.webflux2.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Author: huangxin
 * @Date: Created in 下午1:50 2018/5/9
 * @Description:
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return RouterFunctions.nest(
                //相当于类上面的@RequestMapping("/user")
                RequestPredicates.path("/user"),
                //下面的相当于类里面的@RequestMapping
                RouterFunctions.route(RequestPredicates.GET("/"), handler::getAllUser)
                        //创建用户
                        .andRoute(RequestPredicates.POST("/")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handler::createUser)
                        //删除用户
                        .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById)
        );
    }

}
