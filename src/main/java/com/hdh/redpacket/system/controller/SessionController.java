package com.hdh.redpacket.system.controller;

import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.system.dto.SessionDto;
import com.hdh.redpacket.system.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * session会话控制器
 */
@RestController
@RequestMapping("/api.do")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping("/genSession")
    @ResponseBody
    @MustLogin(false)
    public Result genSession(){
        SessionDto sessionDto = sessionService.genSession();
        return Result.SUCCESS().setData(sessionDto);
    }
}
