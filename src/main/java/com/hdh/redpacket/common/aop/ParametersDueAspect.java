//package com.hdh.redpacket.common.aop;
//
//import com.hdh.redpacket.core.common.ParameterRequestWrapper;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
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
//public class ParametersDueAspect {
//
//    @Pointcut("execution(* com.hdh.redpacket.*.controller.*Controller.*(..))")
//    public void executeService() {
//    }
//
//    /**
//     * 获取request的参数
//     * @param proceedingJoinPoint
//     * @return
//     * @throws Throwable
//     */
//    @Around("executeService()")
//    public Object doBeforeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) ra;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        Object result = proceedingJoinPoint.proceed(new Object[]{((ParameterRequestWrapper) request).getParameters()});
//        return result;
//    }
//}
