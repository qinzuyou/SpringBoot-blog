package com.example.demo1022.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo1022.user.entity.TUser;
import com.example.demo1022.user.mapper.TUserMapper;
import com.example.demo1022.user.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2023-11-23
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
    @Override
    public TUser getUserByAccountAndPassword(TUser user) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",user.getAccount());
        // 这里就不涉及加密了，需要的，自行进行加密处理
        wrapper.eq("password", user.getPassword());

        return baseMapper.selectOne(wrapper);

    }

    @Override
     public TUser getUserByAccount(TUser user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account",user.getAccount());

        return baseMapper.selectOne(wrapper);
    };


}



