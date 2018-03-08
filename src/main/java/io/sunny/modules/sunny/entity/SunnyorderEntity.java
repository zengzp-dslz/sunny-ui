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
public class SunnyorderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String ordNo;
	//订单日期
	private String ordDt;
	//订单时间
	private String ordTm;
	//订单金额
	private Long ordAmt;
	//优惠金额
	private Long freeAmt;
	//支付金额
	private Long payAmt;
	//状态
	private String ordSts;
	//id
	private String mercId;
	//用户id
	private String userType;
	//用户id
	private String useId;
	//支付日期
	private String payDt;
	//支付时间
	private String payTm;
	//时间戳
	private String tmSmp;
	//用户电话
	private String mblNo;
	//状态
	private String ordStatus;

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
	 * 设置：订单日期
	 */
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	/**
	 * 获取：订单日期
	 */
	public String getOrdDt() {
		return ordDt;
	}
	/**
	 * 设置：订单时间
	 */
	public void setOrdTm(String ordTm) {
		this.ordTm = ordTm;
	}
	/**
	 * 获取：订单时间
	 */
	public String getOrdTm() {
		return ordTm;
	}
	/**
	 * 设置：订单金额
	 */
	public void setOrdAmt(Long ordAmt) {
		this.ordAmt = ordAmt;
	}
	/**
	 * 获取：订单金额
	 */
	public Long getOrdAmt() {
		return ordAmt;
	}
	/**
	 * 设置：优惠金额
	 */
	public void setFreeAmt(Long freeAmt) {
		this.freeAmt = freeAmt;
	}
	/**
	 * 获取：优惠金额
	 */
	public Long getFreeAmt() {
		return freeAmt;
	}
	/**
	 * 设置：支付金额
	 */
	public void setPayAmt(Long payAmt) {
		this.payAmt = payAmt;
	}
	/**
	 * 获取：支付金额
	 */
	public Long getPayAmt() {
		return payAmt;
	}
	/**
	 * 设置：状态
	 */
	public void setOrdSts(String ordSts) {
		this.ordSts = ordSts;
	}
	/**
	 * 获取：状态
	 */
	public String getOrdSts() {
		return ordSts;
	}
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
	 * 设置：用户id
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置：用户id
	 */
	public void setUseId(String useId) {
		this.useId = useId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUseId() {
		return useId;
	}
	/**
	 * 设置：支付日期
	 */
	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}
	/**
	 * 获取：支付日期
	 */
	public String getPayDt() {
		return payDt;
	}
	/**
	 * 设置：支付时间
	 */
	public void setPayTm(String payTm) {
		this.payTm = payTm;
	}
	/**
	 * 获取：支付时间
	 */
	public String getPayTm() {
		return payTm;
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
	 * 设置：电话号码
	 */
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}
	/**
	 * 获取：电话号码
	 */
	public String getMblNo() {
		return mblNo;
	}
	public String getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}
	
}

