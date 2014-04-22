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
	
	
	public void saveMovie(String fileName,String dirName){
		//http://www.tutorialspoint.com/java/java_serialization.htm
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(dirName + "/movies/movie1.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + dirName + "movies/");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static void main(String[] args) throws IOException{
		String dirName = System.getProperty("user.dir") + "/data/sequence/";
		System.out.println(dirName);
		String files[] = new String[8];
		files[0] = dirName + "1.jpg";
		files[1] = dirName + "2.jpg";
		files[2] = dirName + "3.jpg";
		files[3] = dirName + "4.jpg";
		files[4] = dirName + "5.jpg";
		files[5] = dirName + "6.jpg";
		files[6] = dirName + "7.jpg";
		files[7] = dirName + "8.jpg";
		Movie m = new Movie(files,.5f);
		m.saveMovie("movie2", dirName);
		
		
		
	}
	
}
