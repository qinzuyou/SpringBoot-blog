package com.example.demo1022;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.demo1022.user.mapper")
@MapperScan("com.example.demo1022.article.mapper")
@MapperScan("com.example.demo1022.articleType.mapper")
@MapperScan("com.example.demo1022.comment.mapper")
@MapperScan("com.example.demo1022.label.mapper")



@SpringBootApplication
//@ComponentScan(value = {"com.example.demo1022","com.example.demo1022.dao"})

public class Demo1022Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1022Application.class, args);
    }

}
