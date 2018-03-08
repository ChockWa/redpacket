package com.hdh.redpacket.user.service;

import com.hdh.redpacket.user.exception.UserException;
import com.hdh.redpacket.user.mapper.PropertyLogMapper;
import com.hdh.redpacket.user.model.PropertyLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PropertyLogService {

    @Autowired
    private PropertyLogMapper propertyLogMapper;

    /**
     * 插入用户属性变化日志
     * @param userId
     * @param propertyType
     * @param oldValue
     * @param newValue
     * @param changeType
     * @param createTime
     */
    public void addPropertyLog(String userId, String propertyType, BigDecimal oldValue,BigDecimal newValue,
                               Integer changeType,Date createTime){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(propertyType) || oldValue == null || newValue == null
                || changeType == null || createTime == null){
            throw UserException.PARAMS_ERROR;
        }

        PropertyLog propertyLog = new PropertyLog();
        propertyLog.setChangeType(changeType);
        propertyLog.setCreateTime(createTime);
        propertyLog.setNewValue(newValue);
        propertyLog.setOldValue(oldValue);
        propertyLog.setUserId(userId);
        propertyLogMapper.insert(propertyLog);
    }
}
