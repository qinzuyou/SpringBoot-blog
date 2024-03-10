package com.example.demo1022.wallpaper.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TWallpaperDto {


    private Integer pId;

    private String name;

    private String cover;

    private String type;

    private Integer uId;

    private String typeName;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

}
