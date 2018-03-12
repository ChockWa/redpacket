package com.hdh.redpacket.user.service;

import com.hdh.redpacket.system.socket.WebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class PushTask {

    @Scheduled(cron = "0 12 * * * *")
    public void pushTest(){
        CopyOnWriteArraySet<WebSocket> socketSets = WebSocket.webSocketSet;
        if(socketSets.isEmpty()){
            return;
        }
        for(WebSocket webSocket : socketSets){
            try {
                webSocket.sendMessage("hahahahahahah");
                System.out.println("往客户端推送信息:hahahahahahah");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
