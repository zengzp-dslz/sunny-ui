package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.ManageuserDao;
import io.sunny.modules.sunny.entity.ManageuserEntity;
import io.sunny.modules.sunny.service.ManageuserService;



@Service("manageuserService")
public class ManageuserServiceImpl implements ManageuserService {
	@Autowired
	private ManageuserDao manageuserDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public ManageuserEntity queryObject(String managerId){
		return manageuserDao.queryObject(managerId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<ManageuserEntity> queryList(Map<String, Object> map){
		return manageuserDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return manageuserDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(ManageuserEntity manageuser){
		manageuserDao.save(manageuser);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(ManageuserEntity manageuser){
		manageuserDao.update(manageuser);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String managerId){
		manageuserDao.delete(managerId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] managerIds){
		manageuserDao.deleteBatch(managerIds);
	}
	
}
