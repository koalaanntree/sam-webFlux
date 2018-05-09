package com.sam.webflux2.handler;

import com.sam.webflux2.domain.User;
import com.sam.webflux2.repository.UserRepository;
import com.sam.webflux2.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Author: huangxin
 * @Date: Created in 下午1:41 2018/5/9
 * @Description:
 */
@Component
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 得到所有用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.repository.findAll(), User.class);

    }

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {

        //2.0.0是可以工作的，但是2.0.1 下面这个模式会报异常
//        User user = request.bodyToMono(User.class).block();
        Mono<User> user = request.bodyToMono(User.class);

        return
//                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(this.repository.saveAll(user), User.class);
                user.flatMap(u -> {
                    //校验代码需要放在这里

                    CheckUtil.checkName(u.getName());
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(this.repository.save(u), User.class);

                });

    }

    /**
     * 根据id删除用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {

        String id = request.pathVariable("id");

        return this.repository.findById(id).flatMap(user ->
                this.repository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

}
