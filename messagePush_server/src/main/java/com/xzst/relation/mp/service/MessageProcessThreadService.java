package com.xzst.relation.mp.service;


import com.alibaba.fastjson.JSON;
import com.xzst.relation.mp.model.MessageDetail.MessageModel;
import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by LHT on 2018/7/5.
 */
@Service
public class MessageProcessThreadService {
    private static Logger LOG = Logger.getLogger(MessageProcessThreadService.class);

    @Autowired
    private MessageService messageService;


    //采用异步多线程
    @Async
    public void processData(ConsumerMessageBean consumerMessageBean) {

        /**
         * 异步线程是否启用测试代码
         * 测试通过注释掉
         */
//		try {
//			int random=((int)(Math.random()*10))*1000;
//			LOG.info(random);
//			Thread.sleep(random);
//		} catch (InterruptedException e) {
//			LOG.error(e.getMessage());
//		}
//		LOG.info(consumerMessageBean);
        try {
            String message= JSON.toJSONString(consumerMessageBean);
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            MessageModel params=new MessageModel();
            params.setId(uuid);
            params.setMessage(message);
            messageService.saveMeassge(params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
