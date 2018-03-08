package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.dto.UserPropertyDto;
import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.mapper.UserPropertyMapper;
import com.hdh.redpacket.user.model.UserProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
