package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.GoodssoldDao;
import io.sunny.modules.sunny.entity.GoodssoldEntity;
import io.sunny.modules.sunny.service.GoodssoldService;



@Service("goodssoldService")
public class GoodssoldServiceImpl implements GoodssoldService {
	@Autowired
	private GoodssoldDao goodssoldDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public GoodssoldEntity queryObject(String goodsId){
		return goodssoldDao.queryObject(goodsId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<GoodssoldEntity> queryList(Map<String, Object> map){
		return goodssoldDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return goodssoldDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(GoodssoldEntity goodssold){
		goodssoldDao.save(goodssold);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(GoodssoldEntity goodssold){
		goodssoldDao.update(goodssold);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String goodsId){
		goodssoldDao.delete(goodsId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] goodsIds){
		goodssoldDao.deleteBatch(goodsIds);
	}
	
}
