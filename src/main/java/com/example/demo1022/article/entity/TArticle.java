package com.example.demo1022.article.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author user
 * @since 2023-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    //文章标题
    private String title;

    //文章封面
    private String cover;

    @JsonProperty(value = "uId")
    private Integer uId;

    private String content;

    //文章类型标签列表
    private String typeList;

    //点赞数
    @JsonProperty(value = "aLike") //添加@JsonProperty 注解，不然读不到值
    private String aLike;

    //点击数
    private Integer hits;

    //标签
    private String labelList;

    //收藏数
    private String collect;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
