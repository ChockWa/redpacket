package com.hdh.redpacket.common.service;

import com.hdh.redpacket.common.exception.CommonException;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.utils.DateUtils;
import com.hdh.redpacket.system.dto.AccessTokenDto;
import com.hdh.redpacket.system.service.AccessTokenService;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class SafeService {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public User checkLoginHandle(String accessToken, String requestMappingUrl){
        User user = null;
        boolean urlIsExist = false;
        Map<RequestMappingInfo, HandlerMethod> handlerMappingMap = requestMappingHandlerMapping.getHandlerMethods();
        a:for(Map.Entry<RequestMappingInfo, HandlerMethod> m : handlerMappingMap.entrySet()){
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            b:for(String pattern : patterns){
                if(pattern.equals(requestMappingUrl)){
                    if(checkLoginOrNot(method)){
                        urlIsExist = true;
                        Long userId = validateAccessToken(accessToken);
                        user = getUserByUserId(userId);
                        break a;
                    }
                }
            }
        }
        if(!urlIsExist){
            throw CommonException.METHOD_NOT_FIND_ERROR;
        }
        return user;
    }

    /**
     * 判断是否需要校验登录
     * @param handlerMethod
     * @return
     */
    private boolean checkLoginOrNot(HandlerMethod handlerMethod){
        if(handlerMethod == null){
            throw CommonException.METHOD_NOT_FIND_ERROR;
        }

        MustLogin methodMustLogin = handlerMethod.getMethodAnnotation(MustLogin.class);

        if(methodMustLogin == null){
            // 如果方法上没找到注解，则往类上找
            MustLogin beanMustLogin = handlerMethod.getBeanType().getAnnotation(MustLogin.class);
            // 没打注解默认为需要登录
            if(beanMustLogin == null || beanMustLogin.value()){
                return true;
            }
        }else{
            if(methodMustLogin.value()){
                return true;
            }
        }
        return false;
    }

    /**
     * 校验accessToken
     * @param accessToken
     * @return
     */
    private Long validateAccessToken(String accessToken){
        if(StringUtils.isBlank(accessToken)){
            throw CommonException.USER_NOT_LOGIN_ERROR;
        }
        AccessTokenDto accessTokenDto = accessTokenService.getAccessToken(accessToken);
        if(accessTokenDto == null){
            throw CommonException.USER_NOT_LOGIN_ERROR;
        }
        // 校验token是否过期
        if(DateUtils.greater(new Date(),accessTokenDto.getExpireTime())){
            throw CommonException.TOKEN_EXPIRE;
        }
        return accessTokenDto.getUserId();
    }

    private User getUserByUserId(Long userId){
        if(userId == null){
            return null;
        }
        return userService.getByUserId(userId);
    }
}
