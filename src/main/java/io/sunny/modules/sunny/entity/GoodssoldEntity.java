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
public class GoodssoldEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String goodsId;
	//
	private String goodsNo;
	//
	private String batNo;
	//
	private String tmSmp;

	/**
	 * 设置：
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：
	 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	/**
	 * 获取：
	 */
	public String getGoodsNo() {
		return goodsNo;
	}
	/**
	 * 设置：
	 */
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	/**
	 * 获取：
	 */
	public String getBatNo() {
		return batNo;
	}
	/**
	 * 设置：
	 */
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
	}
	/**
	 * 获取：
	 */
	public String getTmSmp() {
		return tmSmp;
	}
}
