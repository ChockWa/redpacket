package com.hdh.redpacket.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>{

    @Select("select id,user_name userName,password from user")
    List<User> getList();

    /**
     * 根据条件查询用户
     * @param user
     * @return
     */
    User getByQuery(User user);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    User getByEmail(@Param("email")String email);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User getByName(@Param("name")String name);

    /**
     * 获取总用户数量
     * @return
     */
    long getCount();

    /**
     * 获取最大的id值
     * @return
     */
    long getMaxId();
}
