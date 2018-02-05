package com.hdh.redpacket.system.service;

import com.hdh.redpacket.core.cache.RedisService;
import com.hdh.redpacket.system.exception.SafeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 通用验证类
 * @author hezhuohua
 *
 */
@Service
public class VerificationService {
	
	private static final String VERIFY_CODES = "23456789abcdefghjkmnpqrstuvwsyzABCDEFGHJKLMNPQRSTUVWSYZ";
	
	private static int EXPIRE_TIME = 1*60*5;  // 5分钟

	@Autowired
	private RedisService redisService;
	
	/**
	 * 随机生成验证码字符串
	 * @param codeLen
	 * @return
	 */
	public String verifyCodeGeneration(int codeLen, String bindKey){
		StringBuilder sb = new StringBuilder(codeLen);
		int totalChar = VERIFY_CODES.length();
		Random rand = new Random(System.currentTimeMillis());
		for(int i = 0; i < codeLen; i++){  
            sb.append(VERIFY_CODES.charAt(rand.nextInt(totalChar-1)));  
        }
		// 把验证码与bindKey暂时绑定在缓存
		redisService.set(bindKey,sb.toString(),EXPIRE_TIME);
		return sb.toString();
	}

	/**
	 * 校验验证码
	 * @param bindKey
	 * @param verifyCode
	 */
	public void checkVerfyCode(String bindKey, String verifyCode){
		if(StringUtils.isBlank(verifyCode) || StringUtils.isBlank(bindKey)){
			throw SafeException.PARAMS_ERROR;
		}

		String cacheCode = redisService.get(bindKey) == null ? "" : redisService.get(bindKey).toString();
	    if(!cacheCode.toLowerCase().equals(verifyCode.toLowerCase())){
	    	throw SafeException.VERIFYCAODE_ERROR;
	    }
	}
}
