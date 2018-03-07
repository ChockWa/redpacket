package com.hdh.redpacket.system.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.hdh.redpacket.core.spring.SpringUtil;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.service.ConfigDicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 字典缓存
 */
public class ConfigDicCache {

    private static final Logger logger = LoggerFactory.getLogger(ConfigDicCache.class);

    private static LoadingCache<String,List<ConfigDic>> listConfigItemCache;

    /**
     * 获取根据type类别分的字典
     */
    private static void buildListConfigItemCache(){
        listConfigItemCache = CacheBuilder.newBuilder().maximumSize(1024)
                .refreshAfterWrite(7, TimeUnit.DAYS)
                .build(new CacheLoader<String, List<ConfigDic>>() {
                    @Override
                    public List<ConfigDic> load(String code) throws Exception {
                        ConfigDicService configDicService = SpringUtil.getBean(ConfigDicService.class);
                        return configDicService.getDicsByCode(code);
                    }
                });
    }

    private static LoadingCache<String,List<ConfigDic>> getListConfigItem(){
        if(listConfigItemCache == null){
            synchronized (ConfigDicCache.class){
                if(listConfigItemCache == null){
                    buildListConfigItemCache();
                }
            }
        }
        return listConfigItemCache;
    }


    /**
     *  根据字典类型获取字典列表
     * @param code
     * @return
     */
    public static List<ConfigDic> getListConfigItem(String code){
        if(StringUtils.isBlank(code)){
            return new ArrayList<>();
        }

        LoadingCache<String,List<ConfigDic>> cache = getListConfigItem();
        try {
            return cache.get(code);
        } catch (ExecutionException e) {
            logger.error("获取字典列表失败,type;{}",code,e);
        }
        return null;
    }

    /**
     * 根据编码获取字典项
     * @param code
     * @return
     */
    public static ConfigDic getConfigItem(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }

        LoadingCache<String,List<ConfigDic>> cache = getListConfigItem();
        try {
            return cache.size() > 0 ? cache.get(code).get(0) : null;
        } catch (ExecutionException e) {
            logger.error("获取字典失败,code;{}",code,e);
        }
        return null;
    }

    public static void clearListDictCache(String code) {
        getListConfigItem().invalidate(code);
    }
}
