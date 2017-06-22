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

public class AdjustmentReport {
	
	public static HashMap<String, ArrayList<MessageTypeThree>> processData(ArrayList<Message> messageList){
		
		HashMap<String, ArrayList<MessageTypeThree>> TypeThreeMessageHashMap = new HashMap<String, ArrayList<MessageTypeThree>>();
		
		for (Message msg : messageList){
			if (msg.getClass() == MessageTypeThree.class){
				MessageTypeThree msgTypeThree = (MessageTypeThree)msg;
				
				if (TypeThreeMessageHashMap.get(msgTypeThree.getSaleDetails().getProductType()) == null){
					ArrayList<MessageTypeThree> adjMsgGroupedByProduct = new ArrayList<MessageTypeThree>();
					adjMsgGroupedByProduct.add(msgTypeThree);
					TypeThreeMessageHashMap.put(msgTypeThree.getSaleDetails().getProductType(), adjMsgGroupedByProduct);
				}
				else{
					ArrayList<MessageTypeThree> adjMsgGroupedByProduct = TypeThreeMessageHashMap.get(msgTypeThree.getSaleDetails().getProductType());
					adjMsgGroupedByProduct.add(msgTypeThree);
					TypeThreeMessageHashMap.put(msgTypeThree.getSaleDetails().getProductType(), adjMsgGroupedByProduct);
				}
			}
		}
		
		//System.out.println(TypeThreeMessageHashMap);
		
		return TypeThreeMessageHashMap;
	}
	
	public static void generateReport(HashMap<String, ArrayList<MessageTypeThree>> TypeThreeMessageHashMap){
		System.out.println("###Start of Adjustment Report###");
		
		for(Entry<String, ArrayList<MessageTypeThree>> entry : TypeThreeMessageHashMap.entrySet()) {
		    String key = entry.getKey();
		    ArrayList<MessageTypeThree> adjMsgGroupedByProduct = entry.getValue();
		    
		    System.out.println("Product : " + key);
		    
		    for(int i=0; i<adjMsgGroupedByProduct.size(); i++){
		    	System.out.println("Adjustment " + i + ": " + adjMsgGroupedByProduct.get(i));
		    }
		    
		}
		System.out.println("###End of Adjustment Report###");
	}
}
