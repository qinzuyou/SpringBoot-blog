package com.example.demo1022.wallpaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1022.wallpaper.entity.TWallpaper;
import com.example.demo1022.wallpaper.entity.TWallpaperDto;
import com.example.demo1022.wallpaper.mapper.TWallpaperMapper;
import com.example.demo1022.wallpaper.service.TWallpaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1022.wallpaperType.entity.TWallpaperType;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author user
 * @since 2024-02-16
 */
@Service
@AllArgsConstructor
public class TWallpaperServiceImpl extends ServiceImpl<TWallpaperMapper, TWallpaper> implements TWallpaperService {


    private  final  TWallpaperMapper wallpaperMapper;

    @Override
    public TWallpaper oneWallpaper(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        Random random = new Random();

        int count = baseMapper.selectCount(queryWrapper);
        int offset = random.nextInt(count);

        queryWrapper.last("limit 1 offset "+offset);
        return  baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<TWallpaperDto> typeWallpaper(int pages,int size,String type){

        if(type==null){
            IPage<TWallpaperDto> list = wallpaperMapper.selectJoinPage(
                    new Page<TWallpaperDto>(pages,size),
                    TWallpaperDto.class,
                    new MPJLambdaWrapper<TWallpaper>()
                            .selectAll(TWallpaper.class)
                            .selectAs(TWallpaperType::getName,TWallpaperDto::getTypeName)
                            .leftJoin(TWallpaperType.class,TWallpaperType::getWId,TWallpaper::getType)


            );
            return  list;

        }else {
            IPage<TWallpaperDto> list = wallpaperMapper.selectJoinPage(
                    new Page<TWallpaperDto>(pages,size),
                    TWallpaperDto.class,
                    new MPJLambdaWrapper<TWallpaper>()
                            .selectAll(TWallpaper.class)
                            .selectAs(TWallpaperType::getName,TWallpaperDto::getTypeName)
                            .leftJoin(TWallpaperType.class,TWallpaperType::getWId,TWallpaper::getType)
                            .eq(TWallpaperType::getName,type)

            );
            return  list;
        }




    }

}
