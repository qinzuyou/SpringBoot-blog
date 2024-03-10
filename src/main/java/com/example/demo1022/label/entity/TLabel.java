package com.example.demo1022.label.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author user
 * @since 2024-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "l_id", type = IdType.AUTO)
    private Integer lId;

    private Integer uId;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
