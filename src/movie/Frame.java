package movie;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Frame implements java.io.Serializable{

	ByteArrayOutputStream baos;
	byte[] bytearray;
	int height,width;
	
	/*
	public Frame(String fileName,float quality) throws IOException{
		//String dirName = System.getProperty("user.dir");
		//String fName = dirName + "/data/" + fileName;
		System.out.println(fileName);
		
		
		File imageFile = new File(fileName);
		File compressedImageFile = new File(fileName+"_out.jpg");
		
		InputStream is = new FileInputStream(imageFile);
		OutputStream os = new FileOutputStream(compressedImageFile);
		
		// create a BufferedImage as the result of decoding the supplied InputStream
		BufferedImage image = ImageIO.read(is);
		
		// get all image writers for JPG format
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

		if (!writers.hasNext())
			throw new IllegalStateException("No writers found");

		
		ImageWriter writer = (ImageWriter) writers.next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		writer.setOutput(ios);
				

		ImageWriteParam param = writer.getDefaultWriteParam();

		// compress to a given quality
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);
		
		// appends a complete image stream containing a single image and
		//associated stream and image metadata and thumbnails to the output
		writer.write(null, new IIOImage(image, null, null), param);

		// close all streams
		is.close();
		os.close();
		ios.close();
		writer.dispose();
		
	}
	*/
	
	
	public Frame(String fileName,float quality) throws IOException{
		System.out.println(fileName);
		ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
		BufferedImage img=ImageIO.read(new File(fileName));		
		this.height = img.getHeight();
		this.width = img.getWidth();
		ImageIO.write(img, "jpg", baos);
		this.bytearray = baos.toByteArray();
	}
	
	
	public static void main(String[] args) throws IOException{

		String files[] = new String[6];
		files[0] = "one";
		files[1] = "two";
		files[2] = "three";
		files[3] = "four";
		files[4] = "five";
		files[5] = "six";
		
		Frame movie[] = new Frame[6];
		
		for(int i = 0; i < 6; i++){
			movie[i] = new Frame(files[i],.4f);
		}
		
	}
	
	
}
