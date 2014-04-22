package movie;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

// methods for adding frames to a movie and saving 
public class Movie  implements java.io.Serializable{

	Frame frames[];
	float quality;
	
	public Movie(String[] files, float quality) throws IOException{
		this.quality = quality;
		frames = new Frame[files.length];
		for(int i = 0 ; i < files.length;i++){
			frames[i] = new Frame(files[i],quality);
		}
	}
	
	public static void main(String[] args) throws IOException{
		String dirName = System.getProperty("user.dir") + "/data/";
		System.out.println(dirName);
		String files[] = new String[5];
		files[0] = dirName + "one.jpg";
		files[1] = dirName + "two.jpg";
		files[2] = dirName + "three.jpg";
		files[3] = dirName + "four.jpg";
		files[4] = dirName + "five.jpg";
		Movie m = new Movie(files,.5f);
		
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(dirName + "/movies/movie1.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(m);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + dirName + "movies/");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
}
