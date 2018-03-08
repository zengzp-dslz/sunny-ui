package io.sunny.modules.sunny.dao;

import io.sunny.modules.sunny.entity.SunnystockEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
@Mapper
public interface SunnystockDao extends BaseDao<SunnystockEntity> {
	SunnystockEntity getStock(Map<String, Object> map);
	
	int queryPageCount(Map<String, Object> map);
	
	List<SunnystockEntity> queryPageList(Map<String, Object> map);
	
	List<SunnystockEntity> queryStock(Map<String, Object> map);
}
