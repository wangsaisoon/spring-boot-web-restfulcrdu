package com.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            // 登录成功
//            return "dashboard";
            session.setAttribute("loginUser", username);
            // 为防止表单重复提交，可以重定向
            return "redirect:/main.html";
        }
        // 登录失败
        map.put("msg", "用户名或密码错误");
        return "login";
    }

    // 退出登录
    @GetMapping("/user/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/index.html";
    }
}
