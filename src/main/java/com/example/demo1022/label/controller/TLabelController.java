package com.example.demo1022.label.controller;


import com.example.demo1022.label.entity.TLabel;
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

    @PostMapping("/addLabel")
    public Boolean addLabel( String[] name){
//        System.out.println(name.get(1));

        for(String item:name){
            System.out.println((item));

            TLabel tLabel = labelService.deName(item);
            if(tLabel==null){
                TLabel label = new TLabel();
                label.setName(item);
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

