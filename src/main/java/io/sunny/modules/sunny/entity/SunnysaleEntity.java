package io.sunny.modules.sunny.entity;

import java.io.Serializable;

public class SunnysaleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//订单日期
	private String ordDt;
	//支付金额
	private Long payAmt;
	//成本价
	private Long costAmt;
	//笔数
	private Long count;
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	public Long getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(Long payAmt) {
		this.payAmt = payAmt;
	}
	public Long getCostAmt() {
		return costAmt;
	}
	public void setCostAmt(Long costAmt) {
		this.costAmt = costAmt;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

}
