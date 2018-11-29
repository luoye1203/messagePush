package com.xzst.relation.mp.controller;


import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.model.SwaggerParamModel;
import com.xzst.relation.mp.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("redis")
@Api(value = "redis",tags = "redis测试")
public class RedisController {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RedisService redisService;




    @RequestMapping(value = "/redisTest", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "swaggerParamModel",paramType = "body",value = "模拟参数",dataType = "SwaggerParamModel")
            }
    )
    @VisitLog("redis测试")
    public BaseResponse swaggerTest(){

        logger.info("-----------------开始");
        try {
            redisService.set();
            redisService.get();

        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

}
