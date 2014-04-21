package jpeg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
 
 
public class JPEGtoRaw2 {
	public static void main(String[] args) throws IOException{
		String dirName = System.getProperty("user.dir");
		
		String fileName = dirName + "/data/img2.jpg";
		System.out.println(fileName);
		

		ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
		BufferedImage img=ImageIO.read(new File(fileName));
		ImageIO.write(img, "jpg", baos);
		baos.flush();
 
		String base64String=Base64.encode(baos.toByteArray());
		baos.close();
 
		byte[] bytearray = Base64.decode(base64String);
 
		BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
		ImageIO.write(imag, "jpg", new File(dirName+"/data/","snap.jpg"));
	}
}