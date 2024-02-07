package com.example.demo1022.label.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1022.label.entity.TLabel;
import com.example.demo1022.label.mapper.TLabelMapper;
import com.example.demo1022.label.service.TLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2024-02-07
 */
@Service
public class TLabelServiceImpl extends ServiceImpl<TLabelMapper, TLabel> implements TLabelService {

@Override
    public TLabel  deName(String name){



        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",name);

        return  baseMapper.selectOne(wrapper);
    }
}
