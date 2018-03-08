package io.sunny.modules.sunny.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.sunny.entity.SunnyorderEntity;
import io.sunny.modules.sunny.entity.SunnysaleEntity;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SunnyorderDao extends BaseDao<SunnyorderEntity> {
	
	List<SunnysaleEntity> queryDaySale(Map<String, Object> map);
	
	List<SunnysaleEntity> queryMonthSale(Map<String, Object> map);	
	
	int querydaysaleTotal(Map<String, Object> map);
	
	int querymonthsaleTotal(Map<String, Object> map);
	
	int queryPageCount(Map<String, Object> map);
	
	List<SunnyorderEntity> queryPageList(Map<String, Object> map);
}
