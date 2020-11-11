package com.ftg.kutuphane.security;

import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

public class WebMvcConfig implements WebMvcConfigurer {
    //Before the prod. build, change all TimeUnit.MILLISECONDS to TimeUnit.DAYS
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("dist/**").addResourceLocations("classpath:/static/dist/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
        registry.addResourceHandler("plugins/**").addResourceLocations("classpath:/static/plugins/")
                .setCacheControl(CacheControl.maxAge(15, TimeUnit.MILLISECONDS));
    }
}
