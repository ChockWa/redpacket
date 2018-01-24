package com.hdh.redpacket.core.exception;


import com.hdh.redpacket.core.response.Result;
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
            return Result.FAIL(((BizException) exception).code,((BizException) exception).msg);
        }else{
            BizException sysException = SysException.SYS_ERROR;
            return Result.FAIL(sysException.code,sysException.msg);
        }
    }
}
