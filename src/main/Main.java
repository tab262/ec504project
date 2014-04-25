package main;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
			if(listOfFiles[i].isFile() && (listOfFiles[i].getAbsolutePath().endsWith(".jpg") || listOfFiles[i].getAbsolutePath().endsWith(".jpeg"))){
				numFiles++;
			}
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
		String dirName = System.getProperty("user.dir") + "/data/";
		m.saveMovie("example",dirName);
	}

	public static void fileMode(String[] args) throws IOException{
		String newFileName = null;
		if(args.length < 2){
			printUsageMessage();
		}
		System.out.println("Files...");
		int i;
		ArrayList<String> fileNames = new ArrayList<String>();
		for(i = 1; i < args.length;i++){
			if(args[i].endsWith("jpeg") || args[i].endsWith("jpg")){
				fileNames.add(args[i]); 
			}else if(args[i].equals("-o")){
				if((i+1) < args.length){
					newFileName = args[i+1];
					i = args.length;
				}
			}else{
				throw new IllegalArgumentException("'"+args[i] + "' is not jpg/jpeg. Files must be jpg/jpeg:");
			}
		}
		
		String[] set = fileNames.toArray(new String[0]); 
		for(i = 0 ; i < set.length;i++){
			System.out.println(set[i]);
		}
		System.out.println();
		
		if(newFileName == null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			newFileName = dateFormat.format(date);
			newFileName = newFileName.replace(' ', '_') + "_movie";
			System.out.println(newFileName);
		}
		
		System.out.println("Making movie with filename '" + newFileName + "'...");
		float quality = 0.6f;
		
		
		//Movie m = new Movie(fileNames,quality);
		//String dirName = System.getProperty("user.dir") + "/data/";
		//m.saveMovie("example",dirName);

	}

	public static void guiMode(){
		System.out.println("Launching GUI");
		gui.ImagePickerGUI.main(null);
	}
	

	
	public static void main(String[] args) throws IOException{
		
		if(args.length < 1){
			printUsageMessage();
		}else{			
			if(args[0].equals("-d")){
				if(args.length > 1 && args.length <=2){
					directoryMode(args[1]); //passes directory path
				}else{
					printUsageMessage();
				}
			}else if(args[0].equals("-f")){
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
