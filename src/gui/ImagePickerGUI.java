package gui;

//The following imports include awt (Abstract Window Toolkit), swing (GUI widget toolkit), among others...
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import movie.Frame;
import movie.Movie;
import movie.Player;


//ImagePicker GUI Class - Pick image files, and play back in video format.
public class ImagePickerGUI extends JPanel implements ActionListener {
	
	//Global Variables
    static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc_mov; 	//file chooser for movie file
    private JFileChooser fc;		//file chooser for image files
    private ArrayList<String> list = new ArrayList<String>();
    private String selectedMovie = "";
    
    
    
    //Set up the action listener for selecting the movie
    public ActionListener movSelecting = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e) {
        	System.out.println("Stuff is happening! MovButton Pressed.");
        	if (fc_mov == null) {
        		fc_mov = new JFileChooser();
        	
    	    //Implement the Custom Filter
        	fc_mov.setAcceptAllFileFilterUsed(false);
        	fc_mov.setFileFilter(new VideoFilter());
        	fc_mov.setMultiSelectionEnabled(false);
        	
        	}
        	
            //Show it.
            int returnVal = fc_mov.showDialog(ImagePickerGUI.this,
                                          "Attach");
            //Process the results.
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc_mov.getSelectedFile();
                selectedMovie = file.getAbsolutePath();
                
                log.append("Selected Movie: " + selectedMovie + newline);
            } else {
                log.append("Selection cancelled by user." + newline);
                log.append("Selected Movie: " + selectedMovie + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
            
            //Reset the file chooser for the next time it's shown.
            fc_mov.setSelectedFile(null);
    	
    	}
    };
    
    public ActionListener movCreating = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e) {
    		//Input dialog with a text field
            String input =  JOptionPane.showInputDialog(this 
                    ,"Enter in some text:");
            final JOptionPane optionPane = new JOptionPane(
                    "The only way to close this dialog is by\n"
                    + "pressing one of the following buttons.\n"
                    + "Do you understand?",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);
            
    		String dirName = System.getProperty("user.dir") + "/data/";
    		
    		String[] myList = new String[list.size()];
    		myList = list.toArray(myList);
    		
    		Movie m = null;
			try {
				m = new Movie(myList,.2f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
    		m.saveMovie("Movie", dirName);
    		log.append("Movie created by user!" + newline);
    		System.out.println("Movie has been created by user.");
    	}
    };
    
    
    public ActionListener movPlaying = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e) {
    		
    		String[] myList = new String[list.size()];
    		myList = list.toArray(myList);
    		
    		Movie m = null;
			try {
				m = new Movie(myList,.2f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
    		log.append("A Movie is being played by user!" + newline);
    		System.out.println("A Movie has been played by user.");
    		
    		Player p = new Player();
    		
			System.out.println("Selected Movie: " + selectedMovie + newline);
			//String dirName = System.getProperty("user.dir") + "/data/" + "movies/"; 
			
			p.openMovie(selectedMovie);
//			System.out.println("This movie has " + p.m.frames.length + " frames");
			Frame[] f = p.m.frames;
			
			
			try {
				p.buildBufferedImages(p.m.frames);
				
				ImageIcon icon = null;
				JFrame frame = new JFrame();
				frame.setLayout(new FlowLayout());
				frame.setSize(600,600);
				JLabel lbl = new JLabel();
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				for(int i=0;i<100; i++){
					p.playMovie3(lbl,frame,i);
				}
				
//				p.start();
//				System.out.println("This movie is playing");
//				int i=0;
//				while(i<100){
//					p.playMovie();
//					i=i+1;
//					System.out.println("Robbie end loop");
//				}
				System.out.println("Robbie end while!?");
				
				System.out.println("This movie has played");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    };

    public ImagePickerGUI() {
    	
    	
        super(new BorderLayout());
        this.setSize(new Dimension(1024, 1768));
        //Create the log first - Action Listener refers to this.
        log = new JTextArea(800,800);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Add All Buttons and Action Listeners
        JButton sendButton = new JButton("Attach Images from Files or Directory (.jpg/.jpeg)");
        sendButton.addActionListener(this);
        
        JButton pickMovButton = new JButton("Select Movie (.ser)");
        pickMovButton.addActionListener(movSelecting);
        pickMovButton.setPreferredSize(new Dimension (150,100));
        
        JButton movButton = new JButton("Play Movie from Selected Images!");
        movButton.addActionListener(movPlaying);
        movButton.setPreferredSize(new Dimension (150,100));
        
        JButton createButton = new JButton("Create Movie");
        createButton.addActionListener(movCreating);
        createButton.setPreferredSize(new Dimension (150,100));
        
        //FORMAT THE BUTTONS
        add(sendButton, BorderLayout.NORTH);
        add(logScrollPane, BorderLayout.CENTER);
        add(createButton, BorderLayout.WEST);
        add(pickMovButton, BorderLayout.EAST);
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
