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
		FileWriter writer = null;
		String str = null;
		File file = new File("burnafterusing.txt");
		
		try {
			reader = new BufferedReader(new FileReader(vCalendar));
			System.out.println("reader sur ics cree");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			writer = new FileWriter(file);
			System.out.println("writer sur fichier de merde cree");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			while(reader.readLine() != null) {
				if (reader.readLine().equals("END:VEVENT") == false) {
					System.out.println("creation du string");
						str += reader.readLine();
						
					}
				else {
					writer.write(str);
					System.out.println("ecriture dans fichier de merde");
					calendar.add(new HEventTry(file));
					System.out.println("ajout dun event");
					str = "";
				}
				
			}
			reader.close();
			writer.close();
		}catch (IOException e3) {
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
