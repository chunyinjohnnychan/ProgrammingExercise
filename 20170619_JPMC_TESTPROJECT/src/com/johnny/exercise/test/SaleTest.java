package com.johnny.exercise.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.johnny.exercise.MainProgram;
import com.johnny.exercise.messages.Message;
import com.johnny.exercise.sales.Sale;

public class SaleTest {
	
	final static String expectedSaleRecord = "[[ productType: APPLE salePrice: 0.10 quantity: 1], [ productType: APPLE salePrice: 0.20 quantity: 20], [ productType: APPLE salePrice: 0.10 quantity: -1], [ productType: APPLE salePrice: 0.05 quantity: -1], [ productType: APPLE salePrice: -1.00 quantity: -1]]";

	@Test
	public void testRecordSale() {
		MessageTest mt = new MessageTest();
		File testMessage = null;
		
		try {
			testMessage = mt.createTestMessage();
		} catch (IOException e) {
			mt.deleteTestMessage();
			try {
				testMessage = mt.createTestMessage();
			} catch (IOException e1) {
				fail("SaleTest retry failed, please run this test individually!");
			}
		}
		
		MainProgram testpg = new MainProgram();
		testpg.readAndProcessMessage(new File[]{testMessage});
		ArrayList<Sale> saleList = new ArrayList<Sale>();
		
		for (Message msg : testpg.getMessageList()){
			saleList.add(msg.getSaleDetails());
		}
		
		String actualSaleRecord = saleList.toString();
		//System.out.println(actualSaleRecord);
		mt.deleteTestMessage();
		assertEquals(expectedSaleRecord, actualSaleRecord);
		
	}

}
