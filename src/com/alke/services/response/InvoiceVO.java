package com.alke.services.response;

public class InvoiceVO {

	private String itemName;
	private String groupName;
	private Double rate;
	private Double qty;
	private Double total;
	private int srcNo;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(double d) {
		this.qty = d;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getSrcNo() {
		return srcNo;
	}
	public void setSrcNo(int srcNo) {
		this.srcNo = srcNo;
	}
}
