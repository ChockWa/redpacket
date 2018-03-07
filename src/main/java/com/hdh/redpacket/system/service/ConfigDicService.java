package com.hdh.redpacket.system.service;

import com.hdh.redpacket.system.mapper.ConfigDicMapper;
import com.hdh.redpacket.system.model.ConfigDic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigDicService {

    @Autowired
    private ConfigDicMapper configDicMapper;

    /**
     * 获取所有字典项
     * @return
     */
    public List<ConfigDic> getAllConfigDic(){
        return configDicMapper.getAll();
    }

    /**
     * 根据code获取字典
     * @param code
     * @return
     */
    public ConfigDic getByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }

        return configDicMapper.getByCode(code);
    }

    /**
     * 根据字典类型获取字典列表
     * @param type
     * @return
     */
    public List<ConfigDic> getByDicType(Integer type){
        if(type == null){
            return new ArrayList<>();
        }

        return configDicMapper.getByDicType(type);
    }
}
