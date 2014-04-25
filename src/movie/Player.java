package movie;

import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JPanel;

public class Player {

	float timePerFrame;
	public Movie m;
	BufferedImage images[];
	ImageIcon icons[];
	Image set[];
	
	public void openMovie(String fileName){
		//this.m = null;

	      try
	      {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.m = (Movie) in.readObject();
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
	
	public void paint(Graphics g){
		g.drawImage(images[0], 0, 0, 0, 0, 0, 0, 0, 0, null, null);
	}
	
	
	public void buildBufferedImages(Frame[] f) throws IOException{
		int height = m.frames[0].height;
		int width = m.frames[0].width;
		System.out.println("h:" + height + " w:" + width);
		while(height > 550 || width > 550){
			System.out.println("Hi while loop");
			height = (int)(height * .99);
			width = (int)(width * .99);
		}
		System.out.println("h:" + height + " w:" + width);
		System.out.println("Hi outside of while loop");
		icons = new ImageIcon[f.length];
		for(int i = 0; i < f.length;i++){
			icons[i] = new ImageIcon(ImageIO.read(new ByteArrayInputStream(f[i].bytearray)));
			icons[i] = new ImageIcon(icons[i].getImage().getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH));
		}
		//icons[i] = new ImageIcon(images[i]);
		//set[i] = icons[i].getImage().getScaledInstance(500, 500,java.awt.Image.SCALE_SMOOTH);
		//icons[i] = new ImageIcon(set[i]);
		
	}
	
	public void playMovie() throws InterruptedException{


		ImageIcon icon;
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(600,600);
		JLabel lbl = new JLabel();
		lbl.setIcon(this.icons[0]);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int i = 3;
		//ImageIcon next = this.icons[1];
		while(i < 1000){
			icon = (this.icons[i%m.frames.length]); 
			Thread.sleep(30);
//			frame.remove(lbl);
			lbl.setIcon(icon);
			frame.add(lbl);
			
			icon = (this.icons[4%m.frames.length]); 
			Thread.sleep(30);
//			frame.remove(lbl);
			lbl.setIcon(icon);
			frame.add(lbl);
			
			frame.validate();
			frame.repaint();
			i++;
		}

	}
	
//	public static int k=0;
//	public static JFrame frame = new JFrame();
//	public static JLabel lbl = new JLabel();
//

	public void playMovie3() throws InterruptedException{
		 int k=0;
		 JFrame frame = new JFrame();
		 JLabel lbl = new JLabel();
		
		
		frame.setLayout(new FlowLayout());
//		frame.setLayout(manager);
		frame.setSize(600,600);
//		frame.removeAll();
		ImageIcon icon;
//		JFrame frame = new JFrame();
		
		icon = (this.icons[k%m.frames.length]); 
		lbl.setIcon(icon);

//		lbl.setIcon(this.icons[k%m.frames.length]);
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		int i = 0;
		//ImageIcon next = this.icons[1];
//		while(i < 1000){
//			System.out.println("Robbie WHO!?");
//			icon = (this.icons[k%m.frames.length]); 
			//icon = (this.icons[i%m.frames.length]); 
			Thread.sleep(30);
//			System.out.println("Robbie hu!?");
//			lbl.setIcon(icon);
//			frame.add(lbl);
//			i++;
			k++;
			System.out.println("Robbie WHO!?");
//		}
		
	}
	
	public void playMovie2(){
		JFrame frame = new JFrame("Player 2");
		frame.setSize(600,600);
		frame.setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img1 = images[0];
		panel.getGraphics().drawImage(img1, 0, 0, panel);
	}
	
	public void playMovie3( JLabel lbl,JFrame frame,int i) throws InterruptedException{
//		icon = (); 
		
		if(i==0){
			lbl.setIcon(icons[0]);
			frame.add(lbl);
			frame.setVisible(true);
		}else{
		Thread.sleep(30);
//		frame.remove(lbl);
		lbl.setIcon(this.icons[i%m.frames.length]);
		frame.add(lbl);
		frame.validate();
		frame.repaint();
		}
	}
	public void start() throws InterruptedException{
		
		ImageIcon icon = null;
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(600,600);
		JLabel lbl = new JLabel();
		lbl.setIcon(this.icons[0]);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i=0;i<100; i++){
			this.playMovie3(lbl,frame,i);
		}

		
		System.out.println("start!?");
		this.playMovie();
		System.out.println("end!?");
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{
		Player p = new Player();
		String dirName = System.getProperty("user.dir") + "/data/" + "movies/"; 
		p.openMovie(dirName + "Movie.ser");
		System.out.println("This movie has " + p.m.frames.length + " frames");
		System.out.println("Hi frame array");
		Frame[] f = p.m.frames;
		System.out.println("Hi filled frame array");
		p.buildBufferedImages(p.m.frames);
		p.playMovie();
		// p.playMovie2();
	}
	
}
