package io.sunny.modules.job.service;


import java.util.List;
import java.util.Map;

import io.sunny.modules.job.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLogEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLogEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogEntity log);
	
}
