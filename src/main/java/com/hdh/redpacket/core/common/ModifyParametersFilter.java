//package com.hdh.redpacket.core.common;
//
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//
///**
// * 修改request请求参数的过滤器
// */
//public class ModifyParametersFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        // 修改request请求的参数
//        modefyRequestParams(httpServletRequest,httpServletResponse,filterChain);
//    }
//
//    /**
//     * 修改request请求的参数
//     * @param httpServletRequest
//     * @param httpServletResponse
//     * @param filterChain
//     * @throws IOException
//     * @throws ServletException
//     */
//    private void modefyRequestParams(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain) throws IOException, ServletException {
//        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper(httpServletRequest,new HashMap<>(httpServletRequest.getParameterMap()));
//        parameterRequestWrapper.addParameter("requestUri",httpServletRequest.getRequestURI());
//        filterChain.doFilter(parameterRequestWrapper,httpServletResponse);
//    }
//}
