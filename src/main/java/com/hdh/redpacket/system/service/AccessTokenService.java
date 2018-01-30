package com.hdh.redpacket.system.service;

import com.hdh.redpacket.core.utils.BeanUtils;
import com.hdh.redpacket.system.dao.AccessTokenMapper;
import com.hdh.redpacket.system.dto.AccessTokenDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {

    public static final String DEFAULTE_TOKEN_NAME = "accessToken";

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
}
