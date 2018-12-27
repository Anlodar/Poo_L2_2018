/**
 * 
 */
package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *<b>This class represents a calendar build from a ICS file</b>
 *<p>
 *A calendar is characterized by an array of events, that can be modified. 
 *Finally, a html fragment containing all event's htm fragment can be created or calendar's information can be displayed.
 *</p>
 *
 * @author Yael
 */
public class HCalendar implements java.io.Serializable {
	
	/**
	 * implements java.io.Serializable 
	 */
	private static final long serialVersionUID = -5356372060144573590L;
	
	// Tableau (ArrayList) d'HEvent representant le calendrier
	/**
	 * ArrayList containing all the event of an calendar
	 */
	private ArrayList<HEvent> calendar;
	
	// On initialise le tableau et on appelle la methode create() 
	/**
	 * Constructor HCalendar
	 * <p>When an object is build, the ArrayList representing the calendar is initialize
	 *  and the create() method is called to build all the event.</p>
	 * @param file the ics file from which the calendar is build
	 * @see ArrayList#ArrayList()
	 * @see HCalendar#create(File)eate
	 */
	public HCalendar(File file) {
		calendar = new ArrayList<HEvent>();
		create(file);
		
	}
	
	/**
	 * <p>This method is used to create "calendar" from an ics file given in parameter.
	 * It will read the file line by line, stocking all of these in a string array, until the end of an event.
	 * Then it will create and add this event in "calendar".
	 * It will keep going until the end of the file.</p>   
	 * @param vCalendar an ics file
	 * @see BufferedReader
	 * @see String
	 * @see Object#equals(Object, Object)
	 * @see IOException
	 * @see NullPointerException
	 * @see FileNotFoundException
	 */
	public void create(File vCalendar) {
		
		BufferedReader reader = null;
		
		// Tableau de string
		String str[] = new String[100];
		
		try {
			// On cree un BufferedReader reader sur un fichier vCalendar 
			reader = new BufferedReader(new FileReader(vCalendar));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			// On associe a line la valeur de chaque ligne du fichier
			String line = reader.readLine();
			int i = 0;
			
			// Parcour du fichier jusqu'à sa fin
			while(line != null) {
				if (Objects.equals(line, "END:VEVENT") == false) {
					/* Si la ligne n'indique pas la fin d'un evenement 
					on ajoute la ligne dans la tableau de string déclarer plus tôt
					et on continue a lire le fichier*/
					str[i] = line;
					line = reader.readLine();
					i++;
				}
				else {
					/* Sinon on ajoute un nouvel evenement (en utilisant le tableu de string)
					puis on reinitialise le dit tableau et on continue a lire le fichier*/
					calendar.add(new HEvent(str));
					str = new String[str.length];
					i = 0;
					line = reader.readLine();
				}
				
			}
			reader.close();
		}catch (NullPointerException | IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	}
	
	/**
	 * <p>Search an event by checking if this "summary" is equals to the "summary" of an HEvent in "calendar".
	 *  If there is one corresponding it return the event, else it return a "empty" HEvent ('nul")</p>
	 * @param summary the summary it try to find
	 * @return an HEvent corresponding to this "summary" or an empty HEvent
	 */
	public HEvent searchEvent(String summary) {
		/* On parcourt les evenements du calendrier (ArrayList) 
		et si le summary de l'evenement en cour de verification correspond au string passe en arguments
		on retourne l'evenement*/
		for(HEvent event : calendar) {
			if (event.getSummary().contentEquals(summary)) {
				return event;
			}
		}
		// Sinon on retourne null
		return null;

	}
	
	/**
	 * This method use searchEvent() and this "oldSummary" to find the event to modify, then it use these five other Strings to change the values of the fields.
	 * @param oldsummary the summary to find the event to modify
	 * @param newSummary the new summary of this event
	 * @param newDateStart the new start date of this event
	 * @param newDateEnd the new end date of this event
	 * @param newLocation the new location of this event
	 * @param newDescription the new description of this event
	 * @see HEvent#setDateEnd(String)
	 * @see HEvent#setDateStart(String)
	 * @see HEvent#setDescription(String)
	 * @see HEvent#setLocation(String)
	 * @see HEvent#setSummary(String)
	 * @see HCalendar#searchEvent(String)
	 * @see ArrayList#set(int, Object)
	 */
	public void modify(String oldsummary, String newSummary, String newDateStart,
			   String newDateEnd, String newLocation, String newDescription) {
		// TODO Auto-generated method stub
		/*On recherche l'evenement a modifier et, si on le trouve, on modifie ses parametres un à un 
		puis on ajoute cet evenement modifié à la place de l'ancient evenement*/
		HEvent event = searchEvent(oldsummary);
		if (event != null) {
			int i = calendar.indexOf(event);
			event.setDateEnd(newDateEnd);
			event.setDateStart(newDateStart);
			event.setDescription(newDescription);
			event.setLocation(newLocation.replace(';', ','));
			event.setSummary(newSummary);
			calendar.set(i, event);
		}
	}
	
	/**
	 * This method return all the summaries of "calendar"
	 * @return an string array containing all the summaries of the calendar
	 */
	public String[] getAllSummaries() {
		// On cree un tableau de string  
		String str[] = new String[100];
		int i =0;
		for(HEvent event : calendar) {
			/* On parcourt les evenements du calendrier
			et on joute leur summary au tableau cree
			puis tôt puis on retourne ce tableau */
			str[i]=event.getSummary();
			i++;
		}
		return str;

	}

	/**
	 * Create a file containing an html fragment corresponding to a calendar
	 * @param fileName the name of the html file to create
	 * @see File
	 * @see FileReader
	 * @see IOException
	 */
	public void toHtmlCalendar(String fileName) {
		// On cree un fichier html
		File file = new File(fileName);
		FileWriter writer = null;
		// String contnenant le fragment html
		String str = "<div class=\\\"vcalendar\\\">  \n";
		try {
			// On cree un FileWriter sur le fichier html cree
			writer = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			/* On parcour les evenements et on concatene le le string
			et les toHtmlEvent() de chaque evenement */
			for(HEvent event : calendar) {
				str += event.toHtmlEvent();
			}
			str += "</div>";
			
			// On ecrit le fragments html dans le fichier puis on ferme le flux
			writer.write(str);
			writer.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// On cree le string a retourner
		String str = "Calendrier : \n************\n\n";
		/* On parcourt les evenements du calendrier 
		et on concatene leur toString() avec le String cree plus tôt, que l'on retourne*/
		for(HEvent event : calendar) {
			str+= event.toString();
		}
		return str;
	}	

}
