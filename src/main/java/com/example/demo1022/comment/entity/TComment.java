package com.example.demo1022.comment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author user
 * @since 2024-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    @TableField("u_id")
    private Integer uId;

    //文章id
    @TableField("a_id")
    private Integer aId;

    //内容
    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //权限
    private Integer userCheck;

    //昵称
    @TableField("nickName")
    private String nickname;

    //邮箱
    private String email;

    //qq号
    private String qq;

    //点赞
    private String userLike;

    //头像
    private String avatar;


    //评论图片
    @TableField("imgList")
    private String imgList;

    //地址信息
    private  String info;


}
