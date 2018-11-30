package com.xzst.relation.mp.controller;


import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.service.StringRedisTemplateService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("distributedLocker")
@Api(value = "distributedLocker",tags = "分布式锁测试")
public class DistributedLockerController {

    private final Logger logger = Logger.getLogger(this.getClass());




    @RequestMapping(value = "/redlock", method = RequestMethod.POST)
    @VisitLog("redlock分布式锁测试")
    public BaseResponse swaggerTest(){

        logger.info("-----------------开始");
        try {


        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

}
