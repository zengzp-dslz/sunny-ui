package io.sunny.modules.sys.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.sunny.common.utils.RedisKeys;
import io.sunny.common.utils.RedisUtils;
import io.sunny.modules.sys.entity.SysConfigEntity;

/**
 * 系统配置Redis
 *
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysConfigEntity config) {
        if(config == null){
            return ;
        }
        String key = RedisKeys.getSysConfigKey(config.getKey());
        redisUtils.set(key, config);
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    public SysConfigEntity get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfigEntity.class);
    }
}
