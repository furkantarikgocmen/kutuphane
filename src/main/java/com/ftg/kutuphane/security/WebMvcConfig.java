package com.ftg.kutuphane.security;

import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

public class WebMvcConfig implements WebMvcConfigurer {
    //Before the prod. build, change all TimeUnit.MILLISECONDS to TimeUnit.DAYS
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("js/**").addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
        registry.addResourceHandler("css/**").addResourceLocations("classpath:/static/css/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
        registry.addResourceHandler("img/**").addResourceLocations("classpath:/static/img/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
        registry.addResourceHandler("lib/**").addResourceLocations("classpath:/static/lib/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
        registry.addResourceHandler("assets/**").addResourceLocations("classpath:/static/assets/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
    }
}
