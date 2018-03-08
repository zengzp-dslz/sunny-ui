package io.sunny.modules.sys.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.sunny.modules.sys.entity.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
