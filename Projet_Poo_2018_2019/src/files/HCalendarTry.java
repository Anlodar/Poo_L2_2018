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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Yael
 *
 */
public class HCalendarTry {
	private ArrayList<HEventTry> calendar;
	
	public HCalendarTry(File file) {
		calendar = new ArrayList<HEventTry>();
		create(file);
		
	}
	
	public void create(File vCalendar) {
		BufferedReader reader = null;
		String str[] = new String[30];
		
		try {
			reader = new BufferedReader(new FileReader(vCalendar));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			String line = reader.readLine();
			int i = 0;
			for(String s : str) { s = "";}
			while(line != null) {
				if (Objects.equals(line, "END:VEVENT") == false) {
					System.out.println("creation du string");
						str[i] = line;
						line = reader.readLine();
						i++;
					}
				else {
					System.out.println(str);
					
					calendar.add(new HEventTry(str));
					System.out.println("ajout dun event");
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HCalendarTry [calendar=" + calendar + "]";
	}
	
	

}
