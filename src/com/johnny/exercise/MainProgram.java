package com.johnny.exercise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.johnny.exercise.messages.Message;
import com.johnny.exercise.messages.MessageTypeOne;
import com.johnny.exercise.sales.Sale;
import com.sun.javafx.font.PGFont;

public class MainProgram {

	private boolean running;
	private ArrayList<Message> messageList = null;
	private static String INPUTFILEFOLDER = "C:/Users/johnn/git/ProgrammingExercise/InputFiles";

	public void readAndParse(){
		//System.out.println();
		File folder = new File(INPUTFILEFOLDER);

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".csv")) {
				try {
					String content = FileUtils.readFileToString(file);
					//System.out.println(content);
					List<String> messageValueList = Arrays.asList(content.split(","));

					int messageType = 0;

					if (messageValueList != null && messageValueList.get(0)!= null){
						messageType = Integer.parseInt(messageValueList.get(0));	
					}

					switch (messageType){
					case 1:
						Message tmpMsg = new MessageTypeOne();
						Sale tmpsl = new Sale(messageValueList.get(1),Float.parseFloat(messageValueList.get(2)));
						tmpMsg.setSaleDetails(tmpsl);
						this.messageList.add(tmpMsg);
						break;
					case 2:
						
						
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					this.cleanup(file);
				}
			}
		}
	}

	public void process(){
		System.out.println();

	}

	public void cleanup(File tmp){
		System.out.println();
	}

	public MainProgram(){
		this.running = true;
		this.messageList = new ArrayList<Message>();
	}

	public static void main(String[] args) {

		MainProgram pg = new MainProgram();

		pg.readAndParse();

	}

}
