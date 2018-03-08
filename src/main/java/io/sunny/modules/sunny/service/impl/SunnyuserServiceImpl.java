package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.SunnyuserDao;
import io.sunny.modules.sunny.entity.SunnyuserEntity;
import io.sunny.modules.sunny.service.SunnyuserService;



@Service("sunnyuserService")
public class SunnyuserServiceImpl implements SunnyuserService {
	@Autowired
	private SunnyuserDao sunnyuserDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public SunnyuserEntity queryObject(String userType){
		return sunnyuserDao.queryObject(userType);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<SunnyuserEntity> queryList(Map<String, Object> map){
		return sunnyuserDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return sunnyuserDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(SunnyuserEntity sunnyuser){
		sunnyuserDao.save(sunnyuser);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(SunnyuserEntity sunnyuser){
		sunnyuserDao.update(sunnyuser);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String userType){
		sunnyuserDao.delete(userType);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] userTypes){
		sunnyuserDao.deleteBatch(userTypes);
	}
	
}
