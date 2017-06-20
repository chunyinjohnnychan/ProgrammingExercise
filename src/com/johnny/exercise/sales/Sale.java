package com.johnny.exercise.sales;

public abstract class Sale {

	private String saleTypeId;
	
	private float salePrice;

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
	
}
