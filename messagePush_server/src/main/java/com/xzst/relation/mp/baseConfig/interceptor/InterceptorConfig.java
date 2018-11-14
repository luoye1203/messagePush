package com.xzst.relation.mp.baseConfig.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by LHT on 2018/7/4.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] urls={"/interceptor/*"};
        registry.addInterceptor(new InterceptorMyA()).addPathPatterns(urls);
        registry.addInterceptor(new InterceptorMyB()).addPathPatterns(urls);

    }
}