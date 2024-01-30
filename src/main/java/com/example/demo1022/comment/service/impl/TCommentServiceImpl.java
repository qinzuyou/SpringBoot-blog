package com.example.demo1022.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.comment.entity.TComment;
import com.example.demo1022.comment.mapper.TCommentMapper;
import com.example.demo1022.comment.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2024-01-25
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {
    @Override
        public List<TComment> aidComment(Integer aid){

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("a_id",aid);
            wrapper.orderByDesc("c_id");

            return baseMapper.selectList(wrapper);
        }
}
