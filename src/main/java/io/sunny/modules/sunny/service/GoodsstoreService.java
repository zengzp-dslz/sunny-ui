package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.GoodsstoreEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface GoodsstoreService {
	
	GoodsstoreEntity queryObject(String goodsId);
	
	List<GoodsstoreEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsstoreEntity goodsstore);
	
	void update(GoodsstoreEntity goodsstore);
	
	void delete(String goodsId);
	
	void deleteBatch(String[] goodsIds);
}
