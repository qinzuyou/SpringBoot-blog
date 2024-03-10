package com.example.demo1022.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo1022.article.entity.TArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.article.entity.TArticleDto;
import com.example.demo1022.article.entity.TArticleYear;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2023-11-26
 */
public interface TArticleService extends IService<TArticle> {

    //返回推荐文章

    List<TArticle> Recommend(String re);

    //文章归档
    List<TArticleYear>   Stats(Integer uid);

    //根据标签模糊查询
     IPage<TArticleDto> labelArticle(int pages,int size,String val);

    //模糊查询
    IPage<TArticleDto> searchArticle(int pages,int size,String val);


    //根据文章id查
    TArticleDto aidArticle(Integer aid);

    //根据文章类型查询章
    IPage<TArticleDto> TypeArticle(int pages, int size, String type);

//        根据用户id查


    TArticle articlePrevious(Integer aid);

    TArticle articleNext(Integer aid);

    //根据用户id查
    IPage<TArticleDto> uidArticle(int pages,int size,Integer uid,String type);
}
