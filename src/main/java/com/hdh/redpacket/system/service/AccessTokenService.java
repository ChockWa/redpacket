package com.hdh.redpacket.system.service;

import com.hdh.redpacket.core.utils.BeanUtils;
import com.hdh.redpacket.core.utils.UuidUtil;
import com.hdh.redpacket.system.dao.AccessTokenMapper;
import com.hdh.redpacket.system.dto.AccessTokenDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccessTokenService {

    public static final String DEFAULTE_TOKEN_NAME = "accessToken";

    private final static long TOKEN_EXPIRE_TIME = 1 * 24 * 60 * 60; // 会话失效时间(1天).

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    /**
     * 查找accessToken
     * @param accessToken
     * @return
     */
    public AccessTokenDto getAccessToken(String accessToken){
        if(StringUtils.isBlank(accessToken)){
            return null;
        }

        return BeanUtils.copyToNewBean(accessTokenMapper.getByAccessToken(accessToken),AccessTokenDto.class);
    }

    /**
     * 生成token
     * @return
     */
    private String genToken(){
        return UuidUtil.shortUuid() + UuidUtil.shortUuid();
    }

    /**
     * 获取有效时间
     * @return
     */
    public Date getExpireTime() {
        return new Date(System.currentTimeMillis() + (TOKEN_EXPIRE_TIME * 1000));
    }
}
