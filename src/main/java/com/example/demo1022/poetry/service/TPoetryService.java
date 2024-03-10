package com.example.demo1022.poetry.service;

import com.example.demo1022.poetry.entity.TPoetry;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2024-03-10
 */
public interface TPoetryService extends IService<TPoetry> {

    TPoetry onePoetry();

}
