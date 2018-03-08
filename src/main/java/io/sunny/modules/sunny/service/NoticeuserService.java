package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.NoticeuserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface NoticeuserService {
	
	NoticeuserEntity queryObject(String nationCode);
	
	List<NoticeuserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(NoticeuserEntity noticeuser);
	
	void update(NoticeuserEntity noticeuser);
	
	void delete(String nationCode);
	
	void deleteBatch(String[] nationCodes);
}
