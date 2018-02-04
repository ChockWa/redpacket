//package com.hdh.redpacket.common.filter;
//
//import org.springframework.boot.context.embedded.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 过滤器配置
// */
//@Configuration
//public class FilterConfig {
//
//    /**
//     * 过滤器的注册
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean filterRegist() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new ModifyParametersFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }
//}
