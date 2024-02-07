package com.example.demo1022.article.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TArticleDto {
    private Integer aId;

    private String title;

    private String cover;

    private Integer uId;

    private String content;

    private String typeList;

    //点赞数
    private String aLike;

    //点击数
    private Integer hits;

    //收藏数
    private String collect;

    //用户账号
    private String account;

//    private String password;

    //用户昵称
    private String nickname;

    //标签
    private String labelList;

    //用户头像
    private String portrait;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
