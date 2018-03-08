package io.sunny.modules.oss.service;

import java.util.List;
import java.util.Map;

import io.sunny.modules.oss.entity.SysOssEntity;

/**
 * 文件上传
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SysOssService {
	
	SysOssEntity queryObject(Long id);
	
	List<SysOssEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysOssEntity sysOss);
	
	void update(SysOssEntity sysOss);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
