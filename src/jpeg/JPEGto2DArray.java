package jpeg;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class JPEGto2DArray {
	public static final boolean DEBUG = true;
	
	public static final int[][] create2DArray (BufferedImage image){ 
		int width = image.getWidth(); 
		int height = image.getHeight(); 
		int [][] product = new int[height][width];
		
		for (int row = 0; row < height; row++)  { 
			for (int col = 0; col < width; col++)  { 
				product [row][col] = image.getRGB(col, row); 
			}
		}
		
		return product; 
	}

	
	public static void writeToTextFile(int[][] image2DArray){
		try{  
	           FileWriter fr = new FileWriter((System.getProperty("user.dir") + "\\src\\jpeg\\") + "2Doutput.txt");  
	           BufferedWriter br = new BufferedWriter(fr);  
	           PrintWriter out = new PrintWriter(br);  
	           for(int i=0; i < image2DArray.length; i++){  
	               for(int j=0; j < image2DArray[1].length;j++){
	                     out.write(image2DArray[i][j] + " ");  
	               }
	               out.write("\n");
	           }
	           out.close();  
	             
	             
	       }  
	         
	       catch(IOException e){  
	        System.out.println(e);     
	       }  
	}
	
	public static void main(String args[]) throws IOException{ 
		
		String fileName = System.getProperty("user.dir") + "\\src\\jpeg\\redball.jpeg";
		if(DEBUG) System.out.println(fileName);
		File file = new File(fileName); 
		BufferedImage image = ImageIO.read(file); 
		
		int image2DArray[][] = create2DArray(image); 
		
		int width = image.getWidth(); 
		int height = image.getHeight();
		if(DEBUG) System.out.println(width);
		if(DEBUG) System.out.println(height);
		
		writeToTextFile(image2DArray);
		
		//for (int row = 0; row < height; row++)  { 
		//	for (int col = 0; col < width; col++)  { 
				//System.out.print(image2DArray[row][col] + " "); 
			//}
			
		//}
		//System.out.println("\n" + "\n");
		}
	
}
