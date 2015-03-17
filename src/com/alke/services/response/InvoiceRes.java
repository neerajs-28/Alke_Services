package com.alke.services.response;

import java.util.List;

public class InvoiceRes {

	private List<InvoiceVO> invoice;
	private String success;
	
	public List<InvoiceVO> getInvoice() {
		return invoice;
	}
	public void setInvoice(List<InvoiceVO> invoice) {
		this.invoice = invoice;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
}
