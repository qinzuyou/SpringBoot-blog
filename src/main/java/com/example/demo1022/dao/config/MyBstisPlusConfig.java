package com.example.demo1022.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration

//接口扫描
@MapperScan("com.example.demo1022.dao.mapper")

@EnableTransactionManagement

public class MyBstisPlusConfig {

//    分页配置

}
