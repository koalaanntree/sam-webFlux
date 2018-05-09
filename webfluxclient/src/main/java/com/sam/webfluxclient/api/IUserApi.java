package com.sam.webfluxclient.api;

import com.sam.webfluxclient.anno.ApiServer;
import com.sam.webfluxclient.domain.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:20 2018/5/9
 * @Description:
 */
@ApiServer("http://localhost:8080/user")
public interface IUserApi {

    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id")String id);

    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id")String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);
}
