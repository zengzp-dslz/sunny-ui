package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.SunnystocklogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
public interface SunnystocklogService {
	
	SunnystocklogEntity queryObject(Integer stocklogId);
	
	List<SunnystocklogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnystocklogEntity sunnystocklog);
	
	void update(SunnystocklogEntity sunnystocklog);
	
	void delete(Integer stocklogId);
	
	void deleteBatch(Integer[] stocklogIds);
}
