package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.SunnyuserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SunnyuserService {
	
	SunnyuserEntity queryObject(String userType);
	
	List<SunnyuserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnyuserEntity sunnyuser);
	
	void update(SunnyuserEntity sunnyuser);
	
	void delete(String userType);
	
	void deleteBatch(String[] userTypes);
}
