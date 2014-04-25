package main;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import movie.Movie;
import movie.Player;

public class Main {

	public static void printUsageMessage(){
		System.out.println("Usage:");
		System.out.println("GUI: ptm -g (for GUI)");
		System.out.println("Encode directory set: ptm -d [source directory] -o [output file name]");
		System.out.println("Encode list of images: ptm -f [file1,file2,...,filen] -o [output file name]");
		System.out.println("View: ptm -p [movie file]");
		System.exit(0);
	}
	
	public static void directoryMode(String[] args) throws IOException{
		String newFileName = null;
		System.out.println(args.length);
		
		// Checking arguments: directory mode should have 4
		// 1. -d
		// 2. directory
		// 3. -o
		// 4. output file name
		// If the user fails to specify a name, it will be assigned the time and date as the title
		if(args.length == 4 && args[2].equals("-o")){
			newFileName = args[3];
		}else if(args.length == 2){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			newFileName = dateFormat.format(date);
			newFileName = newFileName.replace(' ', '_') + "_movie";
			System.out.println(newFileName);
		}else{
			printUsageMessage();
		}
		
		// Get directory and check if valid
		String directory = args[1];	
		System.out.println("Directory Mode:" + directory);
		File folder = new File(directory);
		
		if(!folder.isDirectory()){
			System.out.println(directory + " is not a valid directory");
			System.exit(-1);
		}
		
		
		System.out.println("Files....");
		File[] listOfFiles = folder.listFiles();
		int numFiles = 0;
		for(int i = 0; i < listOfFiles.length;i++){
			if(listOfFiles[i].isFile() && (listOfFiles[i].getAbsolutePath().endsWith(".jpg") || listOfFiles[i].getAbsolutePath().endsWith(".jpeg"))){
				numFiles++;
			}
		}
		// check to make sure there are jpegs in the directory
		if(numFiles == 0){
			System.out.println("No jpegs found in this directory...");
			System.exit(-1);
		}
		
		// Assuming movie should play images in alpha order relative of their title
		Arrays.sort(listOfFiles);
		
		// Extract the file names
		String fileNames[] = new String[numFiles];
		//http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
		int j = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
		     	fileNames[j++] = listOfFiles[i].getAbsolutePath();
		    }
		}

		// Print list of files
		for(int i = 0; i < fileNames.length;i++){
		   	System.out.println(fileNames[i]);
		}

		System.out.println("Making movie...");
		float quality = 0.6f;
		makeMovie(fileNames,quality,newFileName);
	}

	public static void fileMode(String[] args) throws IOException{
		String newFileName = null;
		if(args.length < 2){
			System.out.println("Two few arguments...");
			printUsageMessage();
		}
		System.out.println("Files...");
		int i;
		ArrayList<String> fileNames = new ArrayList<String>();
		for(i = 1; i < args.length;i++){
			if(args[i].endsWith(".jpeg") || args[i].endsWith(".jpg")){
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
		
		makeMovie(set,quality,newFileName);
		

	}

	public static void guiMode(){
		System.out.println("Launching GUI");
		gui.ImagePickerGUI.main(null);
	}
	
	public static void playMode(String[] args) throws IOException, InterruptedException{
		if(args[1].endsWith(".ser")){
			Player p = new Player();
			p.openMovie(args[1]);
			p.buildBufferedImages(p.m.frames);
			p.playMovie();
		}else{
			System.out.println("Invalid file name (make sure to include the extension");
			System.exit(-1);
		}
	}
	
	public static void makeMovie(String[] fileNames, float quality, String videoFileName) throws IOException{
		Movie m = new Movie(fileNames,quality);
		String dirName = System.getProperty("user.dir");
		m.saveMovie(videoFileName,dirName);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
		if(args.length < 2){
			printUsageMessage();
		}else{			
			if(args[0].equals("-d")){
				if(args.length > 1 && args.length <=4){
					directoryMode(args); //passes directory path
				}else{
					printUsageMessage();
				}
			}else if(args[0].equals("-f")){
				fileMode(args); //passes set of images
			}else if(args[0].equals("-g")){
				guiMode();
			}else if(args[0].equals("-p")){
				playMode(args);
			}else{
				System.out.println("4");
				printUsageMessage();
			}
		}
		
		
		
		
	}
	
}
