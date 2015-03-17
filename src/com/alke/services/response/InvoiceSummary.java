package com.alke.services.response;

import java.util.List;

import com.alke.services.beans.Invoice;

public class InvoiceSummary {

	private List<Invoice> invoice;
	private String success;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public List<Invoice> getInvoice() {
		return invoice;
	}
	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}
}
