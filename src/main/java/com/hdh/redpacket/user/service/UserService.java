package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dao.UserMapper;
import com.hdh.redpacket.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long userId){
        if(userId == null){
            return null;
        }
        return userMapper.selectById(userId);
    }
}
