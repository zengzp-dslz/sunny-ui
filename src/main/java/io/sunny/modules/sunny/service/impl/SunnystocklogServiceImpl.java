package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.SunnystocklogDao;
import io.sunny.modules.sunny.entity.SunnystocklogEntity;
import io.sunny.modules.sunny.service.SunnystocklogService;



@Service("sunnystocklogService")
public class SunnystocklogServiceImpl implements SunnystocklogService {
	@Autowired
	private SunnystocklogDao sunnystocklogDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public SunnystocklogEntity queryObject(Integer stocklogId){
		return sunnystocklogDao.queryObject(stocklogId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<SunnystocklogEntity> queryList(Map<String, Object> map){
		return sunnystocklogDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return sunnystocklogDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(SunnystocklogEntity sunnystocklog){
		sunnystocklogDao.save(sunnystocklog);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(SunnystocklogEntity sunnystocklog){
		sunnystocklogDao.update(sunnystocklog);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(Integer stocklogId){
		sunnystocklogDao.delete(stocklogId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(Integer[] stocklogIds){
		sunnystocklogDao.deleteBatch(stocklogIds);
	}
	
}
