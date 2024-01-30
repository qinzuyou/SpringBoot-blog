package com.example.demo1022.user.service;

import com.example.demo1022.user.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2023-11-23
 */
public interface TUserService extends IService<TUser> {

    //检验账户密码
    TUser getUserByAccountAndPassword(TUser user);


    //检验账户
    TUser getUserByAccount(TUser user);

    //添加用户
//    TUser addUser(TUser user);



}
