package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.GoodssoldEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface GoodssoldService {
	
	GoodssoldEntity queryObject(String goodsId);
	
	List<GoodssoldEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodssoldEntity goodssold);
	
	void update(GoodssoldEntity goodssold);
	
	void delete(String goodsId);
	
	void deleteBatch(String[] goodsIds);
}
