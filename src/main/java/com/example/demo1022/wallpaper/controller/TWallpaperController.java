package com.example.demo1022.wallpaper.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo1022.wallpaper.entity.TWallpaper;
import com.example.demo1022.wallpaper.entity.TWallpaperDto;
import com.example.demo1022.wallpaper.service.TWallpaperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author user
 * @since 2024-02-16
 */
@RestController
@RequestMapping("/t-wallpaper")
public class TWallpaperController {

    @Autowired
    private TWallpaperService wallpaperService;


    @GetMapping("/oneWallpaper")
    public TWallpaper oneWallpaper(){
        return wallpaperService.oneWallpaper();
    }


    //根据壁纸类型分页返回壁纸

    @GetMapping("/typeWallpaper")
    public IPage<TWallpaperDto> typeWallpaper(int pages,int size,String type){
        return  wallpaperService.typeWallpaper(pages,size,type);
    }

    //添加壁纸
    @PostMapping("/addWallpaper")
    public  Boolean addWallpaper(TWallpaper wallpaper,MultipartFile[] files){

        System.out.println(wallpaper);
//        if(files!=null){
//
//            comment.setImgList(imgList);
//            return commentService.save(comment);
//        }else {
//            comment.setImgList("");
//            return commentService.save(comment);
//        }

        Map<String, Object> map = ajaxUploadFile(files);
        String img = (String) map.get("sqlImg");
        wallpaper.setCover(img);


    return     wallpaperService.save(wallpaper);
    }




    public Map ajaxUploadFile(MultipartFile[] files) {
//        ArrayList<String> list = new ArrayList<String>();
        Map<String, Object> map = new HashMap<>();
        for (MultipartFile file : files) {
            //获取文件名以及后缀名
            String fileName = file.getOriginalFilename();

//            截取"."之后字符串
            String str1 = fileName.substring(0, fileName.indexOf("."));
            String str2 = fileName.substring(str1.length() + 1, fileName.length());

            //获取当前时间戳

            long time = System.currentTimeMillis();
            System.out.println(time);

            //给图片重新起名
            String imgName = time + "." + str2;

            System.out.println(imgName);

            //获取当前年月日,以此创建日期目录
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            System.out.println(sdf.format(date));


            //获取jar包所在目录
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString() + "/upload/wallpaper/" + sdf.format(date) + "/";
            System.out.println(dirPath);

            String sqlImg = "/upload/wallpaper/" + sdf.format(date) + "/" + imgName;

//            list.add(sqlImg);
//            String newList = StringUtils.join(list, ",");
//            System.out.println(newList);

            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                //将文件写入磁盘
//                file.transferTo(new File(dirPath+fileName));
                file.transferTo(new File(dirPath + imgName));

                //文件上传成功返回状态信息
                map.put("msg", "上传成功！");
                map.put("url", fileName);
                map.put("code", 200);
                map.put("sqlImg", sqlImg);

            } catch (Exception e) {
                e.printStackTrace();
                //上传失败，返回失败信息
                map.put("msg", "上传失败！");
            }
        }
        //携带上传状态信息回调到文件上传页面
        return map;
    }

}

