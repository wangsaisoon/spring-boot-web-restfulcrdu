package com.ws.config;

import com.ws.component.LoginHandlerInterceptor;
import com.ws.component.MyLocaleResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 使用WebMvcConfigurerAdapter可以扩展SpringMVC的功能
// SpringBoot2.0后WebMvcConfigurerAdapter类过期了，现在使用WebMvcConfigurer接口
//@EnableWebMvc// 完全管控SpringMVC，默认配置都不生效
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        // 浏览器发送/hello请求，直接去success页面
        registry.addViewController("/hello2").setViewName("success");
        System.out.println("mvcconfig");
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index.html").setViewName("index");
    }

    // 所有的WebMvcConfigurer组件都会一起生效
    @Bean // 将组件注册到容器中
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                System.out.println("configurer");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册登录拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 静态资源*.css，*.js
                // SpringBoot已经做好了静态资源映射
                // 拦截登录请求
               /* registry.addInterceptor(new LoginHandlerInterceptor())
                        // 拦截所有路径下的所有页面
                        .addPathPatterns("/**")
                        // 排除登录页面
                        .excludePathPatterns("/index.html", "/", "/user/login");*/
            }
        };


        return configurer;
    }

    /**
     * 将自己编写的LocaleResolver加入到容器中
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
