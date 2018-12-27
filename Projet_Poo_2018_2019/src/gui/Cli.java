package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import files.HCalendar;
import files.HCard;

public class Cli  {
	@SuppressWarnings("unused")
	private static String type;
	@SuppressWarnings("unused")
	private HCard card;
	@SuppressWarnings("unused")
	private HCalendar calendar;
	
	public static void main(String[] args) {
		if(args.length == 0) {
			
			// Print the different options //
			System.out.println("-d folder : affiche les fichiers vcf et ics du dossier specifie");
			System.out.println("-i file : Prend en entree file et affiche son contenu en mode console");
			System.out.println("-i file -o file.ser : Prend en entree file et sauvegarde le contenu structure dans un fichier file.ser");
			System.out.println("-i file -h fragment.html : Prend file en entree et genere le fichier fragment.html");
		}
		else if(args[0].equals("-d")) {
			
			ShowFiles(args[1]);
		}
		else if(args[0].equals("-i")) {
			
			Access(args);
		}
		
	}
	// This Method get the String[] and process the command line // 
	/**
	 * Access and deals with the command line in console 
	 * @param args
	 * 
	 * @see String#endsWith(String)
	 * @see File#isFile()
	 * @see HCard#toString()
	 * @see HCard#getName()
	 * @see String#replaceAll(String, String)
	 * @see Cli#serializeHCard(HCard, File)
	 * @see String#equals(Object)
	 * @see HCard#toHtml(String)
	 * @see HCalendar#toString()
	 * @see HCalendar#toHtmlCalendar(String)
	 * @see	Cli#serializeHCalendar(HCalendar, File)
	 * 
	 */
	public static void Access(String[] args) {
		
		if(args[1].endsWith(".vcf")) {
			File file = new File(args[1]);
			if(file.isFile()) {
				type = "VCF";
				HCard card = new HCard(file);
				if(args.length == 2) {
					System.out.println(card.toString());
				}
				else if(args[2].equals("-o")) {
					File fileser = new File(card.getName().replaceAll(" ", "")+".ser");
					serializeHCard(card, fileser);
				}
				else if(args[2].equals("-h")) {
					card.toHtml(card.getName().replaceAll(" ", "") + ".html");
					System.out.println("Le fragment html pour la carte de visite a bien ete cree");
				}
			}
		}
		else if(args[1].endsWith(".ics")) {
			File file = new File(args[1]);
			if(file.isFile()) {
				type = "ICS";
				HCalendar calendar = new HCalendar(file);
				if(args.length == 2) {
					System.out.println(calendar.toString());
				}
				else if(args[2].equals("-o")) {
					File fileser = new File("calendar.ser");
					serializeHCalendar(calendar, fileser);
				}
				else if(args[2].equals("-h")) {
					calendar.toHtmlCalendar("calendar.html");
					System.out.println("Le fragment html pour le calendrier a bien ete cree");
				}
			}
		}
	}
	
	// This method get files from the directory and print them //
	/**
	 * Show the list of files in the directory
	 * @param directory
	 * 
	 * @see File#listFiles()
	 * @see File#getCanonicalPath()
	 */
	public static void ShowFiles(String directory) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            
            // Apply Filter //
            
            public boolean accept(File dir, String name) {
                boolean result;
                if(name.endsWith(".vcf")){
                    result=true;
                }
                else if(name.endsWith(".ics")){
                    result=true;
                }
                else {
                    result=false;
                }
                return result;
            }
        });
        
        
        for(File f:listOfFiles) {
                    try {
                        System.out.println(f.getCanonicalPath());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            
        }
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
                System.out.println(card.getName() + " a ete serialise");
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
			System.out.println(card.getName() + " a ete deserialise");
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
			System.out.println("Le calendrier a ete serialise");
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
}