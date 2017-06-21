package com.johnny.exercise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeOne;
import com.johnny.exercise.messages.MessageTypeThree;
import com.johnny.exercise.messages.MessageTypeTwo;
import com.johnny.exercise.sales.Sale;

public class MainProgram {

	private boolean running = true;
	private ArrayList<Message> messageList = null;
	private static String INPUTFILEFOLDER = "C:/Users/johnn/git/ProgrammingExercise/InputFiles";
	private static String OUTPUTFILEFOLDER = "C:/Users/johnn/git/ProgrammingExercise/OutputFiles";
	private static int messageCount = 0;
	private static final int SALEREPORTTRIGGER = 10;
	private static final int ADJUSTMENTREPORTTRIGGER = 50;
	private static final boolean isDebugModeOn = true;

	public MainProgram(){
		this.messageList = new ArrayList<Message>();
	}

	public static void main(String[] args) {

		MainProgram pg = new MainProgram();

		//process files
		File folder = new File(INPUTFILEFOLDER);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".csv")) {
				List<String> lines = null;
				try {
					lines = FileUtils.readLines(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				for (String line : lines) {
					List<String> messageValueList = Arrays.asList(line.split(","));

					int messageType = 0;

					if (messageValueList != null && messageValueList.get(0)!= null){
						messageType = Integer.parseInt(messageValueList.get(0));	
					}

					switch (messageType){
					case 1:
						MessageTypeOne tmpMsg1 = new MessageTypeOne();
						Sale tmpsl1 = new Sale(messageValueList.get(1),Float.parseFloat(messageValueList.get(2)),1);
						tmpMsg1.setSaleDetails(tmpsl1);
						pg.messageList.add(tmpMsg1);
						break;
					case 2:
						MessageTypeTwo tmpMsg2 = new MessageTypeTwo();
						Sale tmpsl2 = new Sale(messageValueList.get(1),Float.parseFloat(messageValueList.get(2)),Integer.parseInt(messageValueList.get(3)));
						tmpMsg2.setSaleDetails(tmpsl2);
						pg.messageList.add(tmpMsg2);
						break;
					case 3:
						MessageTypeThree tmpMsg3 = new MessageTypeThree();

						Sale tmpsl3 = null;
						if (messageValueList.get(2) != null && messageValueList.get(2).length() != 0){
							//for add and subtract
							tmpsl3 = new Sale(messageValueList.get(1),Float.parseFloat(messageValueList.get(2)),-1);
						}
						else {
							//for multiply
							tmpsl3 = new Sale(messageValueList.get(1),-1,-1);
							tmpMsg3.setMultiplier(Float.parseFloat(messageValueList.get(5)));
						}
						tmpMsg3.setSaleDetails(tmpsl3);
						tmpMsg3.setOperation(messageValueList.get(4));

						pg.messageList.add(tmpMsg3);
						break;
					}
					messageCount += 1;

					if (messageCount % SALEREPORTTRIGGER == 0){

					}

					if (messageCount % ADJUSTMENTREPORTTRIGGER == 0){

					}
				}
			}
			//add move file here
			if (isDebugModeOn != true){
				File destinationFile = new File(OUTPUTFILEFOLDER + "/" + file.getName());
				try {
					FileUtils.moveFile(file, destinationFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		//System.out.println(pg.messageList);
		//pg.readAndParse();
		//pg.process();
	}

}
