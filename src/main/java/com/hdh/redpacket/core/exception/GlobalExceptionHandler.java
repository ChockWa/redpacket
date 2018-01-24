package com.hdh.redpacket.core.exception;


import com.hdh.redpacket.core.response.Result;
import com.hdh.redpacket.core.utils.ContextUtil;
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
            ContextUtil.getResult().setCode(((BizException) exception).code);
            ContextUtil.getResult().setMsg(((BizException) exception).msg);
            return ContextUtil.getResult();
        }
        ContextUtil.getResult().setMsg(exception.getMessage());
        return ContextUtil.getResult();
    }
}
