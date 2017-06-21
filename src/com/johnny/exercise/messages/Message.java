package com.johnny.exercise.messages;

import com.johnny.exercise.sales.Sale;

public abstract class Message {

	private Sale saleDetails;
	private boolean isProcessed = false;

	public Sale getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(Sale saleDetails) {
		this.saleDetails = saleDetails;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public abstract Message clone();

}
