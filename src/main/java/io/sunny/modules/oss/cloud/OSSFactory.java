package io.sunny.modules.oss.cloud;


import io.sunny.common.utils.ConfigConstant;
import io.sunny.common.utils.Constant;
import io.sunny.common.utils.SpringContextUtils;
import io.sunny.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
