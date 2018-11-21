package com.xzst.relation.mp.baseConfig.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by LHT on 2018/7/4.
 */
@Configuration
@ConfigurationProperties("interceptor.test")
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    private List<String> urls;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        String[] urls1={"/interceptor/*"};
        registry.addInterceptor(new InterceptorMyA()).addPathPatterns(urls.toArray(new String[urls.size()]));
//        registry.addInterceptor(new InterceptorMyA()).addPathPatterns(urls);
        registry.addInterceptor(new InterceptorMyB()).addPathPatterns(urls.toArray(new String[urls.size()]));
//        registry.addInterceptor(new InterceptorMyB()).addPathPatterns(urls);

    }


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}