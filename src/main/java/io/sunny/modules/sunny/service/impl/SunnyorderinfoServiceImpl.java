package io.sunny.modules.sunny.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.SunnyorderinfoDao;
import io.sunny.modules.sunny.entity.SunnyorderinfoEntity;
import io.sunny.modules.sunny.service.SunnyorderinfoService;



@Service("sunnyorderinfoService")
public class SunnyorderinfoServiceImpl implements SunnyorderinfoService {
	@Autowired
	private SunnyorderinfoDao sunnyorderinfoDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public SunnyorderinfoEntity queryObject(String ordNo){
		return sunnyorderinfoDao.queryObject(ordNo);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<SunnyorderinfoEntity> queryList(Map<String, Object> map){
		return sunnyorderinfoDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return sunnyorderinfoDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(SunnyorderinfoEntity sunnyorderinfo){
		sunnyorderinfoDao.save(sunnyorderinfo);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(SunnyorderinfoEntity sunnyorderinfo){
		sunnyorderinfoDao.update(sunnyorderinfo);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String ordNo){
		sunnyorderinfoDao.delete(ordNo);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] ordNos){
		sunnyorderinfoDao.deleteBatch(ordNos);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int queryPageCount(Map<String, Object> map) {
		int result = sunnyorderinfoDao.queryPageCount(map);
		result =  (int) Math.ceil(result / 10.0);
		return result;
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnyorderinfoEntity> queryPageList(Map<String, Object> map) {
		if(null == map.get("curPage") || Integer.parseInt(map.get("curPage").toString()) <= 0) {
			map.put("curPage", 1);
		}
		map.put("offset", (Integer.parseInt(map.get("curPage").toString()) - 1) * 10);
		return sunnyorderinfoDao.queryPageList(map);
	}
	
}
