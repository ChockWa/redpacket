package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.common.service.UserInfo;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.annotation.SecurityAccess;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.user.dto.LoginDto;
import com.hdh.redpacket.user.dto.RegisterDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.LoginService;
import com.hdh.redpacket.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/getCount")
    @ResponseBody
    @SecurityAccess(false)
    public Result getCount(User user){
        Result result = Result.SUCCESS();
        User user1 = UserInfo.getUser();
        result.setData(user1);
//        return result;
        throw UserException.USER_ISEXIST_ERROR;
    }

    /**
     * 注册
     * @param registerDto
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    @MustLogin(false)
    public Result register(RegisterDto registerDto){
        registerService.register(registerDto);
        return Result.SUCCESS();
    }

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    @MustLogin(false)
    public Result login(LoginDto loginDto){
        return Result.SUCCESS().setData(loginService.login(loginDto));
    }

}
