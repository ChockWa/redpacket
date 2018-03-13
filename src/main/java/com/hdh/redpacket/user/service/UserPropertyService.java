package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dto.UserPropertyDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.mapper.UserPropertyMapper;
import com.hdh.redpacket.user.model.UserProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户属性相关逻辑
 */
@Service
public class UserPropertyService {

    @Autowired
    private UserPropertyMapper userPropertyMapper;

    /**
     * 根据用户id获取用户属性
     * @param userId
     * @return
     */
    public UserPropertyDto getUserProperties(String userId){
        if(StringUtils.isBlank(userId)){
            return null;
        }

        return userPropertyMapper.getUserPropertiesByUserId(userId);
    }

    /**
     * 插入用户属性
     */
    public void addUserProperty(UserProperty userProperty){
        if(userProperty == null){
            return;
        }

        userPropertyMapper.insert(userProperty);
    }

    /**
     * 更新用户属性
     * @param userProperty
     */
    public void updateUserProperty(UserProperty userProperty){
        if(userProperty == null || StringUtils.isBlank(userProperty.getUserId())){
            throw UserException.PARAMS_ERROR;
        }

        userPropertyMapper.updateByUserIdSelective(userProperty);
    }

    /**
     * 根据userId列表查询用户属性列表
     * @param userIds
     * @return
     */
    public List<UserProperty> selectUserProByUserIds(List<String> userIds){
        if(userIds == null || userIds.size() < 1){
            return new ArrayList<>();
        }
        return userPropertyMapper.selectUserProByUserIds(userIds);
    }
}
