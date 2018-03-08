package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.SunnygoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface SunnygoodsService {
	
	SunnygoodsEntity queryObject(String goodsId);
	
	List<SunnygoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SunnygoodsEntity sunnygoods);
	
	void update(SunnygoodsEntity sunnygoods);
	
	void delete(String goodsId);
	
	void deleteBatch(String[] goodsIds);
	
	SunnygoodsEntity queryBarcode(String barcode);
}
