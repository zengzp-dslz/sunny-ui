package io.sunny.modules.sunny.service;

import java.util.List;
import java.util.Map;

import io.sunny.modules.sunny.entity.SunnyorderEntity;
import io.sunny.modules.sunny.entity.SunnysaleEntity;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SunnyorderService {
	
	SunnyorderEntity queryObject(String ordNo);
	
	List<SunnyorderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnyorderEntity sunnyorder);
	
	void update(SunnyorderEntity sunnyorder);
	
	void delete(String ordNo);
	
	void deleteBatch(String[] ordNos);
	
	List<SunnysaleEntity> queryDaySale(Map<String, Object> map);
	
	int querydaysaleTotal(Map<String, Object> map);
	
	List<SunnysaleEntity> queryMonthSale(Map<String, Object> map);
	
	int querymonthsaleTotal(Map<String, Object> map);
	
	int queryPageCount(Map<String, Object> map);
	
	List<SunnyorderEntity> queryPageList(Map<String, Object> map);
}
