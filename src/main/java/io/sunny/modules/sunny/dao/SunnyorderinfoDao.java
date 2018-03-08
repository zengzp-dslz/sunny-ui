package io.sunny.modules.sunny.dao;

import io.sunny.modules.sunny.entity.SunnyorderinfoEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SunnyorderinfoDao extends BaseDao<SunnyorderinfoEntity> {
int queryPageCount(Map<String, Object> map);
	
	List<SunnyorderinfoEntity> queryPageList(Map<String, Object> map);
}
