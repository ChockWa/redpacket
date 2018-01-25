package com.hdh.redpacket.core.exception;


import com.hdh.redpacket.core.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public Result exceptionHandle(HttpServletRequest request, Exception exception) throws Exception{

        if(exception instanceof BizException){
            return Result.FAIL(((BizException) exception).code,((BizException) exception).msg);
        }else{
            BizException sysException = SysException.SYS_ERROR;
            logger.error("系统内部错误:" + exception.toString(),exception);
            return Result.FAIL(sysException.code,sysException.msg);
        }
    }
}
