package jpeg;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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

	
	public static void writeToTextFile(int[][] image2DArray, String filename){
		try{  
	           FileWriter fr = new FileWriter((System.getProperty("user.dir") + "\\src\\jpeg\\") + filename + ".csv");  
	           BufferedWriter br = new BufferedWriter(fr);  
	           PrintWriter out = new PrintWriter(br);  
	           for(int i=0; i < image2DArray.length; i++){  
	               for(int j=0; j < image2DArray[1].length;j++){
	                     out.write(image2DArray[i][j] + ",");  
	               }
	               out.write("\n");
	           }
	           out.close();  
	             
	             
	       }  
	         
	       catch(IOException e){  
	        System.out.println(e);     
	       }  
	}
	
	
	public static void writeColorImageValueToFile(BufferedImage in) {
		int width = in.getWidth();
		int height = in.getHeight();

		System.out.println("width=" + width + " height=" + height);

		try {
			// FileOutputStream fstream1 = new FileOutputStream("r.txt");
			// FileOutputStream fstream2 = new FileOutputStream("g.txt");
			// FileOutputStream fstream3 = new FileOutputStream("b.txt");
			int[][] b = new int[height][width];
			int[][] c = new int[height][width];
			int[][] d = new int[height][width];
			

			int[] data = new int[width * height];
			in.getRGB(0, 0, width, height, data, 0, width);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					b[i][j] = ((data[i*height + j] >> 16) & 0xff);
					c[i][j] = ((data[i*height + j] >> 8) & 0xff);
					d[i][j] = (data[i*height + j] & 0xff);
				}
			}

			writeToTextFile(b,"red");
			writeToTextFile(c,"green");
			writeToTextFile(d,"blue");

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}
	 
	
	
	public static void main(String args[]) throws IOException{ 
		
		String fileName = System.getProperty("user.dir") + "\\src\\jpeg\\redball.jpeg";
		if(DEBUG) System.out.println(fileName);
		File file = new File(fileName); 
		BufferedImage image = ImageIO.read(file); 
		
		
		writeColorImageValueToFile(image);
		
		
		
		int image2DArray[][] = create2DArray(image); 
		writeToTextFile(image2DArray,"sample");
		int width = image.getWidth(); 
		int height = image.getHeight();
		if(DEBUG) System.out.println(width);
		if(DEBUG) System.out.println(height);
		
		
	}
	
}
