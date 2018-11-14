package com.xzst.relation.mp.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2017/7/4.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    //添加全局参数
    private List<Parameter> getTokenParam(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    @Bean
    public Docket kafkaManage() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kafka模块")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/kafka/.*"))//过滤的接口
                .build()
                .globalOperationParameters(getTokenParam())
                .apiInfo(detailInfo("kafka模块"));
    }

    @Bean
    public Docket filterManage() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("filter模块")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/filter/.*"))//过滤的接口
                .build()
                .globalOperationParameters(this.getTokenParam())
                .apiInfo(detailInfo("filter模块"));
    }


    private ApiInfo detailInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)//大标题
                .build();
    }
}
