package com.hdh.redpacket.common.config;

import com.hdh.redpacket.common.interceptor.CommonInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public CommonInteceptor commonInteceptor(){
        return new CommonInteceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(commonInteceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
