package io.sunny.modules.sunny.service;

import java.util.List;
import java.util.Map;

import io.sunny.modules.sunny.entity.SunnyorderinfoEntity;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SunnyorderinfoService {
	
	SunnyorderinfoEntity queryObject(String ordNo);
	
	List<SunnyorderinfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnyorderinfoEntity sunnyorderinfo);
	
	void update(SunnyorderinfoEntity sunnyorderinfo);
	
	void delete(String ordNo);
	
	void deleteBatch(String[] ordNos);
	
	int queryPageCount(Map<String, Object> map);
	
	List<SunnyorderinfoEntity> queryPageList(Map<String, Object> map);
}
