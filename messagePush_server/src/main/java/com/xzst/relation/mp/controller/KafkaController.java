package com.xzst.relation.mp.controller;

import com.xzst.relation.mp.model.BaseResponse;
import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;
import com.xzst.relation.mp.service.MessageService;
import com.xzst.relation.mp.util.DateUtils;
import com.xzst.relation.mp.util.JSONTools;
import io.swagger.annotations.*;
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

/**
 * Created by LHT on 2018/7/4.
 */
@RestController
@Api("kafka服务")
@RequestMapping("/kafka/")
public class KafkaController {
	private final Logger logger = Logger.getLogger(this.getClass());


	@Autowired
	private KafkaTemplate kafkaTemplate;

	@Autowired
    private MessageService messageService;

	private static int count=1;

	@Value("${spring.kafka.producer.topic}")
	private String topicName;


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
			for (int i = 0; i <1 ; i++) {//多次发送
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


			logger.info("发送kafka成功.");
		} catch (Exception e) {
			logger.error("发送kafka失败", e);
			return BaseResponse.buildResponse().setCode(201).setMessage("发送失败").build();
		}
		BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("发送成功").build();
		return response;
	}


    @RequestMapping(value = "/messageList", method = RequestMethod.GET)
    @ApiOperation(value = "查看接收处理到的消息",notes = "")
    @ApiImplicitParams(
            {
            }
    )
    public BaseResponse messageList() {
	    List list=null;
       try {
           list=messageService.messageList();
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return BaseResponse.buildResponse().setCode(201).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj(list).setCode(200).setMessage("发送成功").build();
        return response;
    }


}
