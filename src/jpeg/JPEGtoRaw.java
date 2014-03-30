package jpeg;

/*taken from: http://www.programcreek.com/2009/02/java-convert-image-to-byte-array-convert-byte-array-to-image/ for learning purposes*/

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class JPEGtoRaw {

	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String dir = System.getProperty("user.dir");
		String fileName = dir + "/data/img2.jpg";
		
		File file = new File(fileName);
		
		/*FIS obtains input bytes from a file
		 * It can be used to read a file byte by byte
		 */
		FileInputStream fis = new FileInputStream(file);
		
		/*
		 * BAOS is an output stream in which the data is written into a byte array
		 * The buffer grows as data is written into it
		 */
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		
		try{
			//We read byte byte until read returns -1, meaning it has reached the end of the
			//the file
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum); 
                //System.out.println("read " + readNum + " bytes,");
            }
		}catch (IOException ex) {
           System.out.println("oops");
        }
		
		//convert the BAOS into an array of bytes
		byte[] bytes = bos.toByteArray();
		for(int i = 0; i < 100;i++){
			System.out.print(bytes[i] + " ");
			if(i % 9 == 0 && i != 0)
				System.out.println();
		}
		System.out.println("Length: " + bytes.length);
		
		//We pass the array to the BAIS, which is an internal buffer that contains
		//bytes that may be read from the stream.
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        
		//This iterator allows us to 'decode' the bytes for the jpg format
		//Returns an Iterator containing all currently registered ImageReaders 
		//that claim to be able to decode the named format.
		Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
 
        //ImageIO is a class containing static methods for locating ImageReaders
        //and ImageWriters, and performing simple encoding and decoding. 
 
		//We grab the first Image reader from raders
        ImageReader reader = (ImageReader) readers.next();
        
        //bis Is the internal buffer to hold the read bytes
        Object source = bis; 
        
        //
        ImageInputStream iis = ImageIO.createImageInputStream(source); 
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
 
        Image image = reader.read(0, param);
        System.out.println(image.getHeight(null) + " " + image.getWidth(null));
        //got an image file
 
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        //bufferedImage is the RenderedImage to be written
 
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
 
        File imageFile = new File(dir + "/data/img2-2.jpg");
        ImageIO.write(bufferedImage, "jpg", imageFile);
 
        System.out.println(imageFile.getPath());
	}

}