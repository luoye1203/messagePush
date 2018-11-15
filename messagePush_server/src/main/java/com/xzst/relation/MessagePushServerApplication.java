package com.xzst.relation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@EnableSwagger2
@EnableTransactionManagement
@MapperScan("com.xzst.relation.mp.dao")
@EnableScheduling
@ServletComponentScan //使@WebFilter 起作用
//在 SpringBootApplication 上使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
public class MessagePushServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MessagePushServerApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(MessagePushServerApplication.class, args);
	}
}
