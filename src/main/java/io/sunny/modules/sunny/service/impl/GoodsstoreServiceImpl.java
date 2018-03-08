package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.GoodsstoreDao;
import io.sunny.modules.sunny.entity.GoodsstoreEntity;
import io.sunny.modules.sunny.service.GoodsstoreService;



@Service("goodsstoreService")
public class GoodsstoreServiceImpl implements GoodsstoreService {
	@Autowired
	private GoodsstoreDao goodsstoreDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public GoodsstoreEntity queryObject(String goodsId){
		return goodsstoreDao.queryObject(goodsId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<GoodsstoreEntity> queryList(Map<String, Object> map){
		return goodsstoreDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return goodsstoreDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(GoodsstoreEntity goodsstore){
		goodsstoreDao.save(goodsstore);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(GoodsstoreEntity goodsstore){
		goodsstoreDao.update(goodsstore);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String goodsId){
		goodsstoreDao.delete(goodsId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] goodsIds){
		goodsstoreDao.deleteBatch(goodsIds);
	}
	
}
