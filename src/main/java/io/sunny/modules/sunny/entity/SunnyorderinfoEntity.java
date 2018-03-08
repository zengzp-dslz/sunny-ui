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
public class SunnyorderinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String ordNo;
	//id
	private String goodsId;
	//id
	private String goodsNo;
	//id
	private String batNo;
	//单价
	private Long goodsBid;
	//时间戳
	private String tmSmp;
	//产品名称
	private String goodsName;
	//产品类型
	private String goodsType;
	/**
	 * 设置：id
	 */
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	/**
	 * 获取：id
	 */
	public String getOrdNo() {
		return ordNo;
	}
	/**
	 * 设置：id
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：id
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：id
	 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	/**
	 * 获取：id
	 */
	public String getGoodsNo() {
		return goodsNo;
	}
	/**
	 * 设置：id
	 */
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	/**
	 * 获取：id
	 */
	public String getBatNo() {
		return batNo;
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
	/**
	 * 设置：产品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：产品类型
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	/**
	 * 获取：产品类型
	 */
	public String getGoodsType() {
		return goodsType;
	}
	
}
