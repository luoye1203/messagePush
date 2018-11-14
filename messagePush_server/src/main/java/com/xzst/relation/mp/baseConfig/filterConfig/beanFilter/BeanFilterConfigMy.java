package com.xzst.relation.mp.baseConfig.filterConfig.beanFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFilterConfigMy {

    @Bean
    public FilterRegistrationBean registrationBeanA() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new BeanFilterMyA());//一种写法
        //注释掉的为另一种写法
//        BeanFilterMyA myfilterA= new BeanFilterMyA();
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(myfilterA);
        filterRegistrationBean.addUrlPatterns("/filter/*");
        filterRegistrationBean.addUrlPatterns("/kafka/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationBeanB() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new BeanFilterMyA());//一种写法
        BeanFilterMyB myfilterB= new BeanFilterMyB();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myfilterB);
        filterRegistrationBean.addUrlPatterns("/filter/*");
        filterRegistrationBean.addUrlPatterns("/kafka/*");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }



}
