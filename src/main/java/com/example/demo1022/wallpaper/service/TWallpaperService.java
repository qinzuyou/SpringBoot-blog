package com.example.demo1022.wallpaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo1022.wallpaper.entity.TWallpaper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1022.wallpaper.entity.TWallpaperDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author user
 * @since 2024-02-16
 */
public interface TWallpaperService extends IService<TWallpaper> {

    IPage<TWallpaperDto> typeWallpaper(int pages,int size,String type);

    //随机返回一张壁纸
    TWallpaper oneWallpaper();


}
