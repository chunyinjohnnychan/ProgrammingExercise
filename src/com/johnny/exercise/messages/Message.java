package com.johnny.exercise.messages;

import com.johnny.exercise.sales.Sale;

public abstract class Message {
	
	private Sale saleDetails;

	public Sale getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(Sale saleDetails) {
		this.saleDetails = saleDetails;
	}
	
}
