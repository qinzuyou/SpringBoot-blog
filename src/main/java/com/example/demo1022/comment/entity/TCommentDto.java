package com.example.demo1022.comment.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TCommentDto extends TComment{
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

    //回复关联
    private  Integer relevanceId;

    //回复下回复
    private  Integer replyId;


    //回复列表
    private List<TCommentDto> replyList;

    //回复谁
    private  String replyName;

public TCommentDto(){

}

    public TCommentDto(Integer cId, Integer uId) {
        this.cId = cId;
        this.uId = uId;
    }


}
