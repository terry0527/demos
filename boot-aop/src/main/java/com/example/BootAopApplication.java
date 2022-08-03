package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ① 服务端提供获取 Token 的接口，该 Token 可以是一个序列号，也可以是一个分布式 ID 或者 UUID 串。
 * ② 客户端调用接口获取 Token，这时候服务端会生成一个 Token 串。
 * ③ 然后将该串存入 Redis 数据库中，以该 Token 作为 Redis 的键（注意设置过期时间）。
 * ④ 将 Token 返回到客户端，客户端拿到后应存到表单隐藏域中。
 * ⑤ 客户端在执行提交表单时，把 Token 存入到 Headers 中，执行业务请求带上该 Headers。
 * ⑥ 服务端接收到请求后从 Headers 中拿到 Token，然后根据 Token 到 Redis 中查找该 key 是否存在。
 * ⑦ 服务端根据 Redis 中是否存该 key 进行判断，如果存在就将该 key 删除，然后正常执行业务逻辑。如果不存在就抛异常，返回重复提交的错误信息。
 */
@SpringBootApplication
public class BootAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAopApplication.class, args);
    }

}
