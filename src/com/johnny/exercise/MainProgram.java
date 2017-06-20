package com.johnny.exercise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import com.johnny.exercise.sales.Sale;

public class MainProgram {
	
	private boolean running;
	private ArrayList<Sale> SaleOrders;
	private static String INPUTFILEFOLDER = "C:/Users/johnn/git/ProgrammingExercise/InputFiles";
	
	public void read(){
		//System.out.println();
		File folder = new File(INPUTFILEFOLDER);
		
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  File file = listOfFiles[i];
		  if (file.isFile() && file.getName().endsWith(".txt")) {
		    try {
				String content = FileUtils.readFileToString(file);
			    /* do somthing with content */
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		}
	}
	
	public void process(){
		System.out.println();
		
	}
	
	public void cleanup(){
		System.out.println();
	}

	public MainProgram(){
		this.running = true;
		this.SaleOrders = new ArrayList<Sale>();
	}
	
	public static void main(String[] args) {
		
		MainProgram pg = new MainProgram();
		
		while(pg.running){
			
		}

	}

}
