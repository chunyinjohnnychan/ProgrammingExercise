package com.johnny.exercise.sales;

public class Sale {

	private String productType;
	
	private float salePrice;

	public String getSaleTypeId() {
		return productType;
	}

	public void setSaleTypeId(String saleTypeId) {
		this.productType = saleTypeId;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	
	public Sale(String _productType, float _salePrice){
		this.productType = _productType;
		this.salePrice = _salePrice;
	}
	
}
