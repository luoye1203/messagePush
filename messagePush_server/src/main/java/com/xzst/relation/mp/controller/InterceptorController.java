package com.xzst.relation.mp.controller;

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
@Api("拦截器测试服务")
@RequestMapping("/interceptor/")
public class InterceptorController {
	private final Logger logger = Logger.getLogger(this.getClass());





	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ApiOperation(value = "拦截器测试",notes = "")
	@ApiParam(required = true)
	@ApiImplicitParams(
			{
					@ApiImplicitParam(name = "token",paramType = "query",value = "模拟token,空和填值测试",dataType = "string")
			}
	)
	public BaseResponse testInterceptor(@RequestParam String token) {
		logger.info("开始");
		try {


		} catch (Exception e) {
			logger.error("失败", e);
			return BaseResponse.buildResponse().setCode(201).setMessage("失败").build();
		}
		BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("成功").build();
		return response;
	}





}
