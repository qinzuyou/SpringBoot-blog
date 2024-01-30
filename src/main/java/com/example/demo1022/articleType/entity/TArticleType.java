package com.example.demo1022.articleType.entity;

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
 * @since 2023-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TArticleType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
