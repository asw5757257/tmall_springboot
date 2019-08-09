package com.zyw.tmall_springboot.config;

import com.zyw.tmall_springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: tmall_springboot
 * @description:
 * @author: Cengyuwen
 * @create: 2019-08-08 16:30
 **/
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Bean
    public  LoginInterceptor getLoginIntercepter()
    {
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**");
    }
}
