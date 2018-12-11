package gui;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeftSide {
	
	JTextArea textarea;
	JFileChooser fc;
	JScrollPane scroll;
	JSplitPane splitgauche;
	FileFilter filter1;
	FileFilter filterICS;
	
	public LeftSide() {
		
		textarea = new JTextArea(5,5);
		fc = new JFileChooser();
		filter1 = new FileNameExtensionFilter("VCF File", "vcf");
		filterICS = new FileNameExtensionFilter("ICS File", "ics");

		scroll = new JScrollPane(textarea);
		splitgauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fc, scroll);
		
		fc.setFileSelectionMode(0);
		fc.setFileFilter(filter1);
		fc.setFileFilter(filterICS);
		
		textarea.append("Test1"+"\n");
		textarea.append("Test2"+"\n");
		textarea.setEditable(false);

		splitgauche.setDividerSize(0);
	}

	public JSplitPane getSplitGauche() {
		return splitgauche;
	}
		
}