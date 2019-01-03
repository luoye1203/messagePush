package com.xzst.relation.mp.controller;


import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.service.LambdaService;
import com.xzst.relation.mp.service.StringRedisTemplateService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("labmda")
@Api(value = "labmda")
@VisitLog("labmda模块")
public class LambdaController {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private LambdaService lambdaService;


    @RequestMapping(value = "/lambdaListTest", method = RequestMethod.POST)
    @VisitLog("labmda List测试")
    public BaseResponse lambdaListTest() {

        logger.info("-----------------开始");
        try {
            lambdaService.listService();
        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response = BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

    @RequestMapping(value = "/lambdaMapTest", method = RequestMethod.POST)
    @VisitLog("labmda Map测试")
    public BaseResponse lambdaTMapest() {

        logger.info("-----------------开始");
        try {
            lambdaService.mapService();
        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response = BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }





    @RequestMapping(value = "/lambdaListObjectTest", method = RequestMethod.POST)
    @VisitLog("labmda List测试")
    public BaseResponse lambdaListObjectTest() {

        logger.info("-----------------开始");
        try {
            lambdaService.listObjectService();
        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response = BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }


    @RequestMapping(value = "/lambdaListParalleTest", method = RequestMethod.POST)
    @VisitLog("lambdaListParalle测试")
    public BaseResponse lambdaListParalleTest() {

        logger.info("-----------------开始");
        try {
            lambdaService.lambdaListParalleService();
        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response = BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

    @RequestMapping(value = "/lambdaStringOrderTest", method = RequestMethod.POST)
    @VisitLog("lambdaListParalle测试")
    public BaseResponse lambdaStringOrderTest() {

        logger.info("-----------------开始");
        try {
            lambdaService.lambdaStringOrder();
        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response = BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }


}
