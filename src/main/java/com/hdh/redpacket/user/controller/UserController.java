package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.cache.RedisService;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@MustLogin(false)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "/getCount")
    @ResponseBody
    public Result getCount(@RequestParam Map map){
//        int count = userService.getCount();
        throw UserException.USER_ISEXIST_ERROR;
//        return Result.SUCCESS.setData("count",count);
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Result test() throws InterruptedException {
        redisService.set("name","bbbbb",3000);
        Thread.sleep(1000);
        System.out.println(redisService.get("name"));
        return Result.SUCCESS;
    }

}
