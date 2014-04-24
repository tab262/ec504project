package main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import movie.Movie;

public class Main {

	public static void printUsageMessage(){
		System.out.println("Usage:");
		System.out.println("ptm -g (for GUI)");
		System.out.println("ptm -d directory");
		System.out.println("ptm -f file1,file2,...");
		System.exit(0);
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
		if(args.length < 2){
			printUsageMessage();
		}
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
		m.saveMovie("example","/Users/g/git/ec504project/data/");

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
		System.out.println("Launching GUI");
	}
	

	
	public static void main(String[] args) throws IOException{
		
		if(args.length < 1){
			printUsageMessage();
		}else{			
			if(args[0].equals("-d")){
				if(args.length > 1 && args.length <=2){
					System.out.println(args[0]);
					directoryMode(args[1]); //passes directory path
				}else{
					printUsageMessage();
				}
			}else if(args[0].equals("-f")){
				System.out.println(args[0]);
				fileMode(args); //passes set of images
			}else if(args[0].equals("-g")){
				System.out.println(args[0]);
				guiMode();
			}else{
				printUsageMessage();
			}
		}
		
		
		
		
	}
	
}
