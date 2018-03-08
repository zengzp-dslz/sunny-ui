package io.sunny.modules.oss.dao;


import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.oss.entity.SysOssEntity;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 文件上传
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
	
}
