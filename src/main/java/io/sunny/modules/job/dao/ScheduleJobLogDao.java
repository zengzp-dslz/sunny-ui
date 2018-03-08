package io.sunny.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import io.sunny.modules.job.entity.ScheduleJobLogEntity;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 定时任务日志
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
