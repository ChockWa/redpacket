package com.hdh.redpacket.core.utils;

import com.hdh.redpacket.system.cache.ConfigDicCache;
import com.hdh.redpacket.system.model.ConfigDic;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典工具类
 */
public class DictUtils {

    /**
     * 根据字典类型获取字典列表
     * @param code
     * @return
     */
    public static List<ConfigDic> getDicList(String code){
        if (StringUtils.isBlank(code)) return new ArrayList<>();

        return ConfigDicCache.getConfigDics(code);
    }

    /**
     * 根据字典code获取字典
     * @param code
     * @return
     */
    public static ConfigDic getDic(String code){
        if (StringUtils.isBlank(code)) return null;

        return ConfigDicCache.getConfigDic(code);
    }
}
