package com.alke.services.response;

import com.alke.services.beans.Client;

public class ClientRes {

	private Client client;
	private String success;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
}
