package com.example.demo1022.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.MPJMappingWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.article.entity.TArticleDto;
import com.example.demo1022.article.mapper.TArticleMapper;
import com.example.demo1022.article.service.TArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1022.user.entity.TUser;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2023-11-26
 */
@Service
@AllArgsConstructor
public class TArticleServiceImpl extends ServiceImpl<TArticleMapper, TArticle> implements TArticleService {

    private final TArticleMapper tArticleMapper;


    //根据文章id查
    @Override
    public TArticleDto aidArticle(Integer aid){

        TArticleDto list =tArticleMapper.selectJoinOne(

                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .leftJoin(TUser.class,TUser::getId,TArticle::getUId)
                        .eq(TArticle::getAId,aid)

        );

        return  list;



    }



    //根据文章类型查询文章
    @Override
    public IPage<TArticleDto> TypeArticle(int pages,int size,String type){

      IPage<TArticleDto> list =tArticleMapper.selectJoinPage(
                new Page<TArticleDto>(pages,size),
                TArticleDto.class,
                new MPJLambdaWrapper<TArticle>()
                        .selectAll(TArticle.class)
                        .selectAs(TUser::getAccount,TArticleDto::getAccount)
                        .selectAs(TUser::getNickname,TArticleDto::getNickname)
                        .selectAs(TUser::getPortrait,TArticleDto::getPortrait)
                        .leftJoin(TUser.class,TUser::getId,TArticle::getUId)
                        .like(TArticle::getTypeList,type)
                        .orderByDesc(TArticle::getAId)

        );

      return  list;

    }

}
