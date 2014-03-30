import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 

import javax.imageio.ImageIO;

public class JPEGto2DArray {
	
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

	public static void main(String args[])
		throws IOException
		{ 
		File file = new File("redball.jpeg"); 
		BufferedImage image = ImageIO.read(file); 
		
		int image2DArray[][] = create2DArray(image); 
		
		int width = image.getWidth(); 
		int height = image.getHeight();
		
		for (int row = 0; row < height; row++)  { 
			for (int col = 0; col < width; col++)  { 
				System.out.print(image2DArray[row][col] + " "); 
			}
			
		}
		System.out.println("\n" + "\n");
		}
	
}
