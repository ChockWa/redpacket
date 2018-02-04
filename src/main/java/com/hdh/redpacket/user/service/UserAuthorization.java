package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dao.UserMapper;
import com.hdh.redpacket.user.dto.RegisterDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorization {

    @Autowired
    private UserMapper userMapper;

    public void register(RegisterDto registerDto){
        if(registerDto == null || StringUtils.isBlank(registerDto.getPassword()) ||
                (StringUtils.isBlank(registerDto.getEmail()) && StringUtils.isBlank(registerDto.getName()) ||
                StringUtils.isBlank(registerDto.getVerifyCode()))){
            throw UserException.PARAMS_ERROR;
        }

        // 检验图形验证码
        // 检验用户名是否存在
        if(StringUtils.isNotBlank(registerDto.getName())){
            User user = userMapper.getByName(registerDto.getName());
            if(user != null){
                throw UserException.NAME_ISEXIST_ERROR;
            }
        }
        // 检验邮箱是否已经存在
        if(StringUtils.isNotBlank(registerDto.getEmail())){
            User user = userMapper.getByEmail(registerDto.getEmail());
            if(user != null){
                throw UserException.EMAIL_ISEXIST_ERROR;
            }
        }
        // 初始化用户数据
        // 进行注册
    }
}
