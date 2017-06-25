package com.johnny.exercise.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeThree;

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
	
	public static String generateReportString(HashMap<String, ArrayList<MessageTypeThree>> TypeThreeMessageHashMap){
		String result = "";
		result += "###Start of Adjustment Report###" + System.lineSeparator();
		
		for(Entry<String, ArrayList<MessageTypeThree>> entry : TypeThreeMessageHashMap.entrySet()) {
		    String key = entry.getKey();
		    ArrayList<MessageTypeThree> adjMsgGroupedByProduct = entry.getValue();
		    
		    //System.out.println("Product : " + key);
		    result += "Product : " + key + System.lineSeparator();
		    
		    for(int i=0; i<adjMsgGroupedByProduct.size(); i++){
		    	//System.out.println("Adjustment " + i + ": " + adjMsgGroupedByProduct.get(i));
		    	result += "Adjustment " + i + ": " + adjMsgGroupedByProduct.get(i).getOperationDetails() + System.lineSeparator();
		    }
		    
		}
		//System.out.println("###End of Adjustment Report###");
		result += "###End of Adjustment Report###" + System.lineSeparator();
		
		return result;
	}
}
