package io.sunny.modules.sunny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.modules.sunny.dao.ProvcitycountryDao;
import io.sunny.modules.sunny.entity.ProvcitycountryEntity;
import io.sunny.modules.sunny.service.ProvcitycountryService;



@Service("provcitycountryService")
public class ProvcitycountryServiceImpl implements ProvcitycountryService {
	@Autowired
	private ProvcitycountryDao provcitycountryDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public ProvcitycountryEntity queryObject(String provCd){
		return provcitycountryDao.queryObject(provCd);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<ProvcitycountryEntity> queryList(Map<String, Object> map){
		return provcitycountryDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return provcitycountryDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void save(ProvcitycountryEntity provcitycountry){
		provcitycountryDao.save(provcitycountry);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(ProvcitycountryEntity provcitycountry){
		provcitycountryDao.update(provcitycountry);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(String provCd){
		provcitycountryDao.delete(provCd);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(String[] provCds){
		provcitycountryDao.deleteBatch(provCds);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<ProvcitycountryEntity> selectProv() {
		return provcitycountryDao.selectProv();
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<ProvcitycountryEntity> selectCity(String provId) {
		return provcitycountryDao.selectCity(provId);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<ProvcitycountryEntity> selectCountry(String cityId) {
		return provcitycountryDao.selectCountry(cityId);
	}
	
}
