/**
 * 
 */
package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Yael
 *
 */
public class HCalendarDictionnary implements java.io.Serializable {

	/**
	 * HashMap representing a Calendar
	 */
	private HashMap<Integer, HEvent> calendar;

	/**
	 * Constructor HCard
	 * <p>When an object is builded, the hashmap representing a calendar is initialized at 200.</p>
	 * @param calendar the HashMap representing a calendar
	 * @see HCalendarDictionnary#calendar
	 * @see HashMap#HashMap(int)
	 */
	public HCalendarDictionnary() {
		calendar = new HashMap<Integer, HEvent>(200);
	}

	/**
	 * Return a String to initialize a field
	 * @param vCalendar ICS file from which the calendar is created
	 * @see HCalendarDictionnary#calendar
	 * @see HEvent#HEvent(BufferedReader)
	 * @see BufferedReader
	 * @see FileReader#FileReader(String)
	 */
	public void create(File vCalendar) {
		BufferedReader reader = null;
		int i=0;

		try {
			reader = new BufferedReader(new FileReader(vCalendar));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while(reader.readLine() != null) {
				if(reader.readLine().startsWith("BEGIN:VEVENT")) {
					i++;
					calendar.put(i, new HEvent(reader));
				}
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Change the fields of one event of this calendar 
	 * 
	 * @param key the to select the event to modify
	 * @param summary the new summary of the event
	 * @param dateStart the new dateStart of the event
	 * @param dateEnd the new dateEnd of the event
	 * @param location the new location of the event
	 * @param url the new URL of the event
	 * @see HCalendarDictionnary#calendar
	 * @see HEvent#setSummary()
	 * @see HEvent#setDateStart()
	 * @see HEvent#setDateEnd()
	 * @see HEvent#setLocation()
	 * @see HEvent#setUrl()
	 * 
	 */
	public void modify(int key, String summary, String dateStart, String dateEnd, String location, String url) {
		HEvent event = calendar.get(key);
		event.setSummary(summary);
		event.setDateStart(dateStart);
		event.setDateEnd(dateEnd);
		event.setLocation(location);
		event.setUrl(url);
	}

	/**
	 * Create a file containing an html fragment corresponding to a calendar
	 * 
	 * @see HCalendarDictionnary#calendar
	 * @see HEvent#toHtmlEvent()
	 * @see FileWriter
	 * @see Iterator
	 */
	public void toHtml() {
		File fileHtml = new File("calendar.html");
		FileWriter fw = null;
		String str =  "<div class=\"vcalendar\">  \n";
		Integer key = null;
		Iterator<Integer> iterator = calendar.keySet().iterator(); 

		try {
			fw = new FileWriter(fileHtml);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while(iterator.hasNext()) {
				key = iterator.next();
				str += calendar.get(key).toHtmlEvent();
			}
			str += "</div>";
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Calendar= \n" + calendar;
	}

	/**
	 * Serialize an HCalendar to a binary file
	 * @param calendar a calendar to serialize
	 */
	 public static void serialiser(HCalendarDictionnary calendar) {
         try {
                 FileOutputStream fout = new FileOutputStream("./");
                 ObjectOutputStream oout = new ObjectOutputStream(fout);
                 oout.writeObject(calendar);
                 System.out.println("The calendar has been serialized");
                 oout.close();
                 fout.close();
         } catch (IOException ioe) {
                 ioe.printStackTrace();
         }
	 }

	 /**
	  * Unserialize an HCalendar from a serialized binary file
	  * @return an HCalendar from a binary file
	  */
	 public static HCalendarDictionnary deserialiser() {
         HCalendarDictionnary calendar = null;
         try {
                 FileInputStream fin = new FileInputStream("./");
                 ObjectInputStream oin = new ObjectInputStream(fin);
                 calendar = (HCalendarDictionnary) oin.readObject();
                 System.out.println("The calendar has been deserialized");
                 oin.close();
                 fin.close();
         } catch (ClassNotFoundException nfe) {
                 nfe.printStackTrace();
         } catch (IOException ioe) {
                 ioe.printStackTrace();
         }
         return calendar;
	 }
}
