package com.hdh.redpacket.core.utils;

import java.util.UUID;

/**
 * Uuid工具类
 */
public abstract class UuidUtil {

	public static String genUuid() {
		return getUuid().toString();
	}

	/**
	 * 产生字符串的uuid，并且没有连接线
	 * 
	 * @return
	 */
	public static String genUuidNoLine() {
		UUID uuid = getUuid();
		Long most = uuid.getMostSignificantBits();
		Long least = uuid.getLeastSignificantBits();
		return ByteUtils.bytesToHexString(ByteUtils.longToBytes(most, least));
	}
	/**
	 * 产生短的UUID
	 * @return
	 */
	public static String shortUuid() {
		UUID uuid = getUuid();
		Long most = uuid.getMostSignificantBits();
		Long least = uuid.getLeastSignificantBits();
		return ByteUtils.idToCode(most) + ByteUtils.idToCode(least);
	}

	//这个方法还需要改进随机数的生成
	private static UUID getUuid() {
		return UUID.randomUUID();
	}

	/**
	 * 获取唯一的场次编号
	 * @return
	 */
	public static String getPlayNoByUUId() {
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {//有可能是负数
			hashCodeV = - hashCodeV;
		}
       // 0 代表前面补充0
       // 4 代表长度为4
       // d 代表参数为正数型
		return  machineId+ String.format("%015d", hashCodeV);
	}

}
