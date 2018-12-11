package gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import gui.LeftSide;
import files.HCalendarDictionnary;



public class RightSide{
	
		LeftSide left;
		static JSplitPane splitPane;
		JPanel Pane; 
		static JFrame window;
		JPanel globalpane;
		JPanel pane;
		
		JButton buttonmod;
		JButton buttonser;		
		JButton buttonhtml;
				
		JComboBox<Integer> box;
		
		JTextField name;
		JTextField number;
		JTextField mail;
		JTextField adressWork;
		JTextField numberWork;
		JTextField adressHome;
		
		public RightSide(){
		
		Pane = new JPanel();	
		left = new LeftSide();
		window = new JFrame();
		globalpane = new JPanel();
		pane = new JPanel();
		
		buttonmod = new JButton("Modifier");
		buttonser = new JButton("Serialiser");		
		buttonhtml = new JButton("HTML");
				
		box = new JComboBox<Integer>();
		
		name = new JTextField();
		number = new JTextField();
		mail = new JTextField();
		adressWork= new JTextField();
		numberWork = new JTextField();
		adressHome = new JTextField();
			
		name.setMaximumSize( new Dimension(250,20));
		number.setMaximumSize( new Dimension(250,20));
		mail.setMaximumSize( new Dimension(250,20));
		adressWork.setMaximumSize( new Dimension(250,20));
		numberWork.setMaximumSize( new Dimension(250,20));
		adressHome.setMaximumSize( new Dimension(250,20));
		
		
		Pane.add(buttonmod);
		Pane.add(buttonser);
		Pane.add(buttonhtml);
		
		BoxLayout Box1 = new BoxLayout(pane, 1);
		
		pane.setLayout(Box1);
		pane.add(box);
		pane.add(name);
		pane.add(number);
		pane.add(mail);
		pane.add(adressHome);
		pane.add(adressWork);
		pane.add(numberWork);
		
		
		BoxLayout Box2 = new BoxLayout(globalpane,1);
		
		globalpane.setLayout(Box2);
		globalpane.add(pane);
		globalpane.add(Pane);
		
		JSplitPane splitgauche = left.getSplitGauche();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitgauche, globalpane);
		splitPane.setDividerSize(0);
		}
		
		public static void main(String args[]) {
			
			RightSide right = new RightSide();
			window.setTitle("Ma fenetre");
			window.setLocationRelativeTo(null);
			window.add(splitPane);
			window.pack();
			window.setResizable(false);
			window.setVisible(true);
		
	}
}
