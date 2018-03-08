package io.sunny.modules.sunny.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import io.sunny.common.datasource.TargetDataSource;
import io.sunny.common.utils.DateTimeUtil;
import io.sunny.common.utils.MethodAES;
import io.sunny.common.utils.SequenceUtil;
import io.sunny.modules.sunny.dao.SunnygoodsDao;
import io.sunny.modules.sunny.dao.SunnymercDao;
import io.sunny.modules.sunny.dao.SunnystockDao;
import io.sunny.modules.sunny.dao.SunnystocklogDao;
import io.sunny.modules.sunny.entity.SunnygoodsEntity;
import io.sunny.modules.sunny.entity.SunnymercEntity;
import io.sunny.modules.sunny.entity.SunnystockEntity;
import io.sunny.modules.sunny.entity.SunnystocklogEntity;
import io.sunny.modules.sunny.service.SunnystockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Service("sunnystockService")
@EnableTransactionManagement
public class SunnystockServiceImpl implements SunnystockService {
	@Autowired
	private SunnystockDao sunnystockDao;
	
	@Autowired
	private SunnystocklogDao sunnystocklogDao;
	
	@Autowired
	private SunnygoodsDao sunnygoodsDao;
	
	@Autowired
	private SunnymercDao sunnymercDao;
	
