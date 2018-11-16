package com.xzst.relation.mp.webSocket;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

//
@ServerEndpoint(value = "/chat", configurator = WebSocketConfig.class)
@Component
public class WebSocket {
	private final static Logger LOG=Logger.getLogger(WebSocket.class);

	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	private static final AtomicInteger onlineCountNew = new AtomicInteger(1);

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * <p>
	 * config用来获取WebsocketConfig中的配置信息
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {

		//获取WebsocketConfig.java中配置的“sessionId”信息值
		String httpSessionId=session.getUserProperties().get("sessionId").toString();
		String clientIP= session.getUserProperties().get("clientIP").toString();
		String sessionID=session.getId();
		onlineCountNew.incrementAndGet();

		this.session = session;
		webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		LOG.info("有新连接加入 ip:"+clientIP +" 会话id :"+sessionID);
		LOG.info("当前在线人数为" + getOnlineCount());
		try {
			sendMessage("你好!已经握手成功,开始通信....");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);  //从set中删除
		subOnlineCount();           //在线数减1
		LOG.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息:" + message);
		String clientIP=session.getUserProperties().get("clientIP").toString();
		String sessionId=session.getId();


		try {
			this.sendMessage("来自 "+clientIP+"会话id:"+sessionId+"消息已经收到:"+message);
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		LOG.info("sessionId:"+session.getId()+"发生错误,请查证");
		LOG.error(error.getMessage());
	}


	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfoToAllSession(String message) {
		for (WebSocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (Exception e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocket.onlineCount--;
	}
}