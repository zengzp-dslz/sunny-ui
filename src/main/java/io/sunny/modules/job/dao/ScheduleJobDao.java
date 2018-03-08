package io.sunny.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.job.entity.ScheduleJobEntity;
import io.sunny.modules.sys.dao.BaseDao;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
