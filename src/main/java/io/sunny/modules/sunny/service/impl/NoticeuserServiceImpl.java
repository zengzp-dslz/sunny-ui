package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.NoticeuserDao;
import io.sunny.modules.sunny.entity.NoticeuserEntity;
import io.sunny.modules.sunny.service.NoticeuserService;



@Service("noticeuserService")
public class NoticeuserServiceImpl implements NoticeuserService {
	@Autowired
	private NoticeuserDao noticeuserDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public NoticeuserEntity queryObject(String nationCode){
		return noticeuserDao.queryObject(nationCode);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<NoticeuserEntity> queryList(Map<String, Object> map){
		return noticeuserDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return noticeuserDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(NoticeuserEntity noticeuser){
		noticeuserDao.save(noticeuser);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(NoticeuserEntity noticeuser){
		noticeuserDao.update(noticeuser);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String nationCode){
		noticeuserDao.delete(nationCode);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] nationCodes){
		noticeuserDao.deleteBatch(nationCodes);
	}
	
}
