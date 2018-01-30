package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Result getUserList(){
        List<User> list = userService.getUserList();
        Result result = Result.SUCCESS.setData("list",list);
        return result;
    }

    @RequestMapping(value = "/getCount")
    @ResponseBody
    public Result getCount(){
        int count = userService.getCount();
        return Result.SUCCESS.setData("count",count);
    }

}
