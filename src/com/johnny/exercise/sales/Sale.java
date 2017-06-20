package com.johnny.exercise.sales;

public abstract class Sale {

	private String saleTypeId;
	
	private float salePrice;
	
	private boolean processed;

	public String getSaleTypeId() {
		return saleTypeId;
	}

	public void setSaleTypeId(String saleTypeId) {
		this.saleTypeId = saleTypeId;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
}
