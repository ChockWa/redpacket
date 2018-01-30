package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dao.UserMapper;
import com.hdh.redpacket.user.model.User;
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

    public List<User> getUserList(){
//        List<User> list = userMapper.getList();
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("aaaa");
        User user1 = new User();
        user1.setUserName("bbbb");
        list.add(user);
        list.add(user1);
        return list;
//        throw SysException.NEW_EXCEPTION_INSTANCE_FAILED;
    }

    public int getCount(){
        int count = userMapper.getCount();
        logger.debug("数量:{}",count);
        return count;
    }

    public User getByUserId(Long userId){
        if(userId == null) return null;
        return userMapper.getByUserId(userId);
    }
}
