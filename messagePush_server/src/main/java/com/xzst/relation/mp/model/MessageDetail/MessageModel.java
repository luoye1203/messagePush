package com.xzst.relation.mp.model.MessageDetail;

import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;

import java.util.Date;

/**
 * Created by LHT on 2018/7/6.
 */
public class MessageModel {
	private String id;

	private String message;
	private ConsumerMessageBean consumerMessageBean;

	public ConsumerMessageBean getConsumerMessageBean() {
		return consumerMessageBean;
	}

	public void setConsumerMessageBean(ConsumerMessageBean consumerMessageBean) {
		this.consumerMessageBean = consumerMessageBean;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
