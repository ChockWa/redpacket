package com.hdh.redpacket.system.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/beginGameSocket")
@Component
public class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    private static int onlineCount = 0;

    public static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen (Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        logger.info("有新链接加入!当前在线人数为:{}",getOnlineCount());
    }

    @OnClose
    public void onClose (){
        webSocketSet.remove(this);
        subOnlineCount();
        logger.info("有一链接关闭!当前在线人数为:{}",getOnlineCount());
    }

    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        logger.info("来自前端的消息:{}",message);
        // 群发消息
        for ( WebSocket item : webSocketSet ){
            item.sendMessage(message);
        }
    }

    public void sendMessage (String message) throws IOException {
        logger.info("后台socket往前端推送消息:{}",message);
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized  int getOnlineCount (){
        return WebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        WebSocket.onlineCount--;
    }

}
