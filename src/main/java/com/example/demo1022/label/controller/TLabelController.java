package com.example.demo1022.label.controller;


import com.example.demo1022.label.entity.TLabel;
import com.example.demo1022.label.entity.TLabelDto;
import com.example.demo1022.label.service.TLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author user
 * @since 2024-02-07
 */
@RestController
@RequestMapping("/t-label")
public class TLabelController {

    @Autowired
    private TLabelService labelService;


    //根据uid获取所有标签数量

    @GetMapping("/uidAllLabel")
    public List<TLabelDto> uidAllLabel(Integer uid){
        return  labelService.uidAllLabel(uid);
    }


    //获取用户标签
    @GetMapping("/uidLabel")
    public List<TLabel> uidLabel(Integer uid){

        return  labelService.uidLabel(uid);
    }

    //添加标签
    @PostMapping("/addLabel")
    public Boolean addLabel( String[] name,Integer uid){
//        System.out.println(name.get(1));

        for(String item:name){
            System.out.println((item));

            TLabel tLabel = labelService.deName(item,uid);
            if(tLabel==null){
                TLabel label = new TLabel();
                label.setName(item);
                label.setUId(uid);
               labelService.save(label);
            }

        }
//        List<String> list = new ArrayList<>();
//        list.add("11");
//        for(String item:list){
//                System.out.println((item));
//        }
////
        return  true;





    }

}

