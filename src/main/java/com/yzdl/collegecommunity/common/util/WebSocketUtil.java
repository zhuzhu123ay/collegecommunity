package com.yzdl.collegecommunity.common.util;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint(value="/websockt/chat")
public class WebSocketUtil {
	/*private static final String GUEST_PREFIX="访客";
	private static final AtomicInteger connectionIds=new AtomicInteger(0);*/
	private static ConcurrentMap<String, WebSocketUtil> clientSet=new ConcurrentHashMap<String, WebSocketUtil>();
	private final String nickname;
	private Session session;
	public WebSocketUtil() {
		nickname=null;
	}
	@OnOpen
	public void start(Session session){
		this.session=session;
	}
	@OnMessage
	public void iscoming(String message){
		String msg[]=new String[4];
		msg = message.split(",");
		String nickname=msg[msg.length-1];
		//向谁发
		String publish_username=msg[1];
		System.out.println(publish_username);
		clientSet.put(msg[1],this);
		
		String filteredMessage=String.format("%s: %s", nickname,filter(msg[0]));
		broadcast(filteredMessage);
	}
	@OnClose
	public void end(){
		/*clientSet.remove(this);*/
	}
	@OnError
	public void onError(Throwable t) throws Throwable{
		System.out.println("webSocket服务端错误"+t);
	}
	
	private static void broadcast(String msg){
		/*for (WebSocketUtil client : clientSet) {
//			if(client.session.getId().equals())
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				} 
			}catch (IOException e) {
				System.out.println("聊天错误，向客户端"+client+"发送消息出现错误");
				clientSet.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {}
				String message=String.format("[%s %s]", client.nickname,"已经断开了连接");
				broadcast(message);
		}
	}*/
}
	private static String filter(String message){
		if(message==null)
			return null;
		char content[]=new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuilder result=new StringBuilder(content.length+50);
		for(int i=0;i<content.length;i++){
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());
	}
		
}
