package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.SunnymercDao;
import io.sunny.modules.sunny.entity.SunnymercEntity;
import io.sunny.modules.sunny.service.SunnymercService;



@Service("sunnymercService")
public class SunnymercServiceImpl implements SunnymercService {
	@Autowired
	private SunnymercDao sunnymercDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public SunnymercEntity queryObject(String mercId){
		return sunnymercDao.queryObject(mercId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<SunnymercEntity> queryList(Map<String, Object> map){
		return sunnymercDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return sunnymercDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(SunnymercEntity sunnymerc){
		sunnymercDao.save(sunnymerc);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(SunnymercEntity sunnymerc){
		sunnymercDao.update(sunnymerc);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String mercId){
		sunnymercDao.delete(mercId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] mercIds){
		sunnymercDao.deleteBatch(mercIds);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int queryPageCount() {
		int result = sunnymercDao.queryTotal();
		result =  (int) Math.ceil(result / 10.0);
		return result;
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnymercEntity> queryPageList(Map<String, Object> map) {
		if(null == map.get("curPage") || Integer.parseInt(map.get("curPage").toString()) <= 0) {
			map.put("curPage", 1);
		}
		map.put("offset", (Integer.parseInt(map.get("curPage").toString()) - 1) * 10);
		return sunnymercDao.queryPageList(map);
	}
	
}
