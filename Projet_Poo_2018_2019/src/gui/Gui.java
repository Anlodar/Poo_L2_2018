package ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
import files.HCard;

/**
 * <b> Gui is the Graphic interface of our project.</b>
 * <p>With it you can 
 * <ul>
 * <li>Read and print a specified VCard or ICalendar</li>
 * <li>Modify the specified Card </li>
 * <li>Modify an Event in a specified Calendar </li>
 * <li>Save a file with the serialization</li>
 * <li>Build a HTML fragment of the selected file </li>
 *</p>
 *
 * @author Quentin
 *
 */
public class Gui implements ActionListener{

	// Name All Variable used in Gui //
	private File file;
	private static JFrame window;
	private JButton openbutton;
	private JTextArea textarea;
	private JFileChooser fc;
	private JScrollPane scroll;
	private static JSplitPane splitgauche;
	private FileFilter filterVCF;
	private FileFilter filterICS;
	private FileFilter filterSER;
	private JPanel buttonpane;
	private HCard card;
	private HCalendar calendar;
	static JSplitPane splitPane;
	private String type;

	private JPanel globalpane;
	private JPanel JTextpane;
	private JButton buttonmod;
	private JButton buttonser;		
	private JButton buttonhtml;

	private JComboBox<String> box;
	private String[] event;
	private String oldname;

	private JTextField name;
	private JTextField number;
	private JTextField mail;
	private JTextField adressWork;
	private JTextField numberWork;
	private JTextField adressHome;

	/**
	 * Constructor Gui
	 * <p> When an object is builded, initialize parameter and wait the action to be done</p>
	 * 
	 * @see JTextField#setMaximumSize(Dimension)
	 * @see JTextArea#setEditable(boolean)
	 * @see JFileChooser#setFileSelectionMode(int)
	 * @see JFileChooser#setFileFilter(FileFilter)
	 * @see JButton#addActionListener(ActionListener)
	 * @see JComboBox#addActionListener(ActionListener)
	 * @see JPanel#add(java.awt.Component)
	 * @see JSplitPane#setDividerSize(int)
	 */
	public Gui() {

		// Initialize Panel And TextArea //
		globalpane = new JPanel();
		JTextpane = new JPanel();
		buttonpane = new JPanel();
		textarea = new JTextArea(20,70);

		// Initialize FileChooser and its Filter //
		fc = new JFileChooser();
		filterVCF = new FileNameExtensionFilter("VCF File", "vcf");
		filterICS = new FileNameExtensionFilter("ICS File", "ics");
		filterSER = new FileNameExtensionFilter("SER File", "ser");
		
		// Initialize Buttons //
		buttonmod = new JButton("Modifier");
		buttonser = new JButton("Serialiser");		
		buttonhtml = new JButton("HTML");
		openbutton = new JButton("Open File ...");

		// Initialize JTextField //
		name = new JTextField("Name");
		number = new JTextField("Number Home");
		mail = new JTextField("Mail");
		adressWork= new JTextField("Adress Work");
		numberWork = new JTextField("Number Work");
		adressHome = new JTextField("Adress Home");

		// Initialize the ComboBox and the scrollPane who contains the textarea //
		box = new JComboBox<String>();
		scroll = new JScrollPane(textarea);

		// Initialize Box layouts for JTextpane and globalpane //
		BoxLayout Box1 = new BoxLayout(JTextpane, 1);
		BoxLayout Box2 = new BoxLayout(globalpane,1);

		// Disable the Editable on textarea and set Filter on FileChooser //
		textarea.setEditable(false);
		fc.setFileSelectionMode(0);
		fc.setFileFilter(filterVCF);
		fc.setFileFilter(filterICS);
		fc.setFileFilter(filterSER);

		// Add ActionListener on object //
		openbutton.addActionListener(this);
		buttonmod.addActionListener(this);
		buttonser.addActionListener(this);
		buttonhtml.addActionListener(this);
		box.addActionListener(this);

		// Set the Size of JTextFields //
		name.setMaximumSize( new Dimension(250,20));
		number.setMaximumSize( new Dimension(250,20));
		numberWork.setMaximumSize( new Dimension(250,20));
		mail.setMaximumSize( new Dimension(250,20));
		adressHome.setMaximumSize( new Dimension(250,20));
		adressWork.setMaximumSize( new Dimension(250,20));

		// Add elements to corresponding Panel //
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

		// Set Layout on these panel //
		JTextpane.setLayout(Box1);
		globalpane.setLayout(Box2);

		// Add the Two panel into a global one //
		globalpane.add(JTextpane);
		globalpane.add(buttonpane);

		// Initialize the splitPane in the left part, fill it with a button and a scrollPane and set the divider size to 0 to lock it //
		splitgauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, openbutton, scroll);
		splitgauche.setDividerSize(0);
		//Initialize the global splitPane , fill it with the left splitPane and the global panel and set the divider size to 0 to lock it //
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitgauche, globalpane);
		splitPane.setDividerSize(0);
	}
	/**
	 * @param file
	 * Take a file and return the extension 
	 * @return type the extension of the file
	 */
	public String getType(File file) {
		// Take the name of the file in entry and return the extension corresponding //
		String type = file.getName().substring(file.getName().lastIndexOf("."));
		return type;
	}
	/**
	 * Wait an action to performed an appropriate answer, verify the type of the choosed file and adapt the gui 
	 * 
	 * @see ActionEvent#getSource()
	 * @see JFileChooser#showOpenDialog(java.awt.Component)
	 * @see Gui#getType(File)
	 * @see Gui#equals(Object)
	 * @see JTextArea#append(String)
	 * @see JTextArea#setText(String)
	 * @see JComboBox#removeAllItems()
	 * @see JTextField#setText(String)
	 * @see HCard#toString()
	 * @see HCard#getName()
	 * @see HCard#getNumberHome()
	 * @see HCard#getNumberWork()
	 * @see HCard#getMail()
	 * @see HCard#getAdressHome()
	 * @see HCard#getAdressWork()
	 * @see JTextField#setEditable(boolean)
	 * @see HCalendar#toString()
	 * @see HCalendar#getAllSummaries()
	 * @see JComboBox#addItem(Object)
	 * @see HCalendar#searchEvent(String)
	 * @see JComboBox#getSelectedItem()
	 * @see JComboBox#toString()
	 * @see HEventTry#getSummary()
	 * @see HEventTry#getDateStart()
	 * @see HEventTry#getDateEnd()
	 * @see HEventTry#getDescription()
	 * @see HEventTry#getLocation()
	 * @see JTextField#getText()
	 * @see HCard#modify(String, String, String, String, String, String)
	 * @see HCalendar#modify(String, String, String, String, String, String)
	 * @see HCalendar#toHtmlCalendar()
	 * @see HCard#toHtml() 
	 */
	public void actionPerformed(ActionEvent e) {
		//Handle openbutton action.
		if (e.getSource() == openbutton) {
			int returnVal = fc.showOpenDialog(splitgauche);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				type = getType(file);
				// Case where the file choosed is a Vcard //
				if (type.equals(".vcf")) {
					card = new HCard(file);
					textarea.setText("");
					textarea.append(card.toString());

					box.removeAllItems();

					// Set the current Variable in the JTextFields //
					name.setText(card.getName());
					number.setText(card.getNumberHome());
					numberWork.setText(card.getNumberWork());
					mail.setText(card.getMail());
					adressHome.setText(card.getAdressHome());
					adressWork.setText(card.getAdressWork());
					adressWork.setEditable(true);
				}
				//Handle the case where the file choosed is a VCalendar //
				else if(type.equals(".ics")) {

					calendar = new HCalendar(file);
					textarea.setText("");
					textarea.append(calendar.toString());

					event = calendar.getAllSummaries();

					for(int i=0; i < event.length; i++) {
						if(event[i] != null) {
							box.addItem(event[i]);
						}
					}

					// Set the current variable in JTextFields
					oldname = calendar.searchEvent(box.getSelectedItem().toString()).getSummary();
					name.setText(oldname);
					number.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDescription());
					numberWork.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateStart());
					mail.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateEnd());
					adressHome.setText(calendar.searchEvent(box.getSelectedItem().toString()).getLocation());
					adressWork.setText("");
					adressWork.setEditable(false);
				}
				else if(type.equals(".ser")) {
					if(file.getName().equals("calendar.ser")) {
						calendar = deserializeHCalendar(file);
						textarea.setText("");
						textarea.append(calendar.toString());

						event = calendar.getAllSummaries();

						for(int i=0; i < event.length; i++) {
							if(event[i] != null) {
								box.addItem(event[i]);
							}
						}

						// Set the current variable in JTextFields
						oldname = calendar.searchEvent(box.getSelectedItem().toString()).getSummary();
						name.setText(oldname);
						number.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDescription());
						numberWork.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateStart());
						mail.setText(calendar.searchEvent(box.getSelectedItem().toString()).getDateEnd());
						adressHome.setText(calendar.searchEvent(box.getSelectedItem().toString()).getLocation());
						adressWork.setText("");
						adressWork.setEditable(false);
						type = ".ics";
					}
					else {
						card = deserializeHCard(file);
						textarea.setText("");
						textarea.append(card.toString());

						box.removeAllItems();

						// Set the current Variable in the JTextFields //
						name.setText(card.getName());
						number.setText(card.getNumberHome());
						numberWork.setText(card.getNumberWork());
						mail.setText(card.getMail());
						adressHome.setText(card.getMail());
						adressWork.setText(card.getAdressWork());
						adressWork.setEditable(true);
						type = ".vcf";
					}
				}
			}
		}
		// Handle buttonmod action //
		else if(e.getSource() == buttonmod) {
			// Case where the file choosed is a VCard //
			if(type.equals(".vcf")) {
				card.modify(name.getText(), adressHome.getText(), adressWork.getText(), number.getText(), numberWork.getText(), mail.getText());
				textarea.setText("");
				textarea.append(card.toString());
			}
			// Case where the file choosed is a VCalendar //
			else if(type.equals(".ics")) {
				calendar.modify(oldname, name.getText(), numberWork.getText(), mail.getText(), adressHome.getText(), number.getText());
				textarea.setText("");
				textarea.append(calendar.toString());
			}
		}
		// Handle buttonser action //
		else if(e.getSource() == buttonser) {
			// Case where the file choosed is a VCard //
			if(type.equals(".vcf")) {
				serializeHCard(card, new File(card.getName().replaceAll(" ", "") + ".ser"));
			}
			// Case where the file choosed is a VCalendar //
			else if(type.equals(".ics")) {
				serializeHCalendar(calendar, new File("calendar.ser"));
			}
		}
		// Handle buttonhtml action //
		else if(e.getSource() == buttonhtml) {
			// Case where the file choosed is a VCard //
			if(type.equals(".vcf")) {
				card.toHtml(card.getName().replaceAll(" ", "") + ".html");
			}
			// Case where the file choosed is a VCalendar //
			else if(type.equals(".ics")) {
				calendar.toHtmlCalendar("calendar.html");
			}
		}
		// Handle the JComboBox choice of Event //
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
	/** 
	 * @return splitgauche the left part of the GUI
	 */
	public JSplitPane getSplitGauche() {

		return splitgauche;
	}
	/**
	 * @return splitPane the entire GUI
	 */
	public JSplitPane getSplitPane() {
		return splitPane;
	}
	
	// Serialize a HCard //
	/**
	 * Serialize the HCard
	 * 
	 * @param card
	 * @param fileName
	 * 
	 * @see ObjectOutputStream#writeObject(Object)
	 * @see ObjectOutputStream#close()
	 * @see HCard#getName()
	 * @see IOException#printStackTrace()
	 */
	public static void serializeHCard(HCard card, File fileName) {
        try {
                ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName));
                oout.writeObject(card);
                oout.close();
        } catch (IOException ioe) {
                ioe.printStackTrace();
        }
	}
	
	// Deserialize a HCard //
	/**
	 * Deserialize an HCard
	 * 
	 * @param fileName
	 * @return card the HCard deserialize
	 * 
	 * @see ObjectOutputStream#writeObject(Object)
	 * @see ObjectOutputStream#close()
	 * @see HCard#getName()
	 * @see IOException#printStackTrace()
	 */
	public static HCard deserializeHCard(File fileName) {
		HCard card = null;
		try {
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName));
			card = (HCard) oin.readObject();
			oin.close();
		} catch (ClassNotFoundException nfe) {
			nfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return card;
	}
	// Serialize a HCalendar //
	/**
	 * Serialize a HCalendar
	 * 
	 * @param calendar
	 * @param fileName
	 * 
	 * @see ObjectOutputStream#writeObject(Object)
	 * @see ObjectOutputStream#close()
	 * @see HCard#getName()
	 * @see IOException#printStackTrace()
	 */
	public static void serializeHCalendar(HCalendar calendar, File fileName) {
		try {
			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName));
			oout.writeObject(calendar);
			oout.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	// Deserialize a HCalendar //
	/**
	 * Deserialize a HCalendar
	 * 
	 * @param fileName
	 * @return calendar the HCalendar deserialize
	 * @see ObjectOutputStream#writeObject(Object)
	 * @see ObjectOutputStream#close()
	 * @see HCard#getName()
	 * @see IOException#printStackTrace()
	 */
	public static HCalendar deserializeHCalendar(File fileName) {
		HCalendar calendar = null;
		try {
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName));
			calendar = (HCalendar) oin.readObject();
			oin.close();
		} catch (ClassNotFoundException nfe) {
			nfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return calendar;
	}
	public static void main(String[] args) {

		Gui gui =new Gui();
		// Creation of the Window //
		window = new JFrame();
		window.setTitle("Ma fenetre");
		window.setLocationRelativeTo(null);
		window.add(gui.getSplitPane());
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
	}
}
