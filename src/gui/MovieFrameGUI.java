package gui;
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieFrameGUI {
	
	public float quality = .5f;

	public static void main(String[] args) {
        
        new MovieFrameGUI();
    }

    public MovieFrameGUI()
    {
        JFrame guiFrame = new JFrame();
        guiFrame.add(new ImagePickerGUI(), BorderLayout.CENTER);
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Video Encoder GUI");
        guiFrame.setSize(1024,768);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        JPanel quality = new JPanel();
        JLabel qualityLbl = new JLabel("Quality (0% - 100%)");
        quality.add(qualityLbl);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25); 
        slider.setName("Video Quality");
        quality.add(slider);
        guiFrame.add(quality, BorderLayout.SOUTH);
        
        JPanel progress = new JPanel();
        JLabel progLbl = new JLabel("Progress");
        progress.add(progLbl);
        JProgressBar progressBar = new JProgressBar();
        progress.add(progressBar);
        guiFrame.add(progress, BorderLayout.EAST);
        
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
    }
    
}