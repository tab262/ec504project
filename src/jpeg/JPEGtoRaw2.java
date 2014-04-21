//http://stackoverflow.com/questions/10923842/can-i-change-the-compression-algorithm-used-by-javas-imagewriter-when-creating
/*taken from: http://www.programcreek.com/2009/02/java-convert-image-to-byte-array-convert-byte-array-to-image/ for learning purposes*/

package jpeg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
 
 
public class JPEGtoRaw2 {
	public static void main(String[] args) throws IOException{
		String dirName = System.getProperty("user.dir");
		
		String fileName = dirName + "/data/test.jpeg";
		System.out.println(fileName);

		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(.75f);
		

		ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
		
		BufferedImage img=ImageIO.read(new File(fileName));		
		
		
		System.out.println("IMG: " + img.getHeight() + "x" + img.getWidth());
		
		ImageIO.write(img, "jpg", baos);
		
		
		System.out.println("Number of Bytes In image: " + baos.size());
		baos.flush();
 
		String base64String=Base64.encode(baos.toByteArray());
		System.out.println("String: " + base64String.length());
		baos.close();
 
		byte[] bytearray = Base64.decode(base64String);
		System.out.println("BYTEARRAY: " + bytearray.length);
		BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
		System.out.println("IMAG: " + imag.getHeight() + "x" + imag.getWidth());
		ImageIO.write(imag, "jpg", new File(dirName+"/data/","test2.jpeg"));
	}
	
	public static void testCompression(String fileName){
		
	}
}