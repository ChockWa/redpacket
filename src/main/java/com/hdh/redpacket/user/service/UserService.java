package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public int getCount(){
        int count = userMapper.getCount();
        logger.debug("数量:{}",count);
        return count;
    }

}
