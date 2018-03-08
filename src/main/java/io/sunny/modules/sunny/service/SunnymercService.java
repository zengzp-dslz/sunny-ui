package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.SunnymercEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SunnymercService {
	
	SunnymercEntity queryObject(String mercId);
	
	List<SunnymercEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnymercEntity sunnymerc);
	
	void update(SunnymercEntity sunnymerc);
	
	void delete(String mercId);
	
	void deleteBatch(String[] mercIds);
	
	int queryPageCount();
	
	List<SunnymercEntity> queryPageList(Map<String, Object> map);
}
