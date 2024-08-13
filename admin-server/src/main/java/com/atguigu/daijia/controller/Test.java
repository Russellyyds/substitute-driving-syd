package com.atguigu.daijia.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Test {
    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}
