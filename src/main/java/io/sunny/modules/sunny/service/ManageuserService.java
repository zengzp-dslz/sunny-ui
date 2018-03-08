package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.ManageuserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface ManageuserService {
	
	ManageuserEntity queryObject(String managerId);
	
	List<ManageuserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ManageuserEntity manageuser);
	
	void update(ManageuserEntity manageuser);
	
	void delete(String managerId);
	
	void deleteBatch(String[] managerIds);
}
