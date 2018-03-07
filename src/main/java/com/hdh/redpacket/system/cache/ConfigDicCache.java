package com.hdh.redpacket.system.cache;

import com.google.common.cache.LoadingCache;
import com.hdh.redpacket.system.model.ConfigDic;

import java.util.List;

public class ConfigDicCache {

    private static LoadingCache<String,List<ConfigDic>> listConfigItemCache;

    private static LoadingCache<String,ConfigDic> configItemCache;
}
