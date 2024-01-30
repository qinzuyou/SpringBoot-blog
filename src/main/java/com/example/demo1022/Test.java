package com.example.demo1022;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//一.get请求
//@PathVariable("id") 获取请求路劲参数
//@PathVariable Map<String,String> pv 获取请求路径参数集合
//@RequestHeader("User-Agent") 获取请求头
//@RequestParam("age") 获取请求携带参数

//二.post请求
//@RequestBody String content 获取请求体
//@RequestParam<String>  List<String> list 获取请求携带参数集合
// @CookieValue("_ga") String _ga 获取请求携带的cookie

@Slf4j
@RestController
public class Test {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String name,
                                     @PathVariable Map<String,String> pv ){
                Map<String,Object> map = new HashMap<>();

                map.put("id",id);
                map.put("name",name);
                map.put("pv",pv);
                return map;

    }

    @PostMapping("/save")
    public  Map postMethod(@RequestBody String content){
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }

    //文件上传
    //MultipartFile 自动封装传过来的文件
    @PostMapping("/upload")
    public String upload(@RequestParam("name") String name,
                         @RequestPart("img") MultipartFile img,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("文件信息",name,img.getSize(),photos.length);
        System.out.println(img.getSize());
        System.out.println(photos.length);


        //单文件上传
        //判断文件是否为空
        if(!img.isEmpty()){
            //保存到服务器

            //获取原文件名
            String originalFilename=img.getOriginalFilename();

            img.transferTo(new File("E:\\javaImg\\" + originalFilename));
        }


        //多文件上传
        if (photos.length>0){
            for (MultipartFile photo:photos){
                if (!photo.isEmpty()){
                    String originalFilename2 =photo.getOriginalFilename();
                    photo.transferTo(new File("E:\\javaImg\\"+originalFilename2));
                }
            }
        }


        return "main";

    }
}
