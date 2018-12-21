package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import files.HCard;
import gui.LeftSide;
import gui.RightSide;

public class Gui {
	
	static JFrame window;
	static LeftSide left;
	static RightSide right;
	
	public Gui() {
		
		
		right = new RightSide();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			right = new RightSide();
			left = new LeftSide();
			window = new JFrame();
			window.setTitle("Ma fenetre");
			window.setLocationRelativeTo(null);
			window.add(right.getSplitPane());
			window.pack();
			window.setResizable(true);
			window.setVisible(true);
						
			/*
			 ActionListener actionListener = new ActionListener() {
			      public void actionPerformed(ActionEvent actionEvent) {
			        JFileChooser theFileChooser = (JFileChooser) actionEvent.getSource();
			        String command = actionEvent.getActionCommand();
			        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
			          File selectedFile = theFileChooser.getSelectedFile();
			          System.out.println(selectedFile.getParent());
			          
			          System.out.println(selectedFile.getName());
			          
			          HCard card = new HCard(selectedFile);
			          left.getTextarea().append(card.toString());
			          System.out.println(card.toString());
			          
			        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
			          System.out.println(JFileChooser.CANCEL_SELECTION);
			        }
			      }
			    };
			    left.getFileChooser().addActionListener(actionListener);
			    
			    //left.getTextarea().append(Card.toString()));
			*/    
		}
		
		/*public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}*/

		

}


