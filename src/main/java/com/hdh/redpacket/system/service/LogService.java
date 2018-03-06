package com.hdh.redpacket.system.service;

import com.hdh.redpacket.system.mapper.LogMapper;
import com.hdh.redpacket.system.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 记录请求日志
     * @param log
     */
    public void addRequestLog(Log log){
        if(log == null){
            return;
        }

        logMapper.insert(log);
    }

}
