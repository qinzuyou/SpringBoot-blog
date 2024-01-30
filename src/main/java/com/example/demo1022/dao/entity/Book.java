package com.example.demo1022.dao.entity;


//lombok  工具类 自动添加注解 简化开发

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//默认生成get方法
//@Getter

//默认生成set方法
//@Setter

//默认生成get set toString hashCode equals等方法
@Data
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;
}


