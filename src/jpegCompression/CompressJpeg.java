package jpegCompression;
import jpeg.JPEGto2DArray;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CompressJpeg {
	public static final boolean DEBUG = true;
	
	public static BufferedImage getImage(String fileName) throws IOException{
		File file = new File(fileName); 
		BufferedImage image = ImageIO.read(file);
		return image;
	}
	
	
	
	
	public static void processImage(String fileName) throws IOException{
		if(DEBUG) System.out.println("Processing " + fileName);
		BufferedImage in = getImage(fileName);
		int height = in.getHeight();
		int width = in.getWidth();
		if(DEBUG) System.out.println("Height: " + height + "\nWidth: " + width);
		int blockSize = 8;
		int vertBlocks = height / blockSize;
		int horzBlocks = width / blockSize;
		if(DEBUG) System.out.println("#Vertical Blocks: " + vertBlocks + "\n#Horizontal Blocks: " + horzBlocks);
		int[][][] rgbl = JPEGto2DArray.getRGBL(in);
		int[][] red = rgbl[0];
		int[][] green = rgbl[1];
		int[][] blue = rgbl[2];
		int[][] alpha = rgbl[3];
		JPEGto2DArray.writeToTextFile(red,"red");
		JPEGto2DArray.writeToTextFile(green,"green");
		JPEGto2DArray.writeToTextFile(blue,"blue");
		JPEGto2DArray.writeToTextFile(alpha,"alpha");
		
		
	}
	
	public static void main(String[] args) throws IOException{
		String fileName = System.getProperty("user.dir") + "/src/jpeg/redball.jpeg";
		processImage(fileName);
		
		
	}
	
}
