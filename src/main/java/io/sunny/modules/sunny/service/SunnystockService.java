package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.SunnystockEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
public interface SunnystockService {
	
	SunnystockEntity queryObject(Integer stockId);
	
	List<SunnystockEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnystockEntity sunnystock) throws Exception;
	
	void update(SunnystockEntity sunnystock);
	
	void delete(Integer stockId);
	
	void deleteBatch(Integer[] stockIds);
	
	void savestock(Map<String, Object> map) throws Exception;
	
	List<SunnystockEntity> queryPageList(Map<String, Object> map);
	
	int queryPageCount(Map<String, Object> map);
	
	List<SunnystockEntity> queryStock(Map<String, Object> map);
}
