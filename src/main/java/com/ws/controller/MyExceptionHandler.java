package com.ws.controller;

import com.ws.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 定制异常返回的json数据
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 1、没有自适应效果，浏览器客户端返回的都是json
    /*@ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handlerException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }*/

    // 转发到/error，进行自适应响应效果处理
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message","用户出错啦~");
        // 传入错误状态码，默认200，否则就不会进入定制的错误页面解析流程
//        Integer statusCode = (Integer) request
//                .getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code", 500);
        request.setAttribute("ext", map);
        // 转发到/error
        return "forward:/error";
    }

}
