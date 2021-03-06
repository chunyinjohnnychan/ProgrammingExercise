package com.johnny.exercise.sales;

import java.text.DecimalFormat;

public class Sale {

	private String productType;
	private float salePrice;
	private int quantity;

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	
	public Sale(String _productType, float _salePrice, int _quantity){
		this.productType = _productType;
		this.salePrice = _salePrice;
		this.quantity = _quantity;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		String result = "";
		result += "[";
		result += " productType: " + this.productType;
		result += " salePrice: " + df.format(this.salePrice);
		result += " quantity: " + this.quantity;
		result += "]";
		
		return result;
		
	}
	
}
