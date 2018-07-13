package com.zz.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    question:

    1. Interceptor return false 会怎么样. 我们应该返回一个json给前端，是否需要 throw exception达到分辨 false原因？
    2. creator invites others, If the person should be friend of creator. Need API --> get all friends By uid.


 */

@SpringBootApplication
public class BootStrap {
    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
    }
}
