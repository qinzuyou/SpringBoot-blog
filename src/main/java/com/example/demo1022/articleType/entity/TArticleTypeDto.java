package com.example.demo1022.articleType.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TArticleTypeDto {

    private Integer tId;

    private String name;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private  Integer Count;
}
