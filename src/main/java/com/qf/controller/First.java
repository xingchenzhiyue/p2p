package com.qf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunwenbo
 * @create 2017-09-24 16:44
 **/
@RestController
public class First {

    @RequestMapping("/index.do")
    public static void run(){
        System.out.println("hello world!");
    }
}