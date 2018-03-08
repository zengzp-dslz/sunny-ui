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
public class ManageuserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String managerId;
	//
	private String loginName;
	//
	private String name;
	//
	private String nationCode;
	//
	private String mblNo;
	//
	private String managerAddr;
	//
	private String provCd;
	//
	private String cityCd;
	//
	private String countryCd;
	//
	private String password;
	//
	private String code;

	/**
	 * 设置：
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	/**
	 * 获取：
	 */
	public String getManagerId() {
		return managerId;
	}
	/**
	 * 设置：
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 获取：
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	/**
	 * 获取：
	 */
	public String getNationCode() {
		return nationCode;
	}
	/**
	 * 设置：
	 */
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}
	/**
	 * 获取：
	 */
	public String getMblNo() {
		return mblNo;
	}
	/**
	 * 设置：
	 */
	public void setManagerAddr(String managerAddr) {
		this.managerAddr = managerAddr;
	}
	/**
	 * 获取：
	 */
	public String getManagerAddr() {
		return managerAddr;
	}
	/**
	 * 设置：
	 */
	public void setProvCd(String provCd) {
		this.provCd = provCd;
	}
	/**
	 * 获取：
	 */
	public String getProvCd() {
		return provCd;
	}
	/**
	 * 设置：
	 */
	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}
	/**
	 * 获取：
	 */
	public String getCityCd() {
		return cityCd;
	}
	/**
	 * 设置：
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	/**
	 * 获取：
	 */
	public String getCountryCd() {
		return countryCd;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：
	 */
	public String getCode() {
		return code;
	}
}
