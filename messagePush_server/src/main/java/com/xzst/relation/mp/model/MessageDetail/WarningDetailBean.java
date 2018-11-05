package com.xzst.relation.mp.model.MessageDetail;

import java.util.Map;

/**
 * Created by LHT on 2018/7/11.
 */
public class WarningDetailBean {
	private String id;
	private String typeName;
	private String createrName;
	private String isRead;
	private String createTime;
	private String processedMessage;
	private String processedMessageMapStr;
	private String warningAddress;
	private String lat;
	private String lon;
	private String targetId;
	private Map<String,String> processedMessageMap;

	public Map<String, String> getProcessedMessageMap() {
		return processedMessageMap;
	}

	public void setProcessedMessageMap(Map<String, String> processedMessageMap) {
		this.processedMessageMap = processedMessageMap;
	}

	public String getWarningAddress() {
		return warningAddress;
	}

	public void setWarningAddress(String warningAddress) {
		this.warningAddress = warningAddress;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getProcessedMessage() {
		return processedMessage;
	}

	public void setProcessedMessage(String processedMessage) {
		this.processedMessage = processedMessage;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getProcessedMessageMapStr() {
		return processedMessageMapStr;
	}

	public void setProcessedMessageMapStr(String processedMessageMapStr) {
		this.processedMessageMapStr = processedMessageMapStr;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
}
