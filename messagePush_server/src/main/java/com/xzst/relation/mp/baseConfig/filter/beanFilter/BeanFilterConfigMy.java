package com.xzst.relation.mp.baseConfig.filter.beanFilter;

import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("filter.test")
public class BeanFilterConfigMy {
    private final Logger logger = Logger.getLogger(this.getClass());

    private String urls[];

    @Bean
    public FilterRegistrationBean registrationBeanA() {
        logger.info(urls);
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new BeanFilterMyA());//一种写法
        //注释掉的为另一种写法
//        BeanFilterMyA myfilterA= new BeanFilterMyA();
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(myfilterA);
        filterRegistrationBean.addUrlPatterns(urls);
//        filterRegistrationBean.addUrlPatterns("/kafka/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationBeanB() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new BeanFilterMyA());//一种写法
        BeanFilterMyB myfilterB= new BeanFilterMyB();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myfilterB);
        filterRegistrationBean.addUrlPatterns(urls);
//        filterRegistrationBean.addUrlPatterns("/kafka/*");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }


    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}
