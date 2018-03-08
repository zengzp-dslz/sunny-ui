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
public class SunnystocklogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//库存日志编号
	private String stocklogId;
	//调用方法
	private String method;
	//参数
	private String params;
	//创建时间
	private String tmSmp;

	/**
	 * 设置：库存日志编号
	 */
	public void setStocklogId(String stocklogId) {
		this.stocklogId = stocklogId;
	}
	/**
	 * 获取：库存日志编号
	 */
	public String getStocklogId() {
		return stocklogId;
	}
	/**
	 * 设置：调用方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：调用方法
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：参数
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 获取：参数
	 */
	public String getParams() {
		return params;
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
