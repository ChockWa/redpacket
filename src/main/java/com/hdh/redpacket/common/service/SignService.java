package com.hdh.redpacket.common.service;

import com.alibaba.fastjson.JSONObject;
import com.hdh.redpacket.common.exception.CommonException;
import com.hdh.redpacket.common.model.KeySortedMap;
import com.hdh.redpacket.core.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SignService {

    private Logger logger = LoggerFactory.getLogger(SignService.class);

    private List<String> ignoreParams = Arrays.asList("sign");

    /**
     * 校验签名
     * @param secretKey
     * @param dataMap
     */
    public void checkSign(String secretKey,Map<String,Object> dataMap){
        if(StringUtils.isBlank(secretKey) || dataMap == null || dataMap.size() < 1){
            throw CommonException.SIGN_ERROR;
        }
        String oldSign = dataMap.get("sign") == null ? "" : dataMap.get("sign").toString();
        String newSign = buildNewSign(secretKey,dataMap);
        logger.info("旧签名是:{}",oldSign);
        logger.info("新签名是:{}",newSign);
        if(!oldSign.equals(newSign)){
            throw CommonException.SIGN_ERROR;
        }
    }

    /**
     * 生成签名
     * @param secretKey
     * @param dataMap
     * @return
     */
    private String buildNewSign(String secretKey, Map<String,Object> dataMap){
        for(String param : ignoreParams){
            dataMap.remove(param);
        }
        Map<String,Object> sortedMap = new KeySortedMap(dataMap);
        String paramsString = JSONObject.toJSONString(sortedMap) + secretKey;
        logger.info("构成的原始字符串是:{}",paramsString);
        String newSign = Md5Util.md5(paramsString);
        return newSign;
    }

}
