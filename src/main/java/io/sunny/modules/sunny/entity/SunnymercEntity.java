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
public class SunnymercEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String mercId;
	//id（用于下拉框）
	private String id;
	//名称
	private String mercNm;
	//名称（用于下拉框）
	private String name;
	//地址
	private String mercAddr;
	//类型
	private String mercType;
	//行业
	private String mercAttr;
	//电话
	private String mercTele;
	//管理省份
	private String mercProv;
	//
	private String mercCity;
	//
	private String mercCountry;
	//商户状态 Y开放 N 关闭
	private String mercSts;
	//商户安装设备的id
	private String deviceId;
	//发送给商户的topic
	private String mercTopic;
	//简介
	private String mercDesc;
	//时间戳
	private String tmSmp;

	/**
	 * 设置：id
	 */
	public void setMercId(String mercId) {
		this.mercId = mercId;
	}
	/**
	 * 获取：id
	 */
	public String getMercId() {
		return mercId;
	}
	/**
	 * 设置：名称
	 */
	public void setMercNm(String mercNm) {
		this.mercNm = mercNm;
	}
	/**
	 * 获取：名称
	 */
	public String getMercNm() {
		return mercNm;
	}
	/**
	 * 设置：地址
	 */
	public void setMercAddr(String mercAddr) {
		this.mercAddr = mercAddr;
	}
	/**
	 * 获取：地址
	 */
	public String getMercAddr() {
		return mercAddr;
	}
	/**
	 * 设置：类型
	 */
	public void setMercType(String mercType) {
		this.mercType = mercType;
	}
	/**
	 * 获取：类型
	 */
	public String getMercType() {
		return mercType;
	}
	/**
	 * 设置：行业
	 */
	public void setMercAttr(String mercAttr) {
		this.mercAttr = mercAttr;
	}
	/**
	 * 获取：行业
	 */
	public String getMercAttr() {
		return mercAttr;
	}
	/**
	 * 设置：电话
	 */
	public void setMercTele(String mercTele) {
		this.mercTele = mercTele;
	}
	/**
	 * 获取：电话
	 */
	public String getMercTele() {
		return mercTele;
	}
	/**
	 * 设置：管理省份
	 */
	public void setMercProv(String mercProv) {
		this.mercProv = mercProv;
	}
	/**
	 * 获取：管理省份
	 */
	public String getMercProv() {
		return mercProv;
	}
	/**
	 * 设置：
	 */
	public void setMercCity(String mercCity) {
		this.mercCity = mercCity;
	}
	/**
	 * 获取：
	 */
	public String getMercCity() {
		return mercCity;
	}
	/**
	 * 设置：
	 */
	public void setMercCountry(String mercCountry) {
		this.mercCountry = mercCountry;
	}
	/**
	 * 获取：
	 */
	public String getMercCountry() {
		return mercCountry;
	}
	/**
	 * 设置：商户状态 Y开放 N 关闭
	 */
	public void setMercSts(String mercSts) {
		this.mercSts = mercSts;
	}
	/**
	 * 获取：商户状态 Y开放 N 关闭
	 */
	public String getMercSts() {
		return mercSts;
	}
	/**
	 * 设置：商户安装设备的id
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：商户安装设备的id
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：发送给商户的topic
	 */
	public void setMercTopic(String mercTopic) {
		this.mercTopic = mercTopic;
	}
	/**
	 * 获取：发送给商户的topic
	 */
	public String getMercTopic() {
		return mercTopic;
	}
	/**
	 * 设置：简介
	 */
	public void setMercDesc(String mercDesc) {
		this.mercDesc = mercDesc;
	}
	/**
	 * 获取：简介
	 */
	public String getMercDesc() {
		return mercDesc;
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
	 * 设置：ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
}
