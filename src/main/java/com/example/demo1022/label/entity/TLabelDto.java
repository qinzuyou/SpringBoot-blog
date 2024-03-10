package com.example.demo1022.label.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TLabelDto {
    private Integer lId;

    private Integer uId;

    private String name;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private  Integer count;
}
