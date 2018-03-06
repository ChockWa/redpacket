package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.mapper.UserMapper;
import com.hdh.redpacket.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userId){
        if(StringUtils.isBlank(userId)){
            return null;
        }
        return userMapper.selectById(userId);
    }
}
