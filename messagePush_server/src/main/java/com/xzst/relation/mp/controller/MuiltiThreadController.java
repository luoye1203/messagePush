package com.xzst.relation.mp.controller;


import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.service.StringRedisTemplateService;
import com.xzst.relation.mp.service.ThreadService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("thread")
@Api(value = "thread",tags = "thread测试")
@VisitLog("thread模块")
public class MuiltiThreadController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ThreadService threadService;

    @Autowired()
    @Qualifier("e1")
    private ThreadPoolTaskExecutor taskExecutor;



    @RequestMapping(value = "/threadTest", method = RequestMethod.POST)
    @VisitLog("thread测试")
    public BaseResponse threadTest(){

        logger.info("-----------------开始");
        try {

            for (int i = 0; i <10; i++) {
                threadService.test();
            }
            taskExecutor.shutdown();


        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

}
