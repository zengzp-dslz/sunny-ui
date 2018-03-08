package io.sunny.modules.sunny.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public class SunnygoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//线圈ID
	private String goodsId;
	//
	private String barcode;
	//商品名称
	private String goodsName;
	//商品简称
	private String goodsSname;
	//商品类型
	private String goodsType;
	//描述
	private String goodsRemark;
	//库存
	private Integer goodsNum;
	//过期天数
	private Integer goodsExp;
	//单价
	private Long goodsBid;
	//规格
	private String goodsSize;
	//创建日期
	private String createDt;
	//创建时间
	private String createTm;
	//创建用户
	private String createUser;
	//修改日期
	private String updateDt;
	//修改时间
	private String updateTm;
	//修改用户
	private String updateUser;
	//时间戳
	private String tmSmp;

	/**
	 * 设置：线圈ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：线圈ID
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	/**
	 * 获取：
	 */
	public String getBarcode() {
		return barcode;
	}
	/**
	 * 设置：商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：商品简称
	 */
	public void setGoodsSname(String goodsSname) {
		this.goodsSname = goodsSname;
	}
	/**
	 * 获取：商品简称
	 */
	public String getGoodsSname() {
		return goodsSname;
	}
	/**
	 * 设置：商品类型
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	/**
	 * 获取：商品类型
	 */
	public String getGoodsType() {
		return goodsType;
	}
	/**
	 * 设置：描述
	 */
	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}
	/**
	 * 获取：描述
	 */
	public String getGoodsRemark() {
		return goodsRemark;
	}
	/**
	 * 设置：库存
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	/**
	 * 获取：库存
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	/**
	 * 设置：过期天数
	 */
	public void setGoodsExp(Integer goodsExp) {
		this.goodsExp = goodsExp;
	}
	/**
	 * 获取：过期天数
	 */
	public Integer getGoodsExp() {
		return goodsExp;
	}
	/**
	 * 设置：单价
	 */
	public void setGoodsBid(Long goodsBid) {
		this.goodsBid = goodsBid;
	}
	/**
	 * 获取：单价
	 */
	public Long getGoodsBid() {
		return goodsBid;
	}
	/**
	 * 设置：规格
	 */
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}
	/**
	 * 获取：规格
	 */
	public String getGoodsSize() {
		return goodsSize;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	/**
	 * 获取：创建日期
	 */
	public String getCreateDt() {
		return createDt;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateTm() {
		return createTm;
	}
	/**
	 * 设置：创建用户
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建用户
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：修改日期
	 */
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	/**
	 * 获取：修改日期
	 */
	public String getUpdateDt() {
		return updateDt;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTm(String updateTm) {
		this.updateTm = updateTm;
	}
	/**
	 * 获取：修改时间
	 */
	public String getUpdateTm() {
		return updateTm;
	}
	/**
	 * 设置：修改用户
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：修改用户
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：时间戳
	 */
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	/**
	 * 获取：时间戳
	 */
	public String getTmSmp() {
		return tmSmp;
	}
}
