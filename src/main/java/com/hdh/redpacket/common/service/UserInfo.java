package com.hdh.redpacket.common.service;

import com.hdh.redpacket.core.spring.SpringUtil;
import com.hdh.redpacket.system.service.AccessTokenService;
import com.hdh.redpacket.user.model.User;
import org.apache.commons.lang3.StringUtils;

public class UserInfo {

    private static final ThreadLocal<User> userInfo = new ThreadLocal<>();

    public static void setUserData(String accessToken){
        if(StringUtils.isBlank(accessToken)){
            return;
        }
        AccessTokenService accessTokenService = SpringUtil.getBean(AccessTokenService.class);
        if(accessTokenService == null){
            accessTokenService = new AccessTokenService();
        }
        userInfo.set(accessTokenService.getUserByToken(accessToken));
    }

    public static User getUser(){
        return userInfo.get();
    }

}
