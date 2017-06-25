package com.johnny.exercise.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.johnny.exercise.MainProgram;
import com.johnny.exercise.util.CSVUtils;

public class AdjustmentReportTest {
	
	public static final String expectedAdjustmentReportString = "###Start of Adjustment Report###\r\nProduct : APPLE\r\nAdjustment 0:  Operation: ADD Value: 0.10\r\nAdjustment 1:  Operation: SUBTRACT Value: 0.05\r\nAdjustment 2:  Operation: MULTIPLY Value: 2.00\r\n###End of Adjustment Report###\r\n";
	
	@Test
	public void testAdjustmentReport() {
		AdjustmentReportTest art = new AdjustmentReportTest();
		File testMessage = null;
		
		try {
			testMessage = art.createAdjustmentReportTestMessages();
		} catch (IOException e) {
			this.deleteTestMessage();
			try {
				testMessage = art.createAdjustmentReportTestMessages();
			} catch (IOException e1) {
				fail("AdjustmentReportTest retry failed, please run this test individually!");
			}
		}
		
		MainProgram testpg = new MainProgram();
		testpg.setADJUSTMENTREPORTTRIGGER(99999);
		testpg.setSALEREPORTTRIGGER(99999);
		testpg.readAndProcessMessage(new File[]{testMessage});
		String testResult = testpg.processAdjustmentReport();
		//System.out.println(testResult);
		this.deleteTestMessage();
		assertEquals(expectedAdjustmentReportString, testResult);
	}

	File createAdjustmentReportTestMessages() throws IOException{
		String csvFile = "C:/Users/johnn/git/ProgrammingExercise/20170619_JPMC/test.csv";
		FileWriter writer = new FileWriter(csvFile);

		CSVUtils.writeLine(writer, Arrays.asList("1","APPLE","0.1" ,"1" ,""        ,""));
		CSVUtils.writeLine(writer, Arrays.asList("2","APPLE","0.2" ,"20",""        ,""));
		CSVUtils.writeLine(writer, Arrays.asList("2","PIE"  ,"0.2" ,"20",""        ,""));
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
