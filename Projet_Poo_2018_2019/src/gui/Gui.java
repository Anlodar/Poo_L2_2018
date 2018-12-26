package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.HCalendar;
import files.HCalendarDictionnary;
import files.HCard;

public class Gui implements ActionListener{

	File file;
	static JFrame window;
	JButton openbutton;
	JTextArea textarea;
	JFileChooser fc;
	JScrollPane scroll;
	static JSplitPane splitgauche;
	FileFilter filterVCF;
	FileFilter filterICS;
	JPanel buttonpane;
	HCard card;
	HCalendar calendar;
	static JSplitPane splitPane;
	JPanel Pane;
	String type;
	
	JPanel globalpane;
	JPanel JTextpane;
	JButton buttonmod;
	JButton buttonser;		
	JButton buttonhtml;
			
	JComboBox<String> box;
	String[] event;
	String oldname;
	
	JTextField name;
	JTextField number;
	JTextField mail;
	JTextField adressWork;
	JTextField numberWork;
	JTextField adressHome;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Gui() {
		
		globalpane = new JPanel();
		JTextpane = new JPanel();
		buttonpane = new JPanel();
		
		textarea = new JTextArea(20,70);
		textarea.setEditable(false);
		
		fc = new JFileChooser();
		filterVCF = new FileNameExtensionFilter("VCF File", "vcf");
		filterICS = new FileNameExtensionFilter("ICS File", "ics");
		fc.setFileSelectionMode(0);
		fc.setFileFilter(filterVCF);
		fc.setFileFilter(filterICS);
		
		buttonmod = new JButton("Modifier");
		buttonmod.addActionListener(this);
		buttonser = new JButton("Serialiser");		
		buttonhtml = new JButton("HTML");
		openbutton = new JButton("Open File ...");
		openbutton.addActionListener(this);
		
		name = new JTextField("Name");
		number = new JTextField("Number Home");
		mail = new JTextField("Mail");
		adressWork= new JTextField("Adress Work");
		numberWork = new JTextField("Number Work");
		adressHome = new JTextField("Adress Home");
		
		name.setMaximumSize( new Dimension(250,20));
		number.setMaximumSize( new Dimension(250,20));
		numberWork.setMaximumSize( new Dimension(250,20));
		mail.setMaximumSize( new Dimension(250,20));
		adressHome.setMaximumSize( new Dimension(250,20));
		adressWork.setMaximumSize( new Dimension(250,20));
		
		
		box = new JComboBox();
		box.addActionListener(this);
		
		JTextpane.add(box);
    	JTextpane.add(name);
		JTextpane.add(number);
		JTextpane.add(numberWork);
		JTextpane.add(mail);
		JTextpane.add(adressHome);
		JTextpane.add(adressWork);
		
		
		buttonpane.add(buttonmod);
		buttonpane.add(buttonser);
		buttonpane.add(buttonhtml);
		
		
		
		BoxLayout Box1 = new BoxLayout(JTextpane, 1);
		
		JTextpane.setLayout(Box1);
		
		
		
		
		BoxLayout Box2 = new BoxLayout(globalpane,1);
		
		globalpane.setLayout(Box2);
		globalpane.add(JTextpane);
		globalpane.add(buttonpane);
		
		
		scroll = new JScrollPane(textarea);
		splitgauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, openbutton, scroll);
		splitgauche.setDividerSize(0);
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitgauche, globalpane);
		splitPane.setDividerSize(0);
		}
		
	
	public String getType(File file) {
		
		String type = file.getName().substring(file.getName().lastIndexOf("."));
		
		return type;
	}
	
	public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openbutton) {
            int returnVal = fc.showOpenDialog(splitgauche);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                type = getType(file);
                
                
               if (type.equals(".vcf")) {
                	System.out.println("e");
                	card = new HCard(file);
                	textarea.setText("");
                	textarea.append(card.toString());
                	
                	box.removeAllItems();
                	
                	name.setText(card.getName());
                	number.setText(card.getNumberHome());
                	numberWork.setText(card.getNumberWork());
                	mail.setText(card.getMail());
                	adressHome.setText(card.getMail());
            		adressWork.setText(card.getAdressWork());
            		adressWork.setEditable(true);
            		
            		
               }
               else if(type.equals(".ics")) {
            	   
	               	calendar = new HCalendar(file);
	               	textarea.setText("");
	               	textarea.append(calendar.toString());
	               	
	               	event = calendar.getAllSummaries();
	               	
	               	for(int i=0; i < event.length; i++) {
	               		box.addItem(event[i]);
	               	}
	               	oldname = calendar.searchEvent(box.getSelectedItem().toString()).getSummary();
	               	name.setText(oldname);
		           	number.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDescription());
		           	numberWork.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateStart());
		           	mail.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateEnd());
		           	adressHome.setText(calendar.searchEvent(box.getSelectedItem().toString()).getLocation());
		       		adressWork.setText("");
		       		adressWork.setEditable(false);
              }
           }
       }
        else if(e.getSource() == buttonmod) {
        	if(type.equals(".vcf")) {
       				System.out.println("z");
       				card.modify(name.getText(), adressHome.getText(), adressWork.getText(), number.getText(), numberWork.getText(), mail.getText());
       				System.out.println(card.getName());
       				textarea.setText("");
       				textarea.append(card.toString());
        	}
        	else if(type.equals(".ics")) {
        		calendar.modify(oldname, name.getText(), numberWork.getText(), mail.getText(), adressHome.getText(), number.getText());
        		textarea.setText("");
        		textarea.append(calendar.toString());
        	}
        }
       	else if(e.getSource() == buttonser) {
       			if(type.equals(".vcf")) {
       				
       			}
       			else if(type.equals(".ics")) {
       				
       			}
       	}
       	else if(e.getSource() == buttonhtml) {
       		if(type.equals(".vcf")) {
       			card.toHtml();
       		}
       		else if(type.equals(".ics")) {
       			calendar.toHtmlCalendar();
       		}
       	}
       	else if (e.getSource() == box) {
       		if(type.equals(".ics")) {
       			
       			oldname = calendar.searchEvent(box.getSelectedItem().toString()).getSummary();
	       		name.setText(oldname);
	           	number.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDescription());
	           	numberWork.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateStart());
	           	mail.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateEnd());
	           	adressHome.setText(calendar.searchEvent(box.getSelectedItem().toString()).getLocation());
	       		adressWork.setText("");
	       		adressWork.setEditable(false);
       		}
       	}
              
	}
	public JSplitPane getSplitGauche() {
		
		return splitgauche;
	}
	public static JSplitPane getSplitPane() {
		return splitPane;
	}


	public static void setSplitPane(JSplitPane splitPane) {
		Gui.splitPane = splitPane;
	}


	public static void main(String[] args) {
			
			Gui gui =new Gui();
			window = new JFrame();
			window.setTitle("Ma fenetre");
			window.setLocationRelativeTo(null);
			window.add(gui.getSplitPane());
			window.pack();
			window.setResizable(true);
			window.setVisible(true);
	}
}