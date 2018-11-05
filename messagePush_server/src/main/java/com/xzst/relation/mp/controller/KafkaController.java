package com.xzst.relation.mp.controller;

import com.alibaba.fastjson.JSON;
import com.xzst.relation.mp.http.HttpResult;
import com.xzst.relation.mp.http.ModelPageBean;
import com.xzst.relation.mp.http.ResultSetBean;
import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;
import com.xzst.relation.mp.service.HttpAPIService;
import com.xzst.relation.mp.util.DateUtils;
import com.xzst.relation.mp.util.JSONTools;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LHT on 2018/7/4.
 */
@RestController
@Api("kafka服务")
@RequestMapping("/kafka/")
public class KafkaController {
	private final Logger logger = Logger.getLogger(this.getClass());

	public static final String preTag = "<font weight=\'bold\' color=\'#FF0000\'>";
	public static final String endTag = "</font>";
	@Autowired
	private KafkaTemplate kafkaTemplate;

	private static int count=1;

	@Value("${spring.kafka.producer.topic}")
	private String topicName;

	@Autowired
	private HttpAPIService httpAPIService;

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	@ApiOperation(value = "kafka发送消息",notes = "")
	@ApiParam(required = true)
	@ApiImplicitParams(
			{
					@ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
			}
	)
	public BaseResponse sendKafka(@RequestParam String message) {
		List<Map<String,String>> data;
		try {
			for (int i = 0; i <1 ; i++) {
				ConsumerMessageBean consumerMessageBean=new ConsumerMessageBean();
				consumerMessageBean.setResultSetId("17");
				count++;
				Map<String,String> dataMap=new HashMap<>();
				dataMap.put("personName","李红涛");
				dataMap.put("personType","晴天");
				Date date=DateUtils.randomDate("2018-01-01","2018-08-01");
				dataMap.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
				dataMap.put("addName","摩托罗拉");
				dataMap.put("detailAdd","北京顺义后沙峪");
				dataMap.put("lon","116.2317");
				dataMap.put("lat","39.5427");
				dataMap.put("testTail","问候+++");
				consumerMessageBean.setDataMap(dataMap);
				logger.info(consumerMessageBean);
				kafkaTemplate.send(topicName, "key", JSONTools.javabean2Json(consumerMessageBean));
			}


//			logger.info("发送kafka成功.");
		} catch (Exception e) {
			logger.error("发送kafka失败", e);
			return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
		}
		BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("发送成功").build();
		return response;
	}



	/**
	 * 测试httpclient
	 * @return
	 */
	@RequestMapping(value = "testHttpClientGet" ,method = RequestMethod.POST)
	@ApiOperation(value = "测试httpclientGet", notes = "测试httpclientGet")
	@ApiImplicitParams(
			{
			}
	)
	@ApiResponses(value={@ApiResponse(code=201,message = "无数据"), @ApiResponse(code=202,message = "查询出现异常")})
	public BaseResponse testHttpClientGet(){

		int code=200;
		String message="查询成功";
		List<String> data=null;
		try {

			String keyword="11";
			String url="http://10.4.106.7:7047/task?taskId=";
			Map<String,String> headersMap=new HashMap<>();
			headersMap.put("Authorization","Basic bWFzdGVyOm1vZGVsX21hc3Rlcg==");
			headersMap.put("Content-Type","application/json; charset=utf-8");
			Map<String,List<Map<String,String>>> reMap=new LinkedHashMap<>();
			List<String> modelIdList=new ArrayList<>();
			modelIdList.add("9431");

			for (int i = 0; i <modelIdList.size() ; i++) {
				url=url+modelIdList.get(i);
				HttpResult result = httpAPIService.doGet(url,headersMap);
				if(result.getCode()==200){
					String dataString=result.getBody();
					if(StringUtils.isNotBlank(dataString)){
						ResultSetBean resultSetBean=JSON.parseObject(dataString,ResultSetBean.class);
						List<Map<String,String>> resulList=resultSetBean.getNodes();
						if(StringUtils.isNotBlank(keyword)){
							resulList.stream().
								filter(rowDataMap->rowDataMap.get("name").contains(keyword)).//过滤不含keyword的数据
								map(rowDataMap-> {
									String orignName=rowDataMap.get("name");
									String highLightName=highLightTranslate(orignName,keyword);
									rowDataMap.put("highLightName",highLightName);
									return rowDataMap;}).
								sorted((map1,map2)-> {return -(Integer.parseInt(map1.get("id"))-Integer.parseInt(map2.get("id")));} ).
								collect(Collectors.toList());
							reMap.put(modelIdList.get(i),resulList);
						}else{
							resulList.stream().
								map(rowDataMap-> {
									String orignName=rowDataMap.get("name");
									String highLightName=highLightTranslate(orignName,keyword);
									rowDataMap.put("highLightName",highLightName);
									return rowDataMap;}).
								sorted((map1,map2)-> {return -(Integer.parseInt(map1.get("id"))-Integer.parseInt(map2.get("id")));} ).
								collect(Collectors.toList());
							reMap.put(modelIdList.get(i),resulList);
						}


					}else{
						throw new Exception("url"+ url+"获取内容为空 请确认...");
					}
				}else{
					throw new Exception("url"+ url+"访问失败,失败代码:"+result.getCode()+" 请确认...");
				}

			}
			return BaseResponse.buildResponse().setObj(reMap).setCode(code).setMessage(message).build();
		} catch (Exception e) {
			logger.error(e.getStackTrace(),e);
			code=202;
			message="查询出现异常";
			return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
		}

	}


	/**
	 * 测试httpclient
	 * @return
	 */
	@RequestMapping(value = "testHttpClientGetCol" ,method = RequestMethod.POST)
	@ApiOperation(value = "测试httpclientGet", notes = "测试httpclientGet")
	@ApiImplicitParams(
			{
			}
	)
	@ApiResponses(value={@ApiResponse(code=201,message = "无数据"), @ApiResponse(code=202,message = "查询出现异常")})
	public BaseResponse testHttpClientGetCol(){

		int code=200;
		String message="查询成功";
		List<String> data=null;
		try {

			String url="http://10.4.106.7:7047/task/result/columns?resultId=146001";
			Map<String,String> headersMap=new HashMap<>();
			headersMap.put("Authorization","Basic bWFzdGVyOm1vZGVsX21hc3Rlcg==");
			headersMap.put("Content-Type","application/json; charset=utf-8");
			List<Map> resultSetColInfo=null;
			HttpResult result = httpAPIService.doGet(url,headersMap);
			if(result.getCode()==200){
				String dataString=result.getBody();
				if(StringUtils.isNotBlank(dataString)){
					resultSetColInfo=JSON.parseArray(dataString,Map.class);
				}else{
					throw new Exception("url"+ url+"获取内容为空 请确认...");
				}
			}else{
				throw new Exception("url"+ url+"访问失败,失败代码:"+result.getCode()+" 请确认...");
			}
			return BaseResponse.buildResponse().setObj(resultSetColInfo).setCode(code).setMessage(message).build();
		} catch (Exception e) {
			logger.error(e.getStackTrace(),e);
			code=202;
			message="查询出现异常";
			return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
		}

	}


	/**
	 * 测试httpclient
	 * @return
	 */
	@RequestMapping(value = "testHttpClientPost" ,method = RequestMethod.POST)
	@ApiOperation(value = "测试httpclientPost", notes = "测试httpclientPost")
	@ApiImplicitParams(
			{
			}
	)
	@ApiResponses(value={@ApiResponse(code=201,message = "无数据"), @ApiResponse(code=202,message = "查询出现异常")})
	public BaseResponse testHttpClientPost(){

		int code=200;
		String message="查询成功";
		List<String> data=null;
		try {
			Map<String,Object> paramsMap=new HashMap<>();
			paramsMap.put("pageNow",0);
			paramsMap.put("pageSize",10);
//			paramsMap.put("userId",0);
			logger.info(JSONTools.javabean2Json(paramsMap));
			Map<String,String> headersMap=new HashMap<>();
			headersMap.put("Authorization","Basic bWFzdGVyOm1vZGVsX21hc3Rlcg==");
			headersMap.put("Content-Type","application/json; charset=utf-8");

			HttpResult result = httpAPIService.doPost("http://10.4.106.7:7047/model/public",null,headersMap,JSONTools.javabean2Json(paramsMap));

			Map<String,Object> reMap=new LinkedHashMap<>();
			List<Map<String,String>> processedDataMapList=null;
			List<String> modelIdList=new ArrayList<>();
			String dataJson=result.getBody();
			ModelPageBean modelPageBean=JSONTools.string2JavaBean(dataJson,ModelPageBean.class);
			String keyword="用用";
//			logger.info(dataJson);
			if(result.getCode()==200){
				//获取name包含keyword的 model
				if(StringUtils.isNotBlank(keyword)){
					processedDataMapList=modelPageBean.getData().stream().
							filter(rowDataMap->rowDataMap.get("name").contains(keyword)).//过滤不含keyword的数据
							map(rowDataMap-> {
								String orignName=rowDataMap.get("name");
								String highLightName=highLightTranslate(orignName,keyword);
								rowDataMap.put("highLightName",highLightName);
								return rowDataMap;}).
							sorted((map1,map2)-> {return -(Integer.parseInt(map1.get("id"))-Integer.parseInt(map2.get("id")));} ).
							collect(Collectors.toList());
				}else{
					processedDataMapList=modelPageBean.getData().stream().
							map(rowDataMap-> {
								String orignName=rowDataMap.get("name");
								String highLightName=highLightTranslate(orignName,keyword);
								rowDataMap.put("highLightName",highLightName);
								return rowDataMap;}).
							sorted((map1,map2)-> {return -(Integer.parseInt(map1.get("id"))-Integer.parseInt(map2.get("id")));} ).
							collect(Collectors.toList());
				}
				//获取所有的modelid
				modelPageBean.getData().stream().peek(rowDataMap->{modelIdList.add(rowDataMap.get("id"));}).count();
				reMap.put("modelIdList",modelIdList);
				reMap.put("processedDataMapList",processedDataMapList);
			logger.info(processedDataMapList);
			}else{
				throw new Exception("模型组的url"+"访问失败,失败代码:"+result.getCode()+" 请确认...");
			}

			return BaseResponse.buildResponse().setObj(reMap).setCode(code).setMessage(message).build();
		} catch (Exception e) {
			logger.error(e.getStackTrace(),e);
			code=202;
			message="查询出现异常";
			return BaseResponse.buildResponse().setCode(code).setMessage(message).build();
		}

	}


	public String highLightTranslate(String orignStr,String keyword){
		if(null==keyword || "".equals(keyword.trim())){
			return orignStr;
		}
		return orignStr.replace(keyword,preTag+keyword+endTag);

	}
}
