package com.xzst.relation.mp.kafka;

/**
 * Created by LHT on 2018/7/5.
 */


import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;
import com.xzst.relation.mp.service.MessageProcessThreadService;
import com.xzst.relation.mp.util.JSONTools;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MessageProcessThreadService messageProcessThreadService;

	@KafkaListener(topics = {"warning_new"})
	public void kafkaConsumerListenterMethod(ConsumerRecord<?, ?> record) {

		if(record!=null&&record.value()!=null&&!record.value().toString().trim().equals("")){
			String message=  record.value().toString();
			try {
//				logger.info("消费的topic为:  "+record.topic());
//				logger.info("消费的topic为:  "+record);
				ConsumerMessageBean messageBean= JSONTools.string2JavaBean(message,ConsumerMessageBean.class);
				messageProcessThreadService.processData(messageBean);
			} catch (Exception e) {
				logger.error("data为:"+ message+ "  数据格式无效,请查证...");
			}

		}else{
			logger.error("接收到空消息,摒弃...");
		}

	}





}