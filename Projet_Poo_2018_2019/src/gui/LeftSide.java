package gui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.HCalendarDictionnary;
import files.HCard;

/**
 * @author Quentin
 *
 */

public class LeftSide implements ActionListener {
	
	JButton openbutton;
	JTextArea textarea;
	JFileChooser fc;
	JScrollPane scroll;
	JSplitPane splitgauche;
	FileFilter filterVCF;
	FileFilter filterICS;
	JPanel buttonpane;
	HCard card;
	HCalendarDictionnary calendar;
	
	/**
	 * 
	 */
	public LeftSide() {
		
		textarea = new JTextArea(10,40);
		fc = new JFileChooser();
		filterVCF = new FileNameExtensionFilter("VCF File", "vcf");
		filterICS = new FileNameExtensionFilter("ICS File", "ics");
		openbutton = new JButton("Open File ...");
		openbutton.addActionListener(this);
		
		scroll = new JScrollPane(textarea);
		splitgauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, openbutton, scroll);
		
		fc.setFileSelectionMode(0);
		fc.setFileFilter(filterVCF);
		fc.setFileFilter(filterICS);
		
		
		textarea.setEditable(false);

		splitgauche.setDividerSize(0);
	}

	public JSplitPane getSplitGauche() {
		return splitgauche;
	}
	
	public File FileChoosed(JFileChooser fc) {
		
		return fc.getSelectedFile();
	}
	
	public JFileChooser getFileChooser() {
		
		return fc;
	}
	
	public JTextArea getTextarea() {
		return textarea;
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
                File file = fc.getSelectedFile();
                String type = getType(file);
                System.out.println(type);
                
               if (type.equals(".vcf")) {
                	System.out.println("e");
                	card = new HCard(file);
                	textarea.append(card.toString());
               }
               else if(type.equals("ics")) {
                	calendar = new HCalendarDictionnary();
                	textarea.append(calendar.toString());
               }
            }
        }
	}
}
