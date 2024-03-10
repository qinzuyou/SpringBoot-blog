package com.example.demo1022.poetry.controller;


import com.example.demo1022.poetry.entity.TPoetry;
import com.example.demo1022.poetry.service.TPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author user
 * @since 2024-03-10
 */
@RestController
@RequestMapping("/t-poetry")
public class TPoetryController {

    @Autowired
    private TPoetryService poetryService;

    @GetMapping("/allPoetry")
    public List<TPoetry> allPoetry(){
        return poetryService.list();
    }

    @GetMapping("/onePoetry")
    public TPoetry onePoetry(){
        return poetryService.onePoetry();
    }


}

