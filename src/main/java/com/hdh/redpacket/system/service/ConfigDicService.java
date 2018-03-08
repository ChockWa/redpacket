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
     * 根据code获取字典列表
     * @param code
     * @return
     */
    public List<ConfigDic> getDicsByCode(String code){
        if(StringUtils.isBlank(code)){
            return new ArrayList<>();
        }

        List<ConfigDic> list = configDicMapper.getByCode(code);
        return list;
    }

    public ConfigDic getDicByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }

        List<ConfigDic> list = configDicMapper.getByCode(code);
        return list != null && list.size() == 1 ? list.get(0) : null;
    }


}
