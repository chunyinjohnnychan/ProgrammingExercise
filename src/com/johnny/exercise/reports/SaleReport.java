package com.johnny.exercise.reports;

import java.util.ArrayList;
import java.util.HashMap;

import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeOne;
import com.johnny.exercise.messages.MessageTypeTwo;
import com.johnny.exercise.sales.Sale;

public class SaleReport {

	private ArrayList<Message> messageList;
	private static HashMap<String, SaleReportObject> productHashMap;

	public SaleReport(ArrayList<Message> _messageList){
		this.messageList.addAll(_messageList);
	}

	public void calculate() throws Exception{

		for (Message msg : this.messageList){

			Sale tmpSale = msg.getSaleDetails();

			if (msg.isProcessed() != true){
				//only need to calculate unprocessed message
				if (msg.getClass() == MessageTypeOne.class){
					if (!isProductTypeExistInMap(tmpSale)){
						//record not exist
						productHashMap.put(tmpSale.getProductType(), new SaleReportObject(tmpSale.getQuantity(),tmpSale.getQuantity()*tmpSale.getSalePrice()));
					}else{
						//record already exist
						SaleReportObject tmp = productHashMap.get(tmpSale.getProductType());
						tmp.setTotalValue(tmp.getTotalValue() + tmpSale.getSalePrice()*1);
						tmp.setQuantity(tmp.getQuantity()+tmpSale.getQuantity());
					}
				}
				else if (msg.getClass() == MessageTypeTwo.class){
					if (!isProductTypeExistInMap(tmpSale)){
						//record not exist
						productHashMap.put(tmpSale.getProductType(), new SaleReportObject(tmpSale.getQuantity(),tmpSale.getQuantity()*tmpSale.getSalePrice()));
					}else{
						//record already exist
						SaleReportObject tmp = productHashMap.get(tmpSale.getProductType());
						tmp.setTotalValue(tmp.getTotalValue() + tmpSale.getSalePrice()*tmpSale.getQuantity());
						tmp.setQuantity(tmp.getQuantity()+tmpSale.getQuantity());
					}
				}else{
					//MessageTypeThree
					if (!isProductTypeExistInMap(tmpSale)){
						//record not exist
						throw new Exception("Type three message can't exist before having other type of messages");
					}else{
						//record already exist
						SaleReportObject tmp = productHashMap.get(tmpSale.getProductType());
						tmp.setTotalValue(tmp.getTotalValue() + tmpSale.getSalePrice()*tmpSale.getQuantity());
						tmp.setQuantity(tmp.getQuantity()+tmpSale.getQuantity());
					}
				}
				msg.setProcessed(true);
			}
		}
	}

	public void generateReport(){
	}

	public boolean isProductTypeExistInMap(Sale _tmpSale){
		return (productHashMap.get(_tmpSale.getProductType()) != null);

	}

}
