package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.core.annotation.MustLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SocketTestController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @MustLogin(false)
    public String socketTest(){
        return "/socketTest";
    }
}
