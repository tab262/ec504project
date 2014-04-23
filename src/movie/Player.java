package movie;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Player {

	float timePerFrame;
	Movie m;
	BufferedImage images[];
	ImageIcon icons[];
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
	
	
	public void buildBufferedImages(Frame[] f) throws IOException{
		images = new BufferedImage[f.length];
		icons = new ImageIcon[f.length];
		for(int i = 0; i < f.length;i++){
			images[i] = ImageIO.read(new ByteArrayInputStream(f[i].bytearray));
			icons[i] = new ImageIcon(images[i]);
		}
	}
	
	public void playMovie() throws InterruptedException{
		int height = m.frames[0].height;
		int width = m.frames[0].width;
		
		ImageIcon icon;
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(400,400);
		JLabel lbl = new JLabel();
		lbl.setIcon(this.icons[0]);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int i = 0;
		while(i < 100){
			icon = (this.icons[i%m.frames.length]);
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH);  
			icon = new ImageIcon(newimg); 
			Thread.sleep(100);
			lbl.setIcon(icon);
			frame.add(lbl);
			i++;
		}
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{
		Player p = new Player();
		p.openMovie("/home/gaddis/git/ec504project/data/sequence/movies/movie1.ser");
		System.out.println("This movie has " + p.m.frames.length + " frames");
		Frame[] f = p.m.frames;
		p.buildBufferedImages(p.m.frames);
		p.playMovie();
	}
	
}
