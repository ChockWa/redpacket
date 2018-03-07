package com.hdh.redpacket.system.cache;

import com.google.common.cache.LoadingCache;
import com.hdh.redpacket.system.model.ConfigDic;

import java.util.List;

public class ConfigDicCache {

    private LoadingCache<String,List<ConfigDic>> configDicListItem;
}
