package io.sunny.modules.sunny.dao;

import io.sunny.modules.sunny.entity.SunnygoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import io.sunny.modules.sys.dao.BaseDao;

/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@Mapper
public interface SunnygoodsDao extends BaseDao<SunnygoodsEntity> {
	String newGoodsId();
	
	SunnygoodsEntity getGoodsByBarcode(String barcode);
}
