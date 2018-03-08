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
public class NoticeuserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//国际区号
	private String nationCode;
	//手机号码
	private String mblNo;
	//姓名
	private String userNm;
	//性别
	private String userSex;
	//通知开始时间
	private String beginTm;
	//通知结束时间
	private String endTm;
	//管理省份
	private String mngProv;
	//管理地市
	private String mngCity;
	//管理县区
	private String mngCountry;
	//是否开启 1开启 0 关闭
	private String status;
	//时间戳
	private String tmSmp;

	/**
	 * 设置：国际区号
	 */
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	/**
	 * 获取：国际区号
	 */
	public String getNationCode() {
		return nationCode;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMblNo() {
		return mblNo;
	}
	/**
	 * 设置：姓名
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * 获取：姓名
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * 设置：性别
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	/**
	 * 获取：性别
	 */
	public String getUserSex() {
		return userSex;
	}
	/**
	 * 设置：通知开始时间
	 */
	public void setBeginTm(String beginTm) {
		this.beginTm = beginTm;
	}
	/**
	 * 获取：通知开始时间
	 */
	public String getBeginTm() {
		return beginTm;
	}
	/**
	 * 设置：通知结束时间
	 */
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
	/**
	 * 获取：通知结束时间
	 */
	public String getEndTm() {
		return endTm;
	}
	/**
	 * 设置：管理省份
	 */
	public void setMngProv(String mngProv) {
		this.mngProv = mngProv;
	}
	/**
	 * 获取：管理省份
	 */
	public String getMngProv() {
		return mngProv;
	}
	/**
	 * 设置：管理地市
	 */
	public void setMngCity(String mngCity) {
		this.mngCity = mngCity;
	}
	/**
	 * 获取：管理地市
	 */
	public String getMngCity() {
		return mngCity;
	}
	/**
	 * 设置：管理县区
	 */
	public void setMngCountry(String mngCountry) {
		this.mngCountry = mngCountry;
	}
	/**
	 * 获取：管理县区
	 */
	public String getMngCountry() {
		return mngCountry;
	}
	/**
	 * 设置：是否开启 1开启 0 关闭
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：是否开启 1开启 0 关闭
	 */
	public String getStatus() {
		return status;
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
