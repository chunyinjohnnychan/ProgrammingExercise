package com.johnny.exercise.messages;

import com.johnny.exercise.sales.Sale;

public abstract class Message {
	
	private int messageTypeNumber;
	private Sale saleDetails;

	public Sale getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(Sale saleDetails) {
		this.saleDetails = saleDetails;
	}

	public int getMessageTypeNumber() {
		return messageTypeNumber;
	}

	public void setMessageTypeNumber(int messageTypeNumber) {
		this.messageTypeNumber = messageTypeNumber;
	}
	
	protected String getBasicMessageInformation(){
		String result = "";
		result += " Message Type Number: " + this.messageTypeNumber;
		result += " Sale Details: " + this.saleDetails;
		return result;
	}
	
	@Override
	public String toString(){
		String result = "";
		result += "[";
		result += this.getBasicMessageInformation();
		result += "]";
		
		return result;
	}

}
