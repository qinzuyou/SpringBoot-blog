package com.example.demo1022.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 解决全局跨域
 * @Author: 魏一鹤
 * @Date: 2022-11-30 22:44
 **/

@Configuration
public class CorsMapping implements WebMvcConfigurer {

    @Override
    /**
     * 重新跨域支持方法
     * CorsRegistry  开启跨域注册
     */
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping 添加可跨域的请求地址
        registry.addMapping("/**")
                //设置跨域 域名权限 规定由某一个指定的域名+端口能访问跨域项目
//                .allowedOrigins("*")
                .allowedOrigins("http://localhost:4000")


                //是否开启cookie跨域
                .allowCredentials(true)
                //规定能够跨域访问的方法类型
//                .allowedMethods("GET","HEAD", "POST","DELETE","PUT","OPTIONS")
                .allowedMethods("*")

                //添加验证头信息  token
//                .allowedHeaders()

                //允许所有
                .allowedHeaders("*")
                //预检请求存活时间 在此期间不再次发送预检请求
                .maxAge(3600);

//        addMapping：表示对哪种格式的请求路径进行跨域处理。
//        allowedHeaders：表示允许的请求头，默认允许所有的请求头信息。
//        allowedMethods：表示允许的请求方法，默认是 GET、POST 和 HEAD。这里配置为 * 表示支持所有的请求方法。
//        maxAge：表示探测请求的有效期
//        allowedOrigins 表示支持的域

    }
}

