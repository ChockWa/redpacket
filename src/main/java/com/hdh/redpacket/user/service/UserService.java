package com.hdh.redpacket.user.service;

import com.hdh.redpacket.core.exception.SysException;
import com.hdh.redpacket.user.dao.UserMapper;
import com.hdh.redpacket.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

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
        System.out.println(1);
        return userMapper.getCount();
    }
}
