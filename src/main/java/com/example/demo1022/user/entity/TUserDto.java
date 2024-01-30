package com.example.demo1022.user.entity;

import lombok.Data;

@Data

public class TUserDto {

    private Long id;


    private String account;

//    private String password;

    private String nickname;

    private Integer likes;

    private Integer views;

    //token
    private String token;

    private  String state;
    //

    //收藏数
    private Integer collect;

    //头像
    private String portrait;

    public TUserDto() {

    }

    public TUserDto(String state) {
        this.state = state;
    }

    public TUserDto(Long id, String account, String nickname, Integer likes, Integer views,String portrait,Integer collect) {
        this.id = id;
        this.account = account;
        this.nickname = nickname;
        this.likes = likes;
        this.views = views;
        this.portrait=portrait;
        this.collect=collect;
    }

    //    private Integer permissions;
}
