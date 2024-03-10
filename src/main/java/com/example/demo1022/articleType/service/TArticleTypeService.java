package com.example.demo1022.articleType.service;

import com.example.demo1022.articleType.entity.TArticleType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.articleType.entity.TArticleTypeDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2023-11-28
 */
public interface TArticleTypeService extends IService<TArticleType> {
    //根据用户id返回分类数量
    List<TArticleTypeDto> uidAllType(Integer uid);

}
