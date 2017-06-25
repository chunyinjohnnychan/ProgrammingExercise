package com.johnny.exercise.reports;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeOne;
import com.johnny.exercise.messages.MessageTypeThree;
import com.johnny.exercise.messages.MessageTypeTwo;
import com.johnny.exercise.sales.Sale;

public class SaleReport {

	public static HashMap<String, ArrayList<Sale>> processData(ArrayList<Message> messageList) throws Exception{

		HashMap<String, ArrayList<Sale>> saleReportHashMap = new HashMap<String, ArrayList<Sale>>();

		for (Message msg : messageList){

			Sale saleInMessage = msg.getSaleDetails();

			if (msg.getClass() == MessageTypeOne.class || msg.getClass() == MessageTypeTwo.class){
				//MessageType One or Two
				if (saleReportHashMap.get(saleInMessage.getProductType()) == null){
					ArrayList<Sale> productSaleDetailList = new ArrayList<Sale>();
					productSaleDetailList.add(saleInMessage);
					saleReportHashMap.put(saleInMessage.getProductType(), productSaleDetailList);
				}
				else{
					ArrayList<Sale> productSaleDetailList = saleReportHashMap.get(saleInMessage.getProductType());
					productSaleDetailList.add(saleInMessage);
					saleReportHashMap.put(saleInMessage.getProductType(), productSaleDetailList);
				}
			}
			else{
				//MessageTypeThree
				MessageTypeThree msgTypeThree = (MessageTypeThree)msg;

				if (msgTypeThree.isProcessed() != true){
					if (!isProductTypeExistInMap(saleReportHashMap, saleInMessage)){
						//record not exist
						throw new Exception("Type three message can't exist before having other type of messages");
					}
					else{
						ArrayList<Sale> productSaleDetailList = saleReportHashMap.get(saleInMessage.getProductType());
						//System.out.println(productSaleDetailList);

						String operationRequired = msgTypeThree.getOperation();

						for (Sale saleElement : productSaleDetailList){
							if (operationRequired.equals(MessageTypeThree.ADDOPERATION)){
								saleElement.setSalePrice(saleElement.getSalePrice() + saleInMessage.getSalePrice());
							}
							else if (operationRequired.equals(MessageTypeThree.SUBSTRACTOPERATION)){
								saleElement.setSalePrice(saleElement.getSalePrice() - saleInMessage.getSalePrice());
							}
							else if (operationRequired.equals(MessageTypeThree.MULTIPLYOPERATION)){
								saleElement.setSalePrice(saleElement.getSalePrice() * msgTypeThree.getMultiplier());
							}
						}
					}
				}
				msgTypeThree.setProcessed(true);
			}
		}
		//System.out.println(productHashMap);
		return saleReportHashMap;
	}


	public static String generateReportString(HashMap<String, ArrayList<Sale>> saleReportHashMap){
		
		String result = "";
		//System.out.println("***Start of Sale Report***");
		result += "***Start of Sale Report***" + System.lineSeparator();
		
		for(Entry<String, ArrayList<Sale>> entry : saleReportHashMap.entrySet()) {
		    String key = entry.getKey();
		    
		    ArrayList<Sale> saleOrdersByProduct = entry.getValue();
		    
		    //get number of Sale of this product
		    int numOfSale = 0;
		    for (Sale tmp : saleOrdersByProduct){
		    	numOfSale += tmp.getQuantity();
		    }
		    
		    //get total value
		    float totalValue = 0;
		    for (Sale tmp : saleOrdersByProduct){
		    	totalValue += tmp.getSalePrice() * tmp.getQuantity();
		    }
		    
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			
		    
		    //System.out.println("Product: " + key);
			result += "Product: " + key +System.lineSeparator();
			
		    //System.out.println("Number of Sales of " + key + " : " + numOfSale);
			result += "Number of Sales of " + key + " : " + numOfSale + System.lineSeparator();
			
		    //System.out.println("Total Value of " + key + " : " + df.format(totalValue));
			result += "Total Value of " + key + " : " + df.format(totalValue) + System.lineSeparator();
		}
		//System.out.println("***End of Sale Report***");
		result += "***End of Sale Report***" + System.lineSeparator();
		
		return result;
	}

	private static boolean isProductTypeExistInMap(HashMap<String, ArrayList<Sale>> productHashMap, Sale _tmpSale){
		return (productHashMap.get(_tmpSale.getProductType()) != null);

	}

}
