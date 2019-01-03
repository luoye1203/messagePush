package com.xzst.relation.mp.controller;


import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.service.StringRedisTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("redis")
@Api(value = "redis",tags = "redis")
@VisitLog("redis模块")
public class RedisController {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplateService stringRedisTemplateService;




    @RequestMapping(value = "/stringRedisTemplate", method = RequestMethod.POST)
    @VisitLog("redis测试")
    public BaseResponse swaggerTest(){

        logger.info("-----------------开始");
        try {
            stringRedisTemplateService.setString("ywf","叶伟凤");
            stringRedisTemplateService.getString("ywf");

        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

}
