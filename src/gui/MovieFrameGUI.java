package gui;
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSlider;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieFrameGUI {
    
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
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
        
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25); 
        slider.setName("Video Quality");
        guiFrame.add(slider, BorderLayout.SOUTH);
        
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
    }
    
}