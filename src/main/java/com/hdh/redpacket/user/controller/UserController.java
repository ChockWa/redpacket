package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.core.response.ResponseEntity;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/home")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public ResponseEntity getUserList(){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode("0");
        responseEntity.setData(userService.getUserList());
        return responseEntity;
    }
}
