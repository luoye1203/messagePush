package com.xzst.relation.mp.controller;


import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.model.InnerClassParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("innerClassParam")
@Api(value = "innerClassParam",tags = "静态内部类作为参数测试")
public class InnerClassJsonResolveController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/innerClassTest", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "param",paramType = "body",value = "模拟参数",dataType = "InnerClassParam")
            }
    )
    @VisitLog("静态内部类参数测试")
    public BaseResponse innerClassParamJsonResolveTest(@RequestBody InnerClassParam param){
        logger.info(param);
        logger.info("-----------------开始");
        try {
            InnerClassParam.InnerParam innerParam=param.getList().get(0);
            logger.info(innerParam.getAge());

        } catch (Exception e) {
            logger.error("失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
        return response;

    }

}
