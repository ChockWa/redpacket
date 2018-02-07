package com.hdh.redpacket.common.service;

import com.hdh.redpacket.common.exception.CommonException;
import com.hdh.redpacket.common.model.CommonData;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.annotation.SecurityAccess;
import com.hdh.redpacket.core.utils.DateUtils;
import com.hdh.redpacket.system.dto.AccessTokenDto;
import com.hdh.redpacket.system.dto.SessionDto;
import com.hdh.redpacket.system.service.AccessTokenService;
import com.hdh.redpacket.system.service.SessionService;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Service
public class InvokeService {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SignService signService;

    /**
     * 检查是否登录操作
     * @param dataMap
     */
    public void checkSaftHandle(String requestUri, Map<String,Object> dataMap){
        boolean urlIsExist = false;
        Map<RequestMappingInfo, HandlerMethod> handlerMappingMap = requestMappingHandlerMapping.getHandlerMethods();
        a:for(Map.Entry<RequestMappingInfo, HandlerMethod> m : handlerMappingMap.entrySet()){
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            b:for(String pattern : patterns){
                String accessToken = dataMap.get(AccessTokenService.DEFAULTE_TOKEN_NAME) == null ? "" : dataMap.get(AccessTokenService.DEFAULTE_TOKEN_NAME).toString();
                String sessionKey = dataMap.get("sessionKey") == null ? "" : dataMap.get("sessionKey").toString();
                if(pattern.equals(requestUri)){
                    // 校验是否登录
                    if(checkLoginOrNot(method)){
                        validateAccessToken(accessToken);
                    }
                    // 安全校验
//                    if(checkSecurityAccessOrNot(method)){
//                        checkSecurityAccess(sessionKey,dataMap);
//                    }
                    urlIsExist = true;
                    break a;
                }
            }
        }
        if(!urlIsExist){
            throw CommonException.METHOD_NOT_FIND_ERROR;
        }
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
    private void validateAccessToken(String accessToken){
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
    }

    /**
     * 安全校验
     * @param sessionKey
     * @param dataMap
     */
    public void checkSecurityAccess(String sessionKey,Map<String,Object> dataMap){
        if(StringUtils.isBlank(sessionKey) || dataMap == null || dataMap.size() < 1){
            throw CommonException.SESSION_ISNULL_ERROR;
        }

        SessionDto sessionDto = sessionService.getSession(sessionKey);

        // 校验会话
        validateSession(sessionDto);

        // 校验签名
        signService.checkSign(sessionDto.getSecretKey(),dataMap);
    }

    /**
     * 校验会话
     * @param sessionDto
     */
    private void validateSession(SessionDto sessionDto){
        if(sessionDto == null){
            throw CommonException.SESSION_ISNULL_ERROR;
        }

        if(DateUtils.greater(new Date(),sessionDto.getExpireTime())){
            throw CommonException.SESSION_ISOVERTIME_ERROR;
        }
    }

    /**
     * 判断是否需要安全校验
     * @param handlerMethod
     * @return
     */
    private boolean checkSecurityAccessOrNot(HandlerMethod handlerMethod){
        if(handlerMethod == null){
            throw CommonException.METHOD_NOT_FIND_ERROR;
        }

        SecurityAccess securityAccess = handlerMethod.getMethodAnnotation(SecurityAccess.class);

        if(securityAccess == null){
            // 如果方法上没找到注解，则往类上找
            SecurityAccess beanSecurityAccess = handlerMethod.getBeanType().getAnnotation(SecurityAccess.class);
            // 没打注解默认为需要安全校验
            if(beanSecurityAccess == null || beanSecurityAccess.value()){
                return true;
            }
        }else{
            if(securityAccess.value()){
                return true;
            }
        }
        return false;
    }

}
