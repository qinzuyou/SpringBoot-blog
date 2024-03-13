package com.example.demo1022.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.comment.entity.TComment;
import com.example.demo1022.comment.entity.TCommentDto;
import com.example.demo1022.comment.mapper.TCommentMapper;
import com.example.demo1022.comment.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@AllArgsConstructor
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {


    private final TCommentMapper commentMapper;

    @Override
    public List<TComment> uidComment(Integer uid){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",uid);
        return  baseMapper.selectList(queryWrapper);
    }

    //根据用户id返回评论数量
    @Override
    public IPage<TCommentDto> commentCount(int pages,int size,Integer uid){

        IPage<TCommentDto> list = commentMapper.selectJoinPage(
                new Page<TCommentDto>(pages,size),
                TCommentDto.class,
                new MPJLambdaWrapper<TComment>()
                        .selectAll(TComment.class)

                        .leftJoin(TArticle.class,TArticle::getAId,TComment::getAId)

                        .eq(TArticle::getUId,uid)
                        .orderByDesc(TComment::getCId)
        );

        return list;

    }

    //根据文章id返回评论数量
    @Override
    public IPage<TCommentDto> aidCommentCount(int pages,int size,Integer aid){

        IPage<TCommentDto> list = commentMapper.selectJoinPage(
                new Page<TCommentDto>(pages,size),
                TCommentDto.class,
                new MPJLambdaWrapper<TComment>()
                        .selectAll(TComment.class)
                        .eq(TComment::getAId,aid)
                        .orderByDesc(TComment::getCId)
        );

        return list;

    }



    //根据文章id返回评论
    @Override
        public List<TCommentDto> aidComment(Integer aid){

//            QueryWrapper wrapper = new QueryWrapper();
//            wrapper.eq("a_id",aid);
//            wrapper.orderByDesc("c_id");
//
//        List<TComment> list =baseMapper.selectList(wrapper);
//       List<TCommentDto>  list2= new ArrayList<TCommentDto>();
//            list.get(1);
//            System.out.println(list.get(1).getAId());


        List<TCommentDto> list =commentMapper.selectJoinList(
                TCommentDto.class,
                new MPJLambdaWrapper<TComment>()
                        .selectAll(TComment.class)
                        .eq(TComment::getAId,aid)
                        .orderByDesc(TComment::getCId)


        );

        for (TCommentDto commentDto :list){

            List<TCommentDto> item =commentMapper.selectJoinList(
                    TCommentDto.class,
                    new MPJLambdaWrapper<TComment>()
                            .selectAll(TComment.class)
                            .eq(TComment::getRelevanceId,commentDto.getCId())
            );




            commentDto.setReplyList(item);



            for(TCommentDto commentDto2 :commentDto.getReplyList()){

                TCommentDto dto = commentMapper.selectJoinOne(
                        TCommentDto.class,
                        new MPJLambdaWrapper<TComment>()
                                .selectAll(TComment.class)
                                .selectAs(TComment::getNickname,TCommentDto::getReplyName)
                                .eq(TComment::getCId,commentDto2.getReplyId())
                );

                System.out.println(commentDto2);
                System.out.println(555555);
                System.out.println(dto);
                if(dto!=null){
                    System.out.println("222222222222222222222");
                    commentDto2.setReplyName(dto.getNickname());

                }

            }


        }

        List<TCommentDto> newList = new ArrayList<>();

        for (TCommentDto commentDto:list){
            if(commentDto.getRelevanceId()==null&&commentDto.getReplyId()==null){
                newList.add(commentDto);
            }
        }



            return newList;

        }

    @Override
    public List<TComment> relevanceComment(Integer cId){

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("relevance_id",cId);

        return baseMapper.selectList(wrapper);
    }
    @Override
    public List<TComment> replyComment(Integer cId,Integer replyId){

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("relevance_id",cId);
        wrapper.eq("reply_id",replyId);

        return baseMapper.selectList(wrapper);
    }

}
