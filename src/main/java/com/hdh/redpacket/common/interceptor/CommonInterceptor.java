package com.hdh.redpacket.common.interceptor;

import com.hdh.redpacket.common.service.InvokeService;
import com.hdh.redpacket.common.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Autowired
    private InvokeService invokeService;

    @Autowired
    private ParamsService paramsService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 处理request的参数
        Map<String,Object> dataMap = paramsService.dueRequestParams(httpServletRequest);
        // 安全校验
        invokeService.checkSaftHandle(httpServletRequest.getRequestURI(),dataMap);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        System.out.println("------------"+httpServletRequest.getRequestURI());
    }
}
