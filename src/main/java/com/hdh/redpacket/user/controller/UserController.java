package com.hdh.redpacket.user.controller;

import com.hdh.redpacket.common.service.UserInfo;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.annotation.SecurityAccess;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.core.utils.DictUtils;
import com.hdh.redpacket.system.mapper.AccessTokenMapper;
import com.hdh.redpacket.system.model.AccessToken;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.user.dto.LoginDto;
import com.hdh.redpacket.user.dto.RegisterDto;
import com.hdh.redpacket.user.dto.UserPropertyDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.LoginService;
import com.hdh.redpacket.user.service.RegisterService;
import com.hdh.redpacket.user.service.UserPropertyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserPropertyService userPropertyService;

    @RequestMapping(value = "/getCount")
    @SecurityAccess(false)
    @MustLogin(false)
    public Result getCount(User user){
        Result result = Result.SUCCESS();
        List<ConfigDic> value = DictUtils.getDicList("gender");
        result.setData("value",value.size());
        return result;
//        throw UserException.USER_ISEXIST_ERROR;
    }

    /**
     * 注册
     * @param registerDto
     * @return
     */
    @RequestMapping(value = "/register")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result register(RegisterDto registerDto){
        registerService.register(registerDto);

        // 登录
        LoginDto loginDto = new LoginDto();
        loginDto.setPassword(registerDto.getPassword());
        if(StringUtils.isBlank(registerDto.getEmail())){
            loginDto.setAccount(registerDto.getName());
        }else{
            loginDto.setAccount(registerDto.getEmail());
        }
        return Result.SUCCESS().setData(loginService.login(loginDto));
    }

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @RequestMapping(value = "/login")
    @MustLogin(false)
    @SecurityAccess(true)
    public Result login(LoginDto loginDto){
        return Result.SUCCESS().setData(loginService.login(loginDto));
    }

    /**
     * 获取用户属性
     * @return
     */
    @RequestMapping(value = "/getUserProperties")
    @SecurityAccess(true)
    public Result getUserProperties(){
        UserPropertyDto userPropertyDto = userPropertyService.getUserProperties(UserInfo.getUser().getId());
        return Result.SUCCESS().setData(userPropertyDto);
    }

}
