package com.example.demo1022.comment.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo1022.comment.entity.TComment;
import com.example.demo1022.comment.entity.TCommentDto;
import com.example.demo1022.comment.service.TCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import javax.xml.stream.events.Comment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author user
 * @since 2024-01-25
 */
@RestController
@RequestMapping("/t-comment")
public class TCommentController {

    @Autowired
    private TCommentService commentService;

    //根据用户id返回评论
    @GetMapping("/uidComment")
    public  List<TComment> uidComment(Integer uid){
        return  commentService.uidComment(uid);
    }


    //发表评论
    @PostMapping("/addComment")
    public Boolean addComment(TComment comment, MultipartFile[] files) {

            if(files!=null){
                Map<String, Object> map = ajaxUploadFile(files);
                String imgList = (String) map.get("sqlImg");
                comment.setImgList(imgList);
                return commentService.save(comment);
            }else {
                comment.setImgList("");
                return commentService.save(comment);
            }


    }

    //文章下回复
    @PostMapping("/relevanceComment")
    public  List<TComment> relevanceComment(Integer cId){
        return commentService.relevanceComment(cId);
    }

    //回复下回复
    @PostMapping("/replyComment")
    public  List<TComment> replyComment(Integer cId,Integer replyId){
        return commentService.replyComment(cId,replyId);
    }

    //根据文章id获取评论
    @GetMapping("/aidComment/{aid}")
    public List<TCommentDto> aidComment(@PathVariable("aid") Integer aid){

System.out.println(aid);
        return  commentService.aidComment(aid);
    }

    //根据用户id返回评论数量和评论
    @GetMapping("/commentCount")
    public IPage<TCommentDto> commentCount(int pages,int size,Integer uid ){
        return commentService.commentCount(pages,size,uid);
    }

    //根据用户id返回评论数量
    @GetMapping("/aidCommentCount")
    public IPage<TCommentDto> aidCommentCount(int pages,int size,Integer aid){
        return commentService.aidCommentCount(pages,size,aid);
    }


    //异步上传
    @PostMapping("/ajaxUploadFile")
    @ResponseBody
    public Map ajaxUploadFile(MultipartFile[] files) {
        ArrayList<String> list = new ArrayList<String>();
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
            String dirPath = jarF.getParentFile().toString() + "/upload/img/" + sdf.format(date) + "/";
            System.out.println(dirPath);

            String sqlImg = "/upload/img/" + sdf.format(date) + "/" + imgName;

            list.add(sqlImg);
            String newList = StringUtils.join(list, ",");
            System.out.println(newList);

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
                map.put("sqlImg", newList);

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

