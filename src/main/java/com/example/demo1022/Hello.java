package com.example.demo1022;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/4.png")
    public String hello(){
        return "hello";
    }
}
