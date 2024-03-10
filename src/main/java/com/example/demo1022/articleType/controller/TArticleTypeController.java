package com.example.demo1022.articleType.controller;


import com.example.demo1022.articleType.entity.TArticleType;
import com.example.demo1022.articleType.entity.TArticleTypeDto;
import com.example.demo1022.articleType.service.TArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author user
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/t-article-type")
public class TArticleTypeController {

    @Autowired
    private TArticleTypeService typeService;

    //根据id返回所有分类

    @GetMapping("/uidAllType")
    public  List<TArticleTypeDto> uidAllType(Integer uid){
     return    typeService.uidAllType(uid);
    }

    @PostMapping("/addType")
    public boolean addType(TArticleType type){
            System.out.println(type);
        return typeService.save(type);

    }

    @GetMapping("/allType")
    public List<TArticleType> allType(){
        return typeService.list();
    }

}

