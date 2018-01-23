package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.core.response.ResponseEntity;
import com.hdh.redpacket.core.response.Result;
import com.hdh.redpacket.core.utils.SpringContextUtil;
import com.hdh.redpacket.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/home")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Result getUserList(){
        Result result = SpringContextUtil.getResult();
        result.setData(userService.getUserList());
        return result;
    }
}
