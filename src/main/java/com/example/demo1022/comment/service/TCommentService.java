package com.example.demo1022.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo1022.comment.entity.TComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.comment.entity.TCommentDto;

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
    List<TCommentDto> aidComment(Integer aid);

    //文章回复
    List<TComment> relevanceComment(Integer cId);

    //回复下回复
    List<TComment> replyComment(Integer cId,Integer replyId);

    //根据用户id返回评论数量
    IPage<TCommentDto> commentCount(int pages,int size,Integer uid);


    //根据文章id返回评论列表
    IPage<TCommentDto> aidCommentCount(int pages,int size,Integer aid);




}
