package com.example.demo1022.article.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1022.article.entity.TArticle;
import com.example.demo1022.article.entity.TArticleDto;
import com.example.demo1022.article.entity.TArticleYear;
import com.example.demo1022.article.service.TArticleService;
import com.example.demo1022.user.utils.PassToken;
import com.example.demo1022.user.utils.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PassToken
    //返回推荐文章
    @GetMapping("/recommend")
    public List<TArticle> recommend(String re){
        return  articleService.Recommend(re);
    }

    //文章归档
    @PassToken
    @GetMapping("/Stats")
    public List<TArticleYear>  Stats(Integer uid){
        return articleService.Stats(uid);
    }


    //模糊查询
    @PassToken
    @GetMapping("/searchArticle")
    public IPage<TArticleDto> searchArticle(int pages,int size,String val){
        return  articleService.searchArticle(pages,size,val);
    }

    //根据标签模糊查询
    @PassToken
    @GetMapping("/labelArticle")
    public IPage<TArticleDto> labelArticle(int pages,int size,String val){
        return  articleService.labelArticle(pages,size,val);
    }
    //阅读加一
    @PassToken
    @GetMapping("/addHits")
    public Boolean addHits(Integer aid){

        TArticle oldArticle = articleService.getById(aid);

        int hits = oldArticle.getHits()+1;
        oldArticle.setHits(hits);

        return  articleService.updateById(oldArticle);

    }

    //根据用户id查

    @PassToken
    @GetMapping("/uidArticle")
    public IPage<TArticleDto> uidArticle(int pages, int size, Integer uid,String type){
        return  articleService.uidArticle(pages,size,uid,type);
    }


    @PassToken
    //根据文章id查询文章
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
    public boolean addArticle(TArticle article, MultipartFile[] files){

//        public boolean addArticle(TArticle article,@RequestPart("files")  MultipartFile[] files){
        System.out.println("********************");

        System.out.println(files);
        System.out.println("********************");

        if (files!=null){
            //获取文件名
            String originalFilename=files[0].getOriginalFilename();
            //文件上传
            Map<String, Object> map =  ajaxUploadFile(files);

            String img = (String) map.get("sqlImg");

            article.setCover(img);

            System.out.println(originalFilename);
            System.out.println(article.getALike());
        }





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


    //上一篇
    @PassToken
    @GetMapping("/articlePrevious/{aid}")

    public  TArticle articlePrevious(@PathVariable("aid") Integer aid){
    return  articleService.articlePrevious(aid);
    }


    //下一篇
    @PassToken
    @GetMapping("/articleNext/{aid}")

    public  TArticle articleNext(@PathVariable("aid") Integer aid){
        return  articleService.articleNext(aid);
    }



    //异步上传
    @PassToken
    @PostMapping("/ajaxUploadFile")
    @ResponseBody
    public Map ajaxUploadFile(MultipartFile[] files){
        Map<String,Object> map=new HashMap<>();
        for(MultipartFile file:files){
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
            String dirPath = jarF.getParentFile().toString() + "/upload/img/" + sdf.format(date) + "/";
            System.out.println(dirPath);

            String sqlImg = "/upload/img/" + sdf.format(date) + "/" + imgName;



            File filePath=new File(dirPath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            try{
                //将文件写入磁盘
                file.transferTo(new File(dirPath+imgName));
                //文件上传成功返回状态信息
                map.put("msg", "上传成功！");
                map.put("url", fileName);
                map.put("code", 200);
                map.put("sqlImg", sqlImg);
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

