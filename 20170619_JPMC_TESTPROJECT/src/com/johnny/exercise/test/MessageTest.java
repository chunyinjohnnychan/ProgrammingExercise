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

public class MessageTest {
	
	final static String expectedMessageList = "[[ Message Type Number: 1 Sale Details: [ productType: APPLE salePrice: 0.10 quantity: 1]], [ Message Type Number: 2 Sale Details: [ productType: APPLE salePrice: 0.20 quantity: 20]], [ Message Type Number: 3 Sale Details: [ productType: APPLE salePrice: 0.10 quantity: -1] Operation: ADD Value: 0.10], [ Message Type Number: 3 Sale Details: [ productType: APPLE salePrice: 0.05 quantity: -1] Operation: SUBTRACT Value: 0.05], [ Message Type Number: 3 Sale Details: [ productType: APPLE salePrice: -1.00 quantity: -1] Operation: MULTIPLY Value: 2.00]]";


	@Test
	public void testReadMessage() {

		File testMessage = null;
		
		try {
			testMessage = createTestMessage();
		} catch (IOException e) {
			this.deleteTestMessage();
			try {
				testMessage = this.createTestMessage();
			} catch (IOException e1) {
				fail("MessageTest retry failed, please run this test individually!");
			}
		}
		
		MainProgram testpg = new MainProgram();
		testpg.readAndProcessMessage(new File[]{testMessage});
		//System.out.println(testpg.getMessageList().toString());
		String resultMessageList = testpg.getMessageList().toString();
		this.deleteTestMessage();
		assertEquals(expectedMessageList, resultMessageList);
	}
	
	File createTestMessage() throws IOException{
        String csvFile = "C:/Users/johnn/git/ProgrammingExercise/20170619_JPMC/InputFiles/test.csv";
        FileWriter writer = new FileWriter(csvFile);

        CSVUtils.writeLine(writer, Arrays.asList("1","APPLE","0.1" ,"1" ,""        ,""));
        CSVUtils.writeLine(writer, Arrays.asList("2","APPLE","0.2" ,"20",""        ,""));
        CSVUtils.writeLine(writer, Arrays.asList("3","APPLE","0.1" ,""  ,"ADD"     ,""));
        CSVUtils.writeLine(writer, Arrays.asList("3","APPLE","0.05",""  ,"SUBTRACT",""));
        CSVUtils.writeLine(writer, Arrays.asList("3","APPLE",""    ,""  ,"MULTIPLY","2"));
        
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
