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
 * @author Yael
 */
public class HCalendar {
	
	/**
	 * ArrayList containing all the event of an calendar
	 */
	private ArrayList<HEvent> calendar;
	
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
		String str[] = new String[100];
		
		try {
			reader = new BufferedReader(new FileReader(vCalendar));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			String line = reader.readLine();
			int i = 0;
			while(line != null) {
				if (Objects.equals(line, "END:VEVENT") == false) {
						str[i] = line;
						line = reader.readLine();
						i++;
					}
				else {					
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
		for(HEvent event : calendar) {
			if (event.getSummary().contentEquals(summary)) {
				return event;
			}
		}
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
	public void modify(String oldsummary, String newSummary, String newDateStart, String newDateEnd, String newLocation, String newDescription) {
		// TODO Auto-generated method stub
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
		String str[] = new String[100];
		int i =0;
		for(HEvent event : calendar) {
			str[i]=event.getSummary();
			i++;
		}
		return str;

	}

	/**
	 * Create a file containing an html fragment corresponding to a calendar
	 * @see File
	 * @see FileReader
	 * @see IOException
	 */
	public void toHtmlCalendar(String fileName) {
		File file = new File(fileName);
		FileWriter writer = null;
		String str = "<div class=\\\"vcalendar\\\">  \n";
		try {
			writer = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for(HEvent event : calendar) {
				str += event.toHtmlEvent();
			}
			str += "</div>";
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
		String str = "Calendrier : \n************\n\n";
		for(HEvent event : calendar) {
			str+= event.toString();
		}
		return str;
	}
	
	

}
