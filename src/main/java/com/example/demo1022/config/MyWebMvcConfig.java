package com.example.demo1022.config;


import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;


//设置读取target下的upload目录下的文件
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    //将jar文件下的对应静态资源文件路径对应到磁盘的路径（根据个人的情况修改“file：”的值）

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//
//        //获取jar所在目录
//        ApplicationHome h = new ApplicationHome(getClass());
//        File jarF = h.getSource();
//
//        //在jar包所在目录生成一个upload文件夹来储存上传的图片
//        String dirPath = jarF.getAbsolutePath().toString()+"/upload/";
//
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + dirPath);
//
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取jar包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = jarF.getParentFile().toString()+"/upload/";

        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + dirPath);
    }



}
