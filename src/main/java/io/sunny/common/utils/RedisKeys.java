package io.sunny.common.utils;

/**
 * Redis所有Keys
 *
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}
