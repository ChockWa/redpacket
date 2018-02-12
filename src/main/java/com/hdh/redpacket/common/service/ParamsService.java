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
        Map<String,String[]> reqMap = request.getParameterMap();
        for(Map.Entry<String,String[]> entry : reqMap.entrySet()){
            String[] values = entry.getValue();
            if(values != null && values.length > 0){
                map.put(entry.getKey(),values[0]);
            }
        }

        return map;
    }
}
