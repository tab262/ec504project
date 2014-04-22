package movie;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Player {

	float timePerFrame;
	Movie m;
	BufferedImage images[];

	public void openMovie(String fileName){
		this.m = null;

	      try
	      {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         m = (Movie) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Movie class not found");
	         c.printStackTrace();
	         return;
	      }
	}
	
	
	public static void main(String[] args){
		Player p = new Player();
		p.openMovie("/home/gaddis/git/ec504project/data/movies/movie1.ser");
		System.out.println("This movie has " + p.m.frames.length + " frames");
	}
	
}
