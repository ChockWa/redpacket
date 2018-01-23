package com.hdh.redpacket.core.utils;

import com.hdh.redpacket.core.response.Result;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static Result result;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        result = new Result(Result.SUCCESS_CODE);
    }

    public static Result getResult() {
        return result;
    }

}
