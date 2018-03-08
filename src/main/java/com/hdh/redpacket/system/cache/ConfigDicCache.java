package com.hdh.redpacket.system.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hdh.redpacket.core.spring.SpringUtil;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.service.ConfigDicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 字典缓存
 */
public class ConfigDicCache {

    private static final Logger logger = LoggerFactory.getLogger(ConfigDicCache.class);

    private static LoadingCache<String,List<ConfigDic>> listConfigItemCache = CacheBuilder
            .newBuilder()
            .maximumSize(1024)
            .expireAfterWrite(15,TimeUnit.SECONDS)
            .build(new CacheLoader<String, List<ConfigDic>>() {
                @Override
                public List<ConfigDic> load(String code) throws Exception {
                    ConfigDicService configDicService = SpringUtil.getBean(ConfigDicService.class);
                    return configDicService.getDicsByCode(code);
                }
            });

    public static List<ConfigDic> getConfigDics(String code){
        if(StringUtils.isBlank(code)){
            return new ArrayList<>();
        }
        try {
            return listConfigItemCache.get(code);
        } catch (ExecutionException e) {
            logger.error("获取字典列表失败,code:{}",code,e);
        }
        return null;
    }

    public static ConfigDic getConfigDic(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }

        try {
            List<ConfigDic> list = listConfigItemCache.get(code);
            if(list != null && list.size() == 1){
                return list.get(0);
            }
        } catch (ExecutionException e) {
            logger.error("获取字典列表失败,code:{}",code,e);
        }
        return null;
    }

    public static void clearListDictCache(String code) {
        listConfigItemCache.invalidate(code);
    }
}
