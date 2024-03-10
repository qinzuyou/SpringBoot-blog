package com.example.demo1022.user.entity;

import lombok.Data;

@Data

public class TUserDto {

    private Long uid;



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

    //座右铭
    private  String motto;

    //网站
    private  String website;

    //网站名称
    private String webName;

    //性别
    private  String gender;

    public TUserDto() {

    }

    public TUserDto(String state) {
        this.state = state;
    }

    public TUserDto(Long uid, String account, String nickname, Integer likes, Integer views,String portrait,Integer collect,String motto) {
        this.uid = uid;
        this.account = account;
        this.nickname = nickname;
        this.likes = likes;
        this.views = views;
        this.portrait=portrait;
        this.collect=collect;
        this.motto=motto;
    }

    //    private Integer permissions;
}
