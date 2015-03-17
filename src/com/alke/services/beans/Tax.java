package com.alke.services.beans;

public class Tax {
	
	private long orderId;
	private long taxId;
	private String taxName;
	private double calc;
	private double cost;
	private double taxAmounted;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getTaxId() {
		return taxId;
	}
	public void setTaxId(long taxId) {
		this.taxId = taxId;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public double getCalc() {
		return calc;
	}
	public void setCalc(double calc) {
		this.calc = calc;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getTaxAmounted() {
		return taxAmounted;
	}
	public void setTaxAmounted(double taxAmounted) {
		this.taxAmounted = taxAmounted;
	}
	

}
