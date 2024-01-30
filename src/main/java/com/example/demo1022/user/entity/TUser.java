package com.example.demo1022.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author user
 * @since 2023-11-23
 */
@Data
@TableName("t_user")
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

//    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
//    private String id;

    //用户账号
    private String account;

    //用户密码
    private String password;

    //用户昵称
    private String nickname;

    //头像
    private String portrait;
    //获得点赞
    private Integer likes;

    //被点击数
    private Integer views;

    //用户权限
    private Integer permissions;

    //收藏数
    private Integer collect;

    //入参时间格式化，如果入参不是pattern="yyyy-MM-dd HH:mm:ss" 定义的类型会报错
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    //出参数格式化，pattern = "yyyy-MM-dd HH:mm:ss"
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")//jackson注解
//    //@JSONField(format = "yyyy-MM-dd HH:mm:ss") //适用于Alibaba fastjson，可以直接在JSON.tostring时进行转义
//    private Date createTime;
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date updateTime;

//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    @Override
//    public String toString() {
//        return "TUser{" +
//                "id=" + id +
//                ", account='" + account + '\'' +
//                ", password='" + password + '\'' +
//                ", nickname='" + nickname + '\'' +
//                ", likes=" + likes +
//                ", views=" + views +
//                ", permissions=" + permissions +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                '}';
//    }
}
