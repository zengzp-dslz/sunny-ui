package io.sunny.modules.sunny.service;

import io.sunny.modules.sunny.entity.ProvcitycountryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface ProvcitycountryService {
	
	ProvcitycountryEntity queryObject(String provCd);
	
	List<ProvcitycountryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProvcitycountryEntity provcitycountry);
	
	void update(ProvcitycountryEntity provcitycountry);
	
	void delete(String provCd);
	
	void deleteBatch(String[] provCds);
	
	List<ProvcitycountryEntity> selectProv();
	
	List<ProvcitycountryEntity> selectCity(String provId);
	
	List<ProvcitycountryEntity> selectCountry(String cityId);
}