	@Override
		@TargetDataSource(name = "sunny")
		public SunnystockEntity queryObject(Integer stockId){
		return sunnystockDao.queryObject(stockId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public List<SunnystockEntity> queryList(Map<String, Object> map){
		return sunnystockDao.queryList(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public int queryTotal(Map<String, Object> map){
		return sunnystockDao.queryTotal(map);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		@Transactional(rollbackFor=Exception.class)
		public void save(SunnystockEntity sunnystock) throws Exception{
		SunnymercEntity sunnymercEntity = sunnymercDao.queryObject(sunnystock.getMercId());
		if(null == sunnymercEntity) {
			throw new Exception(sunnystock.getMercId() + "商户不存在");
		}
		//获取商品信息
		SunnygoodsEntity sunnygoods = sunnygoodsDao.getGoodsByBarcode(sunnystock.getBarcode());
		if(null == sunnygoods) {
			throw new Exception(sunnystock.getBarcode() + "条形码不存在");
		}
		//获取库存信息
		Map<String, Object> map = new HashMap<>();
		map.put("mercId", sunnystock.getMercId());
		map.put("goodsId", sunnygoods.getGoodsId());
		SunnystockEntity entity = sunnystockDao.getStock(map);
		//记录操作日志
		SunnystocklogEntity sunnystocklog = new SunnystocklogEntity();
		sunnystocklog.setStocklogId(SequenceUtil.logNo());
		sunnystocklog.setParams(sunnystock.toString());
		sunnystocklog.setTmSmp(DateTimeUtil.currentDateTime());
		if(null != entity) {
			//已存在 修改库存
			entity.setGoodsCid(sunnystock.getGoodsCid());
			entity.setGoodsNum(entity.getGoodsNum() + sunnystock.getGoodsIns());
			entity.setGoodsIns(entity.getGoodsIns() + sunnystock.getGoodsIns());
			entity.setUpdateDt(DateTimeUtil.currentDate());
			entity.setUpdateTm(DateTimeUtil.currentTime());
			sunnystockDao.update(entity);
			sunnystocklog.setMethod("savestock.stockUpdate");
		} else {
			//不存在 添加库存
			sunnystock.setMercId(sunnystock.getMercId());
			sunnystock.setBarcode(sunnygoods.getBarcode());
			sunnystock.setGoodsId(sunnygoods.getGoodsId());
			sunnystock.setGoodsBid(sunnygoods.getGoodsBid());
			sunnystock.setGoodsNum(sunnystock.getGoodsIns());
			sunnystock.setUpdateDt(DateTimeUtil.currentDate());
			sunnystock.setUpdateTm(DateTimeUtil.currentTime());
			sunnystock.setTmSmp(DateTimeUtil.currentDateTime());
			sunnystockDao.save(sunnystock);
			sunnystocklog.setMethod("savestock.stockAdd");
		}
		sunnystocklogDao.save(sunnystocklog);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void update(SunnystockEntity sunnystock){
		sunnystockDao.update(sunnystock);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void delete(Integer stockId){
		sunnystockDao.delete(stockId);
	}
	
	@Override
		@TargetDataSource(name = "sunny")
		public void deleteBatch(Integer[] stockIds){
		sunnystockDao.deleteBatch(stockIds);
	}

	@Override
	@TargetDataSource(name = "sunny")
	@Transactional(rollbackFor=Exception.class)
	public void savestock(Map<String, Object> map) throws Exception{
		@SuppressWarnings("unchecked")
		Map<String, Object> data = JSONObject.fromObject(MethodAES.outsidDecrypt(map.get("data").toString()));
		
		JSONArray jsonArray = JSONArray.fromObject(data.get("product"));
		SunnymercEntity sunnymercEntity = sunnymercDao.queryObject(data.get("mercId"));
		if(null == sunnymercEntity) {
			throw new Exception(data.get("mercId") + "商户不存在");
		}
		for (int i = 0; i < jsonArray.size(); i++) {
			@SuppressWarnings("unchecked")
			Map<String, Object> jsonmap = (Map<String, Object>) jsonArray.get(i);
			//获取商品信息
			SunnygoodsEntity sunnygoods = sunnygoodsDao.getGoodsByBarcode(jsonmap.get("barcode").toString());
			if(null == sunnygoods) {
				throw new Exception(jsonmap.get("barcode").toString() + "条形码不存在");
			}
			//获取库存信息
			jsonmap.put("mercId", data.get("mercId"));
			jsonmap.put("goodsId", sunnygoods.getGoodsId());
			SunnystockEntity entity = sunnystockDao.getStock(jsonmap);
			//记录操作日志
			SunnystocklogEntity sunnystocklog = new SunnystocklogEntity();
			sunnystocklog.setStocklogId(SequenceUtil.logNo());
			sunnystocklog.setParams(jsonmap.toString());
			sunnystocklog.setTmSmp(DateTimeUtil.currentDateTime());
			if(null != entity) {
				//已存在 修改库存
				entity.setGoodsCid(Long.parseLong(jsonmap.get("cid").toString()));
				entity.setGoodsNum(entity.getGoodsNum() + Integer.parseInt(jsonmap.get("goodsNum").toString()));
				entity.setGoodsIns(entity.getGoodsIns() + Integer.parseInt(jsonmap.get("goodsNum").toString()));
				entity.setUpdateDt(DateTimeUtil.currentDate());
				entity.setUpdateTm(DateTimeUtil.currentTime());
				sunnystockDao.update(entity);
				sunnystocklog.setMethod("savestock.stockUpdate");
			} else {
				//不存在 添加库存
				SunnystockEntity sunnystock = new SunnystockEntity();
				sunnystock.setMercId(data.get("mercId").toString());
				sunnystock.setBarcode(sunnygoods.getBarcode());
				sunnystock.setGoodsId(sunnygoods.getGoodsId());
				sunnystock.setGoodsCid(Long.parseLong(jsonmap.get("cid").toString()));
				sunnystock.setGoodsBid(sunnygoods.getGoodsBid());
				sunnystock.setGoodsNum(Integer.parseInt(jsonmap.get("goodsNum").toString()));
				sunnystock.setGoodsIns(Integer.parseInt(jsonmap.get("goodsNum").toString()));
				sunnystock.setUpdateDt(DateTimeUtil.currentDate());
				sunnystock.setUpdateTm(DateTimeUtil.currentTime());
				sunnystock.setTmSmp(DateTimeUtil.currentDateTime());
				sunnystockDao.save(sunnystock);
				sunnystocklog.setMethod("savestock.stockAdd");
			}
			sunnystocklogDao.save(sunnystocklog);
		}
		
	}

	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnystockEntity> queryPageList(Map<String, Object> map) {
		if(null == map.get("curPage") || Integer.parseInt(map.get("curPage").toString()) <= 0) {
			map.put("curPage", 1);
		}
		map.put("offset", (Integer.parseInt(map.get("curPage").toString()) - 1) * 10);
		return sunnystockDao.queryPageList(map);
	}

	@Override
	@TargetDataSource(name = "sunny")
	public int queryPageCount(Map<String, Object> map) {
		int result = sunnystockDao.queryPageCount(map);
		result =  (int) Math.ceil(result / 10.0);
		return result;
	}
	
	@Override
	@TargetDataSource(name = "sunny")
	public List<SunnystockEntity> queryStock(Map<String, Object> map) {
		return sunnystockDao.queryStock(map);
	}
	
}
