package com.example.demo1022.user.service;

import com.example.demo1022.articleType.entity.TArticleTypeDto;
import com.example.demo1022.comment.entity.TCommentDto;
import com.example.demo1022.user.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.user.entity.TUserDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2023-11-23
 */
public interface TUserService extends IService<TUser> {



    //根据uid获取用户信息

    TUserDto uidUserInfo(Integer uid);

    //检验账户密码
    TUser getUserByAccountAndPassword(TUser user);


    //检验账户
    TUser getUserByAccount(TUser user);

    //添加用户
//    TUser addUser(TUser user);



}
