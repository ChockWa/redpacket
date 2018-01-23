package com.hdh.redpacket.user.dao;

import com.hdh.redpacket.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select id,user_name userName,password from user")
    List<User> getList();

}
