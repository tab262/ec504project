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

	public Frame frames[];
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
	         new FileOutputStream(dirName + "/movies/" + fileName+ ".ser");
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
		String dirName = System.getProperty("user.dir") + "/data/";//sequence/";
		System.out.println(dirName);
		String files[] = new String[6];
		files[0] = dirName + "one.jpg";
		files[1] = dirName + "two.jpg";
		files[2] = dirName + "three.jpg";
		files[3] = dirName + "four.jpg";
		files[4] = dirName + "five.jpg";
		files[5] = dirName + "six.jpg";
		//files[6] = dirName + "7.jpg";
		//files[7] = dirName + "8.jpg";
		Movie m = new Movie(files,.2f);
		m.saveMovie("movie5", dirName);
		
		
		
	}
	
}
