package com.johnny.exercise.reports;

import java.util.ArrayList;
import java.util.HashMap;

import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeOne;
import com.johnny.exercise.messages.MessageTypeTwo;
import com.johnny.exercise.sales.Sale;

public class SaleReport {

	private ArrayList<Message> messageList;
	HashMap<String, SaleReportObject> productHashMap;

	public SaleReport(ArrayList<Message> _messageList){
		for (Message msg : _messageList){
			this.messageList.add();
		}
	}

	public void calculate(){

		for (Message msg : this.messageList){

			Sale tmpSale = msg.getSaleDetails();

			if (msg.getClass() == MessageTypeOne.class){
				if (!isProductTypeExistInMap(tmpSale)){
					//record not exist
					productHashMap.put(tmpSale.getProductType(), new SaleReportObject(tmpSale.getQuantity(),tmpSale.getQuantity()*tmpSale.getSalePrice()));
				}else{
					//record already exist
					SaleReportObject tmp = productHashMap.get(tmpSale.getProductType());
					tmp.setTotalValue(tmp.getTotalValue() + tmpSale.getSalePrice()*1);
				}
			}
			else if (msg.getClass() == MessageTypeTwo.class){

			}else{
				//MessageTypeThree
			}
			//			if (isProductTypeExistInMap(tmpSale)){
			//				saleHashMap.put(tmpSale.getProductType(), tmpSale.getSalePrice());	
			//			}else{
			//
			//			}
		}
	}

	public void generateReport(){
	}

	public boolean isProductTypeExistInMap(Sale _tmpSale){
		return (productHashMap.get(_tmpSale.getProductType()) != null);

	}

}
