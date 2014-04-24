package main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import movie.Movie;

public class Main {

	public static void printUsageMessage(){
		System.out.println("Usage:");
		System.out.println("ptm -d directory");
		System.out.println("ptm -f file1,file2,...");
	}
	
	public static void directoryMode(String directory) throws IOException{

		System.out.println("Directory Mode:" + directory);
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		int numFiles = 0;
		for(int i = 0; i < listOfFiles.length;i++){
			if(listOfFiles[i].isFile())
				numFiles++;
		}

		Arrays.sort(listOfFiles);
		String fileNames[] = new String[numFiles];
		//http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
		int j = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
		    //System.out.println("File: " + listOfFiles[i].getName());
		     	fileNames[j++] = listOfFiles[i].getAbsolutePath();
		    }
		}
		
		for(int i = 0; i < fileNames.length;i++){
		   	System.out.println(fileNames[i]);
		}

		System.out.println("Making movie...");
		float quality = 0.6f;

		Movie m = new Movie(fileNames,quality);
		m.saveMovie("example","/Users/g/git/ec504project/data/movies/");
	}

		public static void fileMode(String[] args) throws IOException{
			System.out.println("File Mode: ");

			int i;
			String fileNames[] = new String[args.length - 1];
			for(i = 1; i < args.length;i++){
				fileNames[i-1] = args[i]; 
			}

		for(i = 0 ; i < fileNames.length;i++){
			System.out.println(fileNames[i]);
		}
		System.out.println("Making movie...");
		float quality = 0.6f;
		Movie m = new Movie(fileNames,quality);
		m.saveMovie("example","/Users/g/git/ec504project/data/movies/");

		}
	/*
	public static void directoryMode(String directory){
		System.out.println("Directory Mode:" + directory);
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		String fileNames[] = new String[listOfFiles.length];
		//http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
	    for (int i = 0; i < listOfFiles.length; i++) {
	        if (listOfFiles[i].isFile()) {
	          //System.out.println("File: " + listOfFiles[i].getName());
	          fileNames[i] = listOfFiles[i].getAbsolutePath();
	        }
	    }
	    
	    for(int i = 0; i < fileNames.length;i++){
	    	System.out.println(fileNames[i]);
	    }
	    
	}
	
		public static void fileMode(String[] args){
		System.out.println("File Mode: ");
		int i;
		String fileNames[] = new String[args.length - 1];
		for(i = 1; i < args.length;i++){
			fileNames[i-1] = args[i]; 
		}
		for(i = 0 ; i < fileNames.length;i++){
			System.out.println(fileNames[i]);
		}
	}
	*/
	public static void guiMode(){
		
	}
	

	
	public static void main(String[] args) throws IOException{
		
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
