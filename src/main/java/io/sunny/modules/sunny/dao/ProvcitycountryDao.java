package io.sunny.modules.sunny.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sunny.entity.ProvcitycountryEntity;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface ProvcitycountryDao extends BaseDao<ProvcitycountryEntity> {
	List<ProvcitycountryEntity> selectProv();
	
	List<ProvcitycountryEntity> selectCity(String provId);
	
	List<ProvcitycountryEntity> selectCountry(String cityId);
}
