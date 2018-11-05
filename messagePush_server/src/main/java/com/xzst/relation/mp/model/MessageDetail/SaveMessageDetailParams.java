package com.xzst.relation.mp.model.MessageDetail;

import java.util.Date;

/**
 * Created by LHT on 2018/7/6.
 */
public class SaveMessageDetailParams {
	private String id;
	private String cofnigId;
	private String resultId;
	private String originalMessage;
	private String processedMessage;
	private String processedMessageMap;
	private String warningAddress;
	private String lat;
	private String lon;
	private String warningTime;
	private Date create_time;
	private String targetId;

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCofnigId() {
		return cofnigId;
	}

	public void setCofnigId(String cofnigId) {
		this.cofnigId = cofnigId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getOriginalMessage() {
		return originalMessage;
	}

	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	public String getProcessedMessage() {
		return processedMessage;
	}

	public void setProcessedMessage(String processedMessage) {
		this.processedMessage = processedMessage;
	}

	public String getProcessedMessageMap() {
		return processedMessageMap;
	}

	public void setProcessedMessageMap(String processedMessageMap) {
		this.processedMessageMap = processedMessageMap;
	}
}
