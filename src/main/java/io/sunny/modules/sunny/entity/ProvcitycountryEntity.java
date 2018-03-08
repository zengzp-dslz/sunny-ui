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
public class ProvcitycountryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//省编码
	private String provCd;
	//省名称
	private String provNm;
	//地市编码
	private String cityCd;
	//地市名称
	private String cityNm;
	//省区编码
	private String countryCd;
	//省区名称
	private String countryNm;
	//时间戳
	private String tmSmp;

	/**
	 * 设置：省编码
	 */
	public void setProvCd(String provCd) {
		this.provCd = provCd;
	}
	/**
	 * 获取：省编码
	 */
	public String getProvCd() {
		return provCd;
	}
	/**
	 * 设置：省名称
	 */
	public void setProvNm(String provNm) {
		this.provNm = provNm;
	}
	/**
	 * 获取：省名称
	 */
	public String getProvNm() {
		return provNm;
	}
	/**
	 * 设置：地市编码
	 */
	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}
	/**
	 * 获取：地市编码
	 */
	public String getCityCd() {
		return cityCd;
	}
	/**
	 * 设置：地市名称
	 */
	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}
	/**
	 * 获取：地市名称
	 */
	public String getCityNm() {
		return cityNm;
	}
	/**
	 * 设置：省区编码
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	/**
	 * 获取：省区编码
	 */
	public String getCountryCd() {
		return countryCd;
	}
	/**
	 * 设置：省区名称
	 */
	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}
	/**
	 * 获取：省区名称
	 */
	public String getCountryNm() {
		return countryNm;
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
