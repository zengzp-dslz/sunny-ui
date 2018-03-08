package io.sunny.modules.sys.service;


import java.util.List;
import java.util.Map;

import io.sunny.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SysLogService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogEntity sysLog);
	
	void update(SysLogEntity sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
