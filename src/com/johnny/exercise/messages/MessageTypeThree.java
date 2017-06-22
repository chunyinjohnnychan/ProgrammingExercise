package com.johnny.exercise.messages;

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
	
	
}
