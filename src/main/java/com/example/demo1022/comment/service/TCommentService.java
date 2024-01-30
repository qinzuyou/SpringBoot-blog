package com.example.demo1022.comment.service;

import com.example.demo1022.comment.entity.TComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2024-01-25
 */
public interface TCommentService extends IService<TComment> {

    //根据文章id返回评论
    List<TComment> aidComment(Integer aid);

}
