package com.hdh.redpacket.system.service;

import com.hdh.redpacket.system.mapper.TaskCronMapper;
import com.hdh.redpacket.system.model.TaskCron;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCronService {

    @Autowired
    private TaskCronMapper taskCronMapper;

    public TaskCron getByType(String type){
        if(StringUtils.isBlank(type)){
            return null;
        }
        return taskCronMapper.getByType(type);
    }
}


