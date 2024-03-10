package com.example.demo1022.wallpaperType.controller;


import com.example.demo1022.wallpaperType.entity.TWallpaperType;
import com.example.demo1022.wallpaperType.service.TWallpaperTypeService;
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
 * @since 2024-02-16
 */
@RestController
@RequestMapping("/t-wallpaper-type")
public class TWallpaperTypeController {
        @Autowired
    private TWallpaperTypeService wallpaperTypeService;

        @GetMapping("/allType")
    public List<TWallpaperType> allType(){
            return wallpaperTypeService.list();
        }
}

