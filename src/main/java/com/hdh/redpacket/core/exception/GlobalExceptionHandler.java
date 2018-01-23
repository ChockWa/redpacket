package com.hdh.redpacket.core.exception;


import com.hdh.redpacket.core.response.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity exceptionHandle(HttpServletRequest request, Exception exception) throws Exception{

        if(exception != null){
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.setMsg(exception.getMessage());
            return responseEntity;
        }
        return new ResponseEntity();
    }
}
