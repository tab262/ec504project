package main;

import java.io.File;
import java.util.Arrays;

public class Main {

	public static void printUsageMessage(){
		System.out.println("Usage:");
		System.out.println("ptm -d directory");
		System.out.println("ptm -f file1,file2,...");
	}
	
	public static void directoryMode(String directory){
		System.out.println("Directory Mode:" + directory);
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		//http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
	    for (int i = 0; i < listOfFiles.length; i++) {
	        if (listOfFiles[i].isFile()) {
	          System.out.println("File: " + listOfFiles[i].getName());
	        }
	      }
	}
	
	public static void fileMode(String[] args){
		System.out.println("File Mode: ");
		int i;
		for(i = 1; i < args.length;i++){
			System.out.println(args[i]);
		}
	}
	
	public static void main(String[] args){
		
		if(args.length < 2){
			printUsageMessage();
		}else{			
			if(args[0].equals("-d")){
				System.out.println(args[0]);
				directoryMode(args[1]);
			}else if(args[0].equals("-f")){
				System.out.println( args[0]);
				fileMode(args);
			}
		}
		
		
		
		
	}
	
}
