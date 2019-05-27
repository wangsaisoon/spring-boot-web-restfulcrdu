package com.ws.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

// 给容器中加入自定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    // 返回值的map，就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company", "ws");
        // 异常处理器携带的数据
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        System.out.println("ext-->" + ext);
        map.put("ext", ext);
        return map;
    }
}
