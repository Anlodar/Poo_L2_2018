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
	private static String type;
	private HCard card;
	private HCalendar calendar;
	
	public static void main(String[] args) {
		if(args[0].equals(null)) {
			
			System.out.println("-d folder : affiche les fichiers vcf et ics du dossier spécifié");
			System.out.println("-i file : Prend en entrée file et affiche son contenu en mode console");
			System.out.println("-i file -o file.ser : Prend en entrée file et sauvegarde le contenu structuré dans un fichier file.ser");
			System.out.println("-i file -h fragment.html : Prend file en entrée et génère le fichier fragment.html");
		}
		else if(args[0].equals("-d")) {
			
			ShowFiles(args[1]);
		}
		else if(args[0].equals("-i")) {
			
			Access(args);
		}
		
	}
	
	public static void Access(String[] args) {
		
		if(args[1].endsWith(".vcf")) {
			File file = new File(args[1]);
			if(file.isFile()) {
				type = "VCF";
				HCard card = new HCard(file);
				if(args[2].equals(null)) {
					System.out.println(card.toString());
				}
				else if(args[2].equals("-o")) {
					File fileser = new File(card.getName().replaceAll(" ", "")+".ser");
					serializeHCard(card, fileser);
				}
				else if(args[2].equals("-h")) {
					card.toHtml();
				}
			}
		}
		else if(args[1].endsWith(".ics")) {
			File file = new File(args[1]);
			if(file.isFile()) {
				type = "ICS";
				HCalendar calendar = new HCalendar(file);
				if(args[2].equals(null)) {
					System.out.println(calendar.toString());
				}
				else if(args[2].equals("-o")) {
					File fileser = new File("calendar.ser");
					serializeHCalendar(calendar, fileser);
				}
				else if(args[2].equals("-h")) {
					calendar.toHtmlCalendar();
				}
			}
		}
	}
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
	public static void serializeHCard(HCard card, File fileName) {
        try {
                ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName));
                oout.writeObject(card);
                System.out.println(card.getName() + " a été serialisé");
                oout.close();
        } catch (IOException ioe) {
                ioe.printStackTrace();
        }
	 }
	public static HCard deserializeHCard(File fileName) {
		HCard card = null;
		try {
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName));
			card = (HCard) oin.readObject();
			System.out.println(card.getName() + " a été deserialise");
			oin.close();
		} catch (ClassNotFoundException nfe) {
			nfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return card;
	}
	public static void serializeHCalendar(HCalendar calendar, File fileName) {
		try {
			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName));
			oout.writeObject(calendar);
			System.out.println();
			oout.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

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