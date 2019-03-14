package com.hdh.redpacket.game.controller;

import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.system.socket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class BeginController {

    private static final Logger logger = LoggerFactory.getLogger(BeginController.class);

    @RequestMapping("/socketTest")
    @MustLogin(false)
    public String enterPage(){
        return "socketTest";
    }

    @PostMapping(value = "/")
    @ResponseBody
    public String  test(@RequestBody String json){
        pushGamePlayMsg(json + "------Time:" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        logger.info("接收到的message:{}",json);
        return "ok";
    }

    private void pushGamePlayMsg(String message){
        CopyOnWriteArraySet<WebSocket> webSockets = WebSocket.webSocketSet;
        for(WebSocket webSocket : webSockets){
            try {
                webSocket.sendMessage(message);
            } catch (IOException e) {
                logger.error("socket通讯失败，游戏结果信息推送失败:{}",message,e);
            }
        }
    }
}
