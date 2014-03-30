import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImagePlayGround {
	
	public static void main(String[] args) throws IOException {
		BufferedImage imgBuffer = ImageIO.read(new File("/home/gaddis/School/ec504/ec504project/img2.jpg"));
		byte[] pixels = (byte[])imgBuffer.getRaster().getDataElements(0, 0, imgBuffer.getWidth(), imgBuffer.getHeight(), null);
		
		System.out.println(pixels.length);
		int i = 0;
		int val = 0;
		
		
		for(i = 0; i < 100000; i++){
			System.out.print(pixels[i] + " ");
			if(i % 10 == 0)
				System.out.println();
		}
				
	}

}
