package com.johnny.exercise.reports;

import java.util.ArrayList;
import java.util.HashMap;

import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeOne;
import com.johnny.exercise.messages.MessageTypeThree;
import com.johnny.exercise.messages.MessageTypeTwo;
import com.johnny.exercise.sales.Sale;

public class SaleReport {

	public static void processData(ArrayList<Message> messageList) throws Exception{

		HashMap<String, ArrayList<Sale>> productHashMap = new HashMap<String, ArrayList<Sale>>();

		for (Message msg : messageList){

			Sale saleInMessage = msg.getSaleDetails();

			if (msg.getClass() == MessageTypeOne.class || msg.getClass() == MessageTypeTwo.class){
				//MessageType One or Two
				if (productHashMap.get(saleInMessage.getProductType()) == null){
					//need to clone?
					ArrayList<Sale> productSaleDetailList = new ArrayList<Sale>();
					productSaleDetailList.add(saleInMessage);
					productHashMap.put(saleInMessage.getProductType(), productSaleDetailList);
				}
				else{
					ArrayList<Sale> productSaleDetailList = productHashMap.get(saleInMessage.getProductType());
					productSaleDetailList.add(saleInMessage);
					productHashMap.put(saleInMessage.getProductType(), productSaleDetailList);
				}
			}
			else{
				//MessageTypeThree
				MessageTypeThree msgTypeThree = (MessageTypeThree)msg;

				if (!isProductTypeExistInMap(productHashMap, saleInMessage)){
					//record not exist
					throw new Exception("Type three message can't exist before having other type of messages");
				}
				else{
					ArrayList<Sale> productSaleDetailList = productHashMap.get(saleInMessage.getProductType());
					//System.out.println(productSaleDetailList);

					String operationRequired = msgTypeThree.getOperation();

					for (Sale saleElement : productSaleDetailList){
						if (operationRequired.equals(msgTypeThree.ADDOPERATION)){
							saleElement.setSalePrice(saleElement.getSalePrice() + saleInMessage.getSalePrice());
						}
						else if (operationRequired.equals(msgTypeThree.SUBSTRACTOPERATION)){
							saleElement.setSalePrice(saleElement.getSalePrice() - saleInMessage.getSalePrice());
						}
						else if (operationRequired.equals(msgTypeThree.MULTIPLYOPERATION)){
							saleElement.setSalePrice(saleElement.getSalePrice() * msgTypeThree.getMultiplier());
						}
					}
				}
			}
		}
		System.out.println(productHashMap);
	}


	public static void generateReport(){
	}

	public static boolean isProductTypeExistInMap(HashMap<String, ArrayList<Sale>> productHashMap, Sale _tmpSale){
		return (productHashMap.get(_tmpSale.getProductType()) != null);

	}

}
