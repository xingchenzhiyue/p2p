package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 王暄 on 2017/9/24.
 */
@Controller
public class First {
    @RequestMapping("/index.do")
    @ResponseBody
    public void run(){
        System.out.println("hello world!");
    }

}
