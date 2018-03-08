package io.sunny.modules.sunny.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.sunny.common.datasource.TargetDataSource;
import io.sunny.common.utils.DateTimeUtil;
import io.sunny.modules.sunny.dao.SunnygoodsDao;
import io.sunny.modules.sunny.entity.SunnygoodsEntity;
import io.sunny.modules.sunny.service.SunnygoodsService;
import io.sunny.modules.sys.entity.SysUserEntity;

@Service("sunnygoodsService")
public class SunnygoodsServiceImpl implements SunnygoodsService {
	@Autowired
	private SunnygoodsDao sunnygoodsDao;

	@Override
	@TargetDataSource(name = "sunny")
	public SunnygoodsEntity queryObject(String goodsId) {
		return sunnygoodsDao.queryObject(goodsId);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnygoodsEntity> queryList(Map<String, Object> map) {
		return sunnygoodsDao.queryList(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int queryTotal(Map<String, Object> map) {
		return sunnygoodsDao.queryTotal(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public void save(SunnygoodsEntity sunnygoods) {
		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		if (StringUtils.isBlank(sunnygoods.getGoodsId())) {
			sunnygoods.setGoodsId(sunnygoodsDao.newGoodsId());
			sunnygoods.setCreateUser(username);
			sunnygoods.setCreateDt(DateTimeUtil.currentDate());
			sunnygoods.setCreateTm(DateTimeUtil.currentTime());
		}
		sunnygoods.setUpdateUser(username);
		sunnygoods.setUpdateDt(DateTimeUtil.currentDate());
		sunnygoods.setUpdateTm(DateTimeUtil.currentTime());
		sunnygoods.setTmSmp(DateTimeUtil.currentDateTime());
		sunnygoodsDao.save(sunnygoods);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public void update(SunnygoodsEntity sunnygoods) {
		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		sunnygoods.setUpdateUser(username);
		sunnygoods.setUpdateDt(DateTimeUtil.currentDate());
		sunnygoods.setUpdateTm(DateTimeUtil.currentTime());
		sunnygoods.setTmSmp(DateTimeUtil.currentDateTime());
		sunnygoodsDao.update(sunnygoods);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public void delete(String goodsId) {
		sunnygoodsDao.delete(goodsId);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public void deleteBatch(String[] goodsIds) {
		sunnygoodsDao.deleteBatch(goodsIds);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public SunnygoodsEntity queryBarcode(String barcode) {
		return sunnygoodsDao.getGoodsByBarcode(barcode);
	}

}
