package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.common.model.CommonData;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @MustLogin(false)
    @RequestMapping(value = "/getCount")
    @ResponseBody
    public Result getCount(@RequestParam Map map){
        int count = userService.getCount();
        throw UserException.USER_ISEXIST_ERROR;
//        return Result.SUCCESS.setData("count",count);
    }

}
