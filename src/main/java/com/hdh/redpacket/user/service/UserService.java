package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dao.UserMapper;
import com.hdh.redpacket.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        List<User> list = userMapper.getList();
        throw new RuntimeException("出错啦！！");
    }
}
