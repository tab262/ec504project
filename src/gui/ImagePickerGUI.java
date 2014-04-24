package gui;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;


//ImagePicker GUI Class - Pick image files, and play back in video format.
public class ImagePickerGUI extends JPanel implements ActionListener {
	
	//Global Variables
    static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc;
    private ArrayList<String> list = new ArrayList<String>();

    public ImagePickerGUI() {
        super(new BorderLayout());

        //Create the log first - Action Listener refers to this.
        log = new JTextArea(800,800);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Add All Buttons and Action Listeners
        JButton sendButton = new JButton("Attach Images from Files or Directory");
        sendButton.addActionListener(this);
        
        JButton playButton = new JButton("Select a movie file to play (.ser)");
        playButton.addActionListener(new ActionListener() {
        @Override
		public void actionPerformed(ActionEvent e) {
        	System.out.println("Stuff is happening! MovButton Pressed.");
        	if (fc == null) {
                fc = new JFileChooser();
    	    //Implement the Custom Filter
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(new VideoFilter());
                fc.setMultiSelectionEnabled(false);
            //Show it.
            int returnVal = fc.showDialog(ImagePickerGUI.this,
                                          "Attach");
            //Process the results.
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File[] file = fc.getSelectedFiles();
                for (int i = 0; i < file.length; i++)
                {
                    log.append("Attaching file: " + file[i].getAbsolutePath()
                            + "." + newline);
                    list.add(file[i].getAbsolutePath());
                }
                log.append("Files so far: " + list.toString() + newline);
            } else {
                log.append("Attachment cancelled by user." + newline);
                log.append("Files so far: " + list.toString() + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
            
            //Reset the file chooser for the next time it's shown.
            fc.setSelectedFile(null);
		}
        }});
        
        JButton movButton = new JButton("Create Movie from Selected Images!");
        
        //FORMAT THE BUTTONS
        add(sendButton, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(playButton, BorderLayout.WEST);
        add(movButton, BorderLayout.SOUTH);
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// Set up file chooser if empty
        
		if (fc == null) {
            fc = new JFileChooser();
        
        
	    //Custom Filter (Images)
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(new ImageFilter());
            fc.setMultiSelectionEnabled(true);

	    //File Viewing - Custom View
            fc.setFileView(new ImageFileView());

	    //Preview Pane
            fc.setAccessory(new ImagePreview(fc));
        }
        
		int returnVal = fc.showDialog(ImagePickerGUI.this,
                                      "Attach");

        //Results are being processed here, and also logged.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] file = fc.getSelectedFiles();
            for (int i = 0; i < file.length; i++)
            {
                log.append("Attaching file: " + file[i].getAbsolutePath()
                        + "." + newline);
                list.add(file[i].getAbsolutePath());
            }
            

            log.append("Files so far: " + list.toString() + newline);
        } else {
            log.append("Attachment cancelled by user." + newline);
            log.append("Files so far: " + list.toString() + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());
        
        //File Chooser RESET
        fc.setSelectedFile(null);
		
	}

    private static void createAndShowGUI() {
        //Create and Initialize GUI in a JFrame
        JFrame frame = new JFrame("ImagePickerGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add the JPanel (our custom class)
        frame.add(new ImagePickerGUI());
        
        //DISPLAY SETTINGS
        frame.pack();
        
        frame.setSize(600, 1200);
        
        frame.setVisible(true);
    }
	
    public static void main(String[] args) {
        //Job Scheduling for Dispatch (Running GUI)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
