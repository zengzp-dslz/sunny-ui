package io.sunny.modules.sys.dao;


import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
