package com.johnny.exercise.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.johnny.exercise.MainProgram;
import com.johnny.exercise.util.CSVUtils;

public class SaleReportTest {
	
	String expectedResult = "***Start of Sale Report***\r\nProduct: APPLE\r\nNumber of Sales of APPLE : 21\r\nTotal Value of APPLE : 42.00\r\nProduct: PIE\r\nNumber of Sales of PIE : 20\r\nTotal Value of PIE : 20.00\r\n***End of Sale Report***\r\n";

	@Test
	public void testSaleReport() {
		SaleReportTest st = new SaleReportTest();
		File testMessage = null;

		try {
			testMessage = st.createSaleReportTestMessages();
		} catch (IOException e) {
			this.deleteTestMessage();
			try {
				testMessage = st.createSaleReportTestMessages();
			} catch (IOException e1) {
				fail("SaleReport Test retry failed, please run this test individually!");
			}
		}

		MainProgram testpg = new MainProgram();
		testpg.setADJUSTMENTREPORTTRIGGER(99999);
		testpg.setSALEREPORTTRIGGER(99999);
		testpg.readAndProcessMessage(new File[]{testMessage});
		
		String testResult = null;
		try {
			testResult = testpg.processSaleReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(testResult);
		this.deleteTestMessage();
		assertEquals(expectedResult, testResult);
	}

	File createSaleReportTestMessages() throws IOException{
		String csvFile = "C:/Users/johnn/git/ProgrammingExercise/20170619_JPMC/InputFiles/test.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtils.writeLine(writer, Arrays.asList("1","APPLE","1" ,"1" ,""        ,"" ));
		CSVUtils.writeLine(writer, Arrays.asList("2","APPLE","1" ,"20",""        ,"" ));
		CSVUtils.writeLine(writer, Arrays.asList("2","PIE"  ,"1" ,"20",""        ,"" ));
		CSVUtils.writeLine(writer, Arrays.asList("3","APPLE","1" ,""  ,"ADD"     ,"" ));
		CSVUtils.writeLine(writer, Arrays.asList("3","APPLE","1" ,""  ,"SUBTRACT","" ));
		CSVUtils.writeLine(writer, Arrays.asList("3","APPLE",""  ,""  ,"MULTIPLY","2"));

		writer.flush();
		writer.close();

		return new File(csvFile);
	}

	void deleteTestMessage(){
		File inputTestFile = new File( "C:/Users/johnn/git/ProgrammingExercise/20170619_JPMC/InputFiles/test.csv");
		FileUtils.deleteQuietly(inputTestFile);

		File testfile = new File("C:/Users/johnn/git/ProgrammingExercise/20170619_JPMC/OutputFiles/test.csv");
		FileUtils.deleteQuietly(testfile);
	}



}
