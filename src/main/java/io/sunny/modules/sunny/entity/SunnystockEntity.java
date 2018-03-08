package io.sunny.modules.sunny.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author zdy
 * @email sunny@sunny.com
 * @date 2018-01-16 11:31:55
 */
public class SunnystockEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//商户编号
	private String mercId;
	//商户名称
	private String mercNm;
	//商品编号
	private String goodsId;
	//商品名称
	private String goodsName;
	//条形码
	private String barcode;
	//成本价
	private Long goodsCid;
	//售价（单价）
	private Long goodsBid;
	//库存总数
	private Integer goodsNum;
	//即时库存
	private Integer goodsIns;
	//修改日期
	private String updateDt;
	//修改时间
	private String updateTm;
	//创建时间
	private String tmSmp;

	/**
	 * 设置：商户名称
	 */
	public void setMercNm(String mercNm) {
		this.mercNm = mercNm;
	}
	/**
	 * 获取：商户名称
	 */
	public String getMercNm() {
		return mercNm;
	}
	/**
	 * 设置：商户编号
	 */
	public void setMercId(String mercId) {
		this.mercId = mercId;
	}
	/**
	 * 获取：商户编号
	 */
	public String getMercId() {
		return mercId;
	}
	/**
	 * 设置：商品编号
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品编号
	 */
	public String getGoodsId() {
		return goodsId;
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
	 * 设置：条形码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	/**
	 * 获取：条形码
	 */
	public String getBarcode() {
		return barcode;
	}
	/**
	 * 设置：成本价
	 */
	public void setGoodsCid(Long goodsCid) {
		this.goodsCid = goodsCid;
	}
	/**
	 * 获取：成本价
	 */
	public Long getGoodsCid() {
		return goodsCid;
	}
	/**
	 * 设置：售价（单价）
	 */
	public void setGoodsBid(Long goodsBid) {
		this.goodsBid = goodsBid;
	}
	/**
	 * 获取：售价（单价）
	 */
	public Long getGoodsBid() {
		return goodsBid;
	}
	/**
	 * 设置：库存总数
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	/**
	 * 获取：库存总数
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	/**
	 * 设置：即时库存
	 */
	public void setGoodsIns(Integer goodsIns) {
		this.goodsIns = goodsIns;
	}
	/**
	 * 获取：即时库存
	 */
	public Integer getGoodsIns() {
		return goodsIns;
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
	 * 设置：创建时间
	 */
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	/**
	 * 获取：创建时间
	 */
	public String getTmSmp() {
		return tmSmp;
	}
}
