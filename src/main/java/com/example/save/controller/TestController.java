package com.example.save.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
//    @CrossOrigin    //跨域处理put请求需要额外配置
    @RequestMapping("/test")
    public String test(){
        return "ok";
    }
}
