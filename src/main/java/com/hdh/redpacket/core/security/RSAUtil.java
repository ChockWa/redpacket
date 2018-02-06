package com.hdh.redpacket.core.security;

import com.hdh.redpacket.core.utils.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密工具类
 */
public class RSAUtil {

    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";

    /** 公钥 */
    public static String PUBLIC_KEY = "PublicKey";

    /** 私钥 */
    private static String PRIVATE_KEY = "PrivateKey";

    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 1024;

    /**
     * 加密方法
     * @param publicKey 公钥
     * @param source    数据
     * @return
     * @throws Exception
     */
    public static String encrypt(Key publicKey, String source) throws Exception {

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * 解密算法
     * @param privateKey  私钥
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decrypt(Key privateKey, String cryptograph) throws Exception {

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);

        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 生成密钥对
     * @return
     * @throws Exception
     */
    private static Map<String,Object> genKey() throws Exception {
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
//             keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        Map<String,Object> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY,publicKey);
        keyMap.put(PRIVATE_KEY,privateKey);

        return keyMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> map = genKey();
        System.out.println(Base64Utils.encode(((Key)map.get(PUBLIC_KEY)).getEncoded()));
    }

}
