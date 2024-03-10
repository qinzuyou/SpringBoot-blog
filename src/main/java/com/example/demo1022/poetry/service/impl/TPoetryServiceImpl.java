package com.example.demo1022.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1022.poetry.entity.TPoetry;
import com.example.demo1022.poetry.mapper.TPoetryMapper;
import com.example.demo1022.poetry.service.TPoetryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2024-03-10
 */
@Service
public class TPoetryServiceImpl extends ServiceImpl<TPoetryMapper, TPoetry> implements TPoetryService {

    @Override
    public TPoetry onePoetry(){
        QueryWrapper queryWrapper = new QueryWrapper<TPoetry>();
        Random random = new Random();
       int count = baseMapper.selectCount(queryWrapper);
            int offset = random.nextInt(count);
        queryWrapper.last("limit 1 offset "+offset);
        return baseMapper.selectOne(queryWrapper);
//        queryWrapper.orderByRandom();
    }
}
