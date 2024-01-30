package com.example.demo1022.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo1022.article.entity.TArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.article.entity.TArticleDto;

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


    //根据商品id查
    TArticleDto aidArticle(Integer aid);

    //根据文章类型查询章
    IPage<TArticleDto> TypeArticle(int pages, int size, String type);
}
