package com.sam.webflux2.repository;

import com.sam.webflux2.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @Author: huangxin
 * @Date: Created in 上午9:45 2018/5/9
 * @Description:
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);


    @Query("{'age':{'$gte':20,'$lte':30}}")
    Flux<User> oldUser();
}
