package com.johnny.exercise.messages;

import java.text.DecimalFormat;

public class MessageTypeThree extends Message{

	private String operation;
	private float multiplier;
	private boolean isProcessed = false;
	
	public static final String ADDOPERATION = "ADD";
	public static final String SUBSTRACTOPERATION = "SUBTRACT";
	public static final String MULTIPLYOPERATION = "MULTIPLY";
	

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}
	
	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	@Override
	public String toString(){
		
		String result = "";
		result += "[";
		result += this.getBasicMessageInformation();
		result += this.getOperationDetails();
		result += "]";
		
		return result;
		
	}
	
	public MessageTypeThree(){
		this.setMessageTypeNumber(3);
	}
	
	public String getOperationDetails(){
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		
		float value = 0;
		if (!this.operation.equals(MULTIPLYOPERATION)){
			//for add and subtract
			value = this.getSaleDetails().getSalePrice();
		}
		else{
			value = this.multiplier;
		}
		
		String result = "";
		result += " Operation: " + this.operation;		
		result += " Value: " + df.format(value);
		
		return result;
	}
	
}
