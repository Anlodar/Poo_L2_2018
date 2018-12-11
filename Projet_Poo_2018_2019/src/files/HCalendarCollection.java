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
import java.util.Iterator;

/**
 * @author Yael
 *
 */
public class HCalendarCollection {

	private ArrayList<HEvent> calendar;


	/**
	 * @param calendar
	 */
	public HCalendarCollection() {
		calendar = new ArrayList<HEvent>(200);
	}

	public void create(File vCalendar) {
		BufferedReader bf = null;

		try {
			bf = new BufferedReader(new FileReader(vCalendar));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while(bf.readLine() != null) {
				if(bf.readLine().startsWith("BEGIN:VEVENT")) {
					calendar.add(new HEvent(bf));
				}

			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



	public void toHtml() {
		File fileName = new File("calendar.html");
		FileWriter fw = null;
		String str =  "<div class=\"vcalendar\">  \n";
		Iterator<HEvent> iterator = calendar.iterator(); 


		try {
			fw = new FileWriter(fileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(iterator.hasNext()) {
				str += iterator.next().toHtmlEvent();
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

}
