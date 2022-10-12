package edu.uc.config;

import edu.uc.intercept.LoginInterceptHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SysConfig implements WebMvcConfigurer {


    @Autowired
    private LoginInterceptHandler loginInterceptHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器,并设置拦截的请求路径
        //addPathPatterns为拦截此请求路径的请求
        //excludePathPatterns为不拦截此路径的请求
        registry.addInterceptor(loginInterceptHandler)
                .addPathPatterns("/area35a/*")
                .excludePathPatterns("/area35a/Login")
                .excludePathPatterns("/area35a/ValidateCode")
                .excludePathPatterns("/area35a/Login_loginDeal");
    }
}
