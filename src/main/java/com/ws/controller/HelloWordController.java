package com.ws.controller;

import com.ws.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloWordController {


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user) {
        if ("aaa".equals(user)) {
            throw new UserNotExistException();
        }
        return "hello world~";
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>你好啊~<h1>");
        map.put("users", Arrays.asList("小白", "小黑", "小白菜"));
        // classpath:/templates/success.html
        return "success";
    }

    /*@RequestMapping({"/", "/index.html"})
    public String index() {
        System.out.println("controller");
        return "login";
    }*/
}
