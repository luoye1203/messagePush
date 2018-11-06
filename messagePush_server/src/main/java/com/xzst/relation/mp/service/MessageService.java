package com.xzst.relation.mp.service;

import com.alibaba.fastjson.JSON;
import com.xzst.relation.mp.dao.MessageDao;
import com.xzst.relation.mp.model.MessageDetail.MessageModel;
import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LHT on 2018/7/6.
 */
@Service
public class MessageService {
	private static  final Logger log=Logger.getLogger(MessageService.class);

	@Autowired
	private MessageDao messageDao;


	public void saveMeassge(MessageModel params){

		messageDao.insertMeassge2Database(params);
	}

	public List<MessageModel> messageList(){
		List<MessageModel> list=messageDao.messageList();
		for (MessageModel messageModel :list) {
			messageModel.setConsumerMessageBean(JSON.parseObject(messageModel.getMessage(), ConsumerMessageBean.class));
			messageModel.setMessage("清零");
		}

		return list;
	}

}
