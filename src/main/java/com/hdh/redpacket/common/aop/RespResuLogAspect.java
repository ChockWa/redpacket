//package com.hdh.redpacket.common.aop;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.hdh.redpacket.user.model.User;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Configuration
//public class RespResuLogAspect {
//
//    @Pointcut("execution(* com.hdh.redpacket.*.controller.*Controller.*(..))")
//    public void executeLogService(){
//    }
//
//    @Around("executeLogService()")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) ra;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        User user = new User();
//        user.setId(1L);
//        user.setName("aaaa");
//        return user;
//    }
//}
