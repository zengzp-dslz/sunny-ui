package io.sunny.modules.sunny.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.SunnyorderDao;
import io.sunny.modules.sunny.entity.SunnyorderEntity;
import io.sunny.modules.sunny.entity.SunnysaleEntity;
import io.sunny.modules.sunny.service.SunnyorderService;



@Service("sunnyorderService")
public class SunnyorderServiceImpl implements SunnyorderService {
	@Autowired
	private SunnyorderDao sunnyorderDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public SunnyorderEntity queryObject(String ordNo){
		return sunnyorderDao.queryObject(ordNo);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<SunnyorderEntity> queryList(Map<String, Object> map){
		return sunnyorderDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return sunnyorderDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(SunnyorderEntity sunnyorder){
		sunnyorderDao.save(sunnyorder);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(SunnyorderEntity sunnyorder){
		sunnyorderDao.update(sunnyorder);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String ordNo){
		sunnyorderDao.delete(ordNo);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] ordNos){
		sunnyorderDao.deleteBatch(ordNos);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnysaleEntity> queryDaySale(Map<String, Object> map) {
		return sunnyorderDao.queryDaySale(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnysaleEntity> queryMonthSale(Map<String, Object> map) {
		return sunnyorderDao.queryMonthSale(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int querydaysaleTotal(Map<String, Object> map) {
		return sunnyorderDao.querydaysaleTotal(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int querymonthsaleTotal(Map<String, Object> map) {
		return sunnyorderDao.querymonthsaleTotal(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int queryPageCount(Map<String, Object> map) {
		int result = sunnyorderDao.queryPageCount(map);
		result =  (int) Math.ceil(result / 10.0);
		return result;
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnyorderEntity> queryPageList(Map<String, Object> map) {
		if(null == map.get("curPage") || Integer.parseInt(map.get("curPage").toString()) <= 0) {
			map.put("curPage", 1);
		}
		map.put("offset", (Integer.parseInt(map.get("curPage").toString()) - 1) * 10);
		return sunnyorderDao.queryPageList(map);
	}
	
}
