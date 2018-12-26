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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Yael
 *
 */
public class HCalendar {
	private ArrayList<HEventTry> calendar;
	
	public HCalendar(File file) {
		calendar = new ArrayList<HEventTry>();
		create(file);
		
	}
	
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
					calendar.add(new HEventTry(str));
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
	
	public HEventTry searchEvent(String summary) {
		for(HEventTry event : calendar) {
			if (event.getSummary().contentEquals(summary)) {
				return event;
			}
		}
		HEventTry nul = new HEventTry("---","---","---","---","---");
		return nul;

	}
	
	public void modify(String oldsummary, String newSummary, String newDateStart, String newDateEnd, String newLocation, String newDescription) {
		// TODO Auto-generated method stub
		HEventTry event = searchEvent(oldsummary);
		if (event != null) {
			int i = calendar.indexOf(event);
			event.setDateEnd(newDateEnd);
			event.setDateStart(newDateStart);
			event.setDescription(newDescription);
			event.setLocation(newLocation);
			event.setSummary(newSummary);
			calendar.set(i, event);
		}
	}
	
	public String[] getAllSummaries() {
		String str[] = new String[100];
		int i =0;
		for(HEventTry event : calendar) {
			str[i]=event.getSummary();
			i++;
		}
		return str;

	}

	public void toHtmlCalendar() {
		File fileName = new File("././calendar.html");
		FileWriter writer = null;
		String str = "<div class=\\\"vcalendar\\\">  \n";
		try {
			writer = new FileWriter(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for(HEventTry event : calendar) {
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
		for(HEventTry event : calendar) {
			str+= event.toString();
		}
		return str;
	}
	
	

}
