package com.hdh.redpacket.common.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParamsService {

    /**
     * 处理request中的参数
     * @param request
     * @return
     */
    public Map<String,Object> dueRequestParams(HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();
        for(Map.Entry entry : request.getParameterMap().entrySet()){
            map.put(entry.getKey().toString(),entry.getValue());
        }

        return map;
    }
}
