package com.hdh.redpacket.core.exception;


import com.hdh.redpacket.core.response.Result;
import com.hdh.redpacket.core.utils.SpringContextUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public Result exceptionHandle(HttpServletRequest request, Exception exception) throws Exception{

        if(exception instanceof BizException){
            SpringContextUtil.getResult().setCode(((BizException) exception).code);
            SpringContextUtil.getResult().setMsg(((BizException) exception).msg);
            return SpringContextUtil.getResult();
        }
        SpringContextUtil.getResult().setMsg(exception.getMessage());
        return SpringContextUtil.getResult();
    }
}
