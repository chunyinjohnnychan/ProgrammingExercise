package com.johnny.exercise.reports;

public class SaleReportObject {
	
	private int quantity;
	private float totalValue;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}
	
	public SaleReportObject (int _quantity, float _totalValue){
		this.quantity = _quantity;
		this.totalValue = _totalValue;
	}

}
