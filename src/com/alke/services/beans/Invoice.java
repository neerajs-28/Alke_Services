package com.alke.services.beans;

import java.util.Date;
import java.util.List;

public class Invoice {

	
	private long orderId;
	private String invoiceId;
	private Date invoicedate;
	private Double invoiceCost;
	private String  invoicePayStatus;
	private String po;
	private Date poDate;
	private Date invoicePayDate;
	private String invoiceOrdrStatus;
	private int rating;
	private List<String> comments;
	private String downloadLink;
	private int year;
	private long clientId;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Date getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}
	public Double getInvoiceCost() {
		return invoiceCost;
	}
	public void setInvoiceCost(Double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}
	public String getInvoicePayStatus() {
		return invoicePayStatus;
	}
	public void setInvoicePayStatus(String invoicePayStatus) {
		this.invoicePayStatus = invoicePayStatus;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getInvoiceOrdrStatus() {
		return invoiceOrdrStatus;
	}
	public void setInvoiceOrdrStatus(String invoiceOrdrStatus) {
		this.invoiceOrdrStatus = invoiceOrdrStatus;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public Date getInvoicePayDate() {
		return invoicePayDate;
	}
	public void setInvoicePayDate(Date invoicePayDate) {
		this.invoicePayDate = invoicePayDate;
	}
	
}
