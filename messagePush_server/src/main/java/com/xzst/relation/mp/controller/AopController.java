package com.xzst.relation.mp.controller;

import com.xzst.relation.mp.annotation.VisitLog;
import com.xzst.relation.mp.model.BaseResponse;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LHT on 2018/7/4.
 */
@RestController
@Api(value = "AOP测试服务",tags = "AOP")
@RequestMapping("/aop/")
@VisitLog(value = "aop模块")
public class AopController {
	private final Logger logger = Logger.getLogger(this.getClass());


	@RequestMapping(value = "/aopTest", method = RequestMethod.GET)
	@ApiOperation(value = "aop测试",notes = "注意",tags = "好的")
	@ApiParam(required = true)
	@ApiImplicitParams(
			{
					@ApiImplicitParam(name = "token",paramType = "query",value = "模拟token,空和填值测试",dataType = "string")
			}
	)
	@VisitLog("测试")
	public BaseResponse testWebFilter(@RequestParam String token) {
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
