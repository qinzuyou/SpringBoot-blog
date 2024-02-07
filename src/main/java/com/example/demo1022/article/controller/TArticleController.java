package com.example.demo1022.article.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.article.entity.TArticleDto;
import com.example.demo1022.article.service.TArticleService;
import com.example.demo1022.dao.entity.Book;
import com.example.demo1022.user.utils.PassToken;
import com.example.demo1022.user.utils.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author user
 * @since 2023-11-26
 */

@RestController
@RequestMapping("/t-article")
public class TArticleController {

    @Autowired
    private TArticleService articleService;

    //根据id查询文章
    @GetMapping("/aidArticle/{aid}")
    public TArticleDto aidArticle(@PathVariable("aid") Integer aid){
        return  articleService.aidArticle(aid);
    }
    @PassToken
    //根据文章类型查询
    @GetMapping("/typeArticle")
    public IPage<TArticleDto> typeArticle(int pages, int size, String type){
        return  articleService.TypeArticle(pages,size,type);
    }



    //返回所有文章
    @GetMapping("/list")
    public List<TArticle> list() {
        return articleService.list();
    }

    //根据文章id查文章
    //根据学号查询书本
    @GetMapping("/getArticleId/{id}")
    public TArticle findBySno(@PathVariable("id") Integer id){

        return articleService.getById(id);
    }

        //分页查询文章
//        @UserLoginToken
    @PassToken
    @GetMapping("/listPage")
    public Page<TArticle> tableController(@RequestParam(value = "pn",defaultValue = "1") Integer pn,@RequestParam(value = "sp",defaultValue = "4") Integer sp){

        //新建分页构造函数Page<T> T为目标实体类
        //pn:当前页，sp：每页数据条数
        Page<TArticle> userPage = new Page<TArticle>(pn, sp);
        //result为分页查询结果
        Page<TArticle> result = articleService.page(userPage);

//        return "table";
        return result;
    }


    //添加文章
    @PassToken
    @PostMapping("/addArticle")
    @ResponseBody
    public boolean addArticle(TArticle article,@RequestPart("files")  MultipartFile[] files){

        //获取文件名
        String originalFilename=files[0].getOriginalFilename();

        //文件上传
        ajaxUploadFile(files);

        article.setCover(originalFilename);

        System.out.println(originalFilename);
        System.out.println(article.getALike());

        article.setALike("88");
        System.out.println(article);



        return articleService.save(article);

    }



    //上传图片（非异步）
    @PostMapping("/singleUploadFile")
    public String singleUploadFile(MultipartFile file, Model model){
        String fileName=file.getOriginalFilename(); //获取文件名以及后缀名
        //fileName= UUID.randomUUID()+"_"+fileName;//重新生成文件名（根据具体情况生成对应文件名）

        //获取jar包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = jarF.getParentFile().toString()+"/upload/";
        System.out.println(dirPath);

        File filePath=new File(dirPath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        try{
            //将文件写入磁盘
            file.transferTo(new File(dirPath+fileName));
            //上传成功返回状态信息
            model.addAttribute("uploadStatus","上传成功");
        }catch (Exception e){
            e.printStackTrace();
            //上传失败，返回失败信息
            model.addAttribute("uploadStatus","上传失败:"+e.getMessage());
        }
        //携带上传状态信息回调到文件上传页面
        return "singleUpload";
    }


    //异步上传
    @PostMapping("/ajaxUploadFile")
    @ResponseBody
    public Map ajaxUploadFile(MultipartFile[] files){
        Map<String,Object> map=new HashMap<>();
        for(MultipartFile file:files){
            //获取文件名以及后缀名
            String fileName=file.getOriginalFilename();

            //获取jar包所在目录
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString()+"/upload/";
            System.out.println(dirPath);



            File filePath=new File(dirPath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            try{
                //将文件写入磁盘
                file.transferTo(new File(dirPath+fileName));
                //文件上传成功返回状态信息
                map.put("msg","上传成功！");
            }catch (Exception e){
                e.printStackTrace();
                //上传失败，返回失败信息
                map.put("msg","上传失败！");
            }
        }
        //携带上传状态信息回调到文件上传页面
        return map;
    }





}

