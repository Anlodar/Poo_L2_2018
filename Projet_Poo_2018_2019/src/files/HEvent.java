/**
 * 
 */
package files;

import java.io.IOException;

/**
 * @author Yael
 *
 */
public class HEvent implements java.io.Serializable {

	/**
	 * Serial version UID for this class
	 */
	private static final long serialVersionUID = -7262625584590060341L;

	/**
	 * Start date of the event. This field can be changed.
	 * @see HEvent#getDateStart()
	 * @see HEvent#setDateStart(String)
	 */
	private String dateStart;

	/**
	 * End date of the event. This field can be changed.
	 * @see HEvent#getDateEnd()
	 * @see HEvent#setDateEnd(String)
	 */
	private String dateEnd;

	/**
	 * Summary of the event. This field can be changed.
	 * @see HEvent#getSummary()
	 * @see HEvent#setSummary(String)
	 */
	private String summary;

	/**
	 * Location of the event. This field can be changed.
	 * @see HEvent#getLocation()
	 * @see HEvent#setLocation(String)
	 */
	private String location;

	/**
	 * Description of the event. This field can be changed.
	 * @see HEvent#getDescription()
	 * @see HEvent#setDescrition(String)
	 */
	private String description; 

	/**
	 * 	Constructor HEvent
	 * <p>When an object is builded with this constructor, all fields are initialized as an empty String. 
	 * Then, using initParemeter() and a array of string, the fields and the content they have to get are concatenate.</p> 
	 * @param str a String array containing lines of a ics file event 
	 * @see HEvent#initParameter(String, String)
	 * @see IOException
	 * @see NullPointerException
	 */
	public HEvent(String[] str){
		summary = dateEnd = dateStart = location = description = "";
		try {
			for(String line: str) {
				if(line != null && (line.isEmpty() == false)) {
					summary+=initParameter(line, "SUMMARY:");
					location+=initParameter(line, "LOCATION:");
					dateStart+=initParameter(line, "DTSTART;");
					dateEnd+=initParameter(line, "DTEND;");
					description+=initParameter(line, "DESCRIPTION:");
				}
			}
		}catch (NullPointerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 	Constructor HEvent
	 * <p>When an object is builded with this constructor, the fields are initialized from five Strings given in parameter.</p> 
	 * @param summary the summary from which the object is build
	 * @param dateStart the dateStart from which the object is build
	 * @param dateEnd the dateEnd from which the object is build
	 * @param location the location from which the object is build
	 * @param description the description from which the object is build
	 * @see HEvent#setDateEnd(String)
	 * @see HEvent#setDateStart(String)
	 * @see HEvent#setDescription(String)
	 * @see HEvent#setLocation(String)
	 * @see HEvent#setSummary(String)
	 */
	public HEvent(String summary, String dateStart, String dateEnd, String location, String description) {
		setDateEnd(dateEnd);
		setDateStart(dateStart);
		setLocation(location);
		setSummary(summary);
		setDescription(description);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Return a String to initialized a field if the beginning of a "line" is equal to the "separator"
	 * @param line a line of the ics file
	 * @param separator the tag indicating if it's the line to use to initialize the field
	 * @return a String to initialize a field
	 * @throws NullPointerException
	 * @throws IOException
	 * 
	 */
	public String initParameter(String line, String separator) throws NullPointerException, IOException {
		String parameter = "";
		String[] parameterArray = {};
		char[] dateArray = {};
		int i = 0;

		if(line.startsWith(separator)) {
			if (separator.startsWith("DT")) {
				parameterArray = line.split(separator);
				for (i = 0; i < parameterArray.length; i++) {
					parameter += parameterArray[i];
				}
				dateArray = parameter.toCharArray();
				dateArray[10] = dateArray[8];
				dateArray[9] = dateArray[7];
				dateArray[8] = '-';
				dateArray[7] = dateArray[6];
				dateArray[6] = dateArray[5];
				dateArray[5] = '-';
				parameter = new String(dateArray);// + " \n";	
			}
			else {
				parameterArray = line.split(separator);
				for (i = 0; i < parameterArray.length; i++) {
					parameter += parameterArray[i];
				}
			}
		} 
		return parameter;

	}

	/**
	 * @return the dateStart
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * @param dateStart the dateStart to set
	 * @see HEvent#dateStart
	 * 
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd the dateEnd to set
	 * @see HEvent#dateEnd
	 * 
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the summary
	 */
	public  String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 * @see HEvent#summary
	 * 
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location.replaceAll(";", ",");
	}

	/**
	 * @param location the location to set
	 * @see HEvent#location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 * @see HEvent#description
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return an HTML fragment for a vEvent
	 * @return an HTML fragment for a vEvent 
	 * @see HEvent#getSummary()
	 * @see HEvent#getDateStart()
	 * @see HEvent#getDateEnd()
	 * @see HEvent#getLocation()
	 * @see HEvent#getDescription()
	 * 
	 */
	public String toHtmlEvent() {
		return "<div class=\\\"vevent\\\">  \n \t"
				+ "<span class=\\\"description\\\">" + getDescription() + "</span> \n \t"
				+ "<span class=\\\"summary\\\">" + getSummary() + "</span> \n \t"
				+ "<abbr class=\\\"dtstart\\\">" + getDateStart() + "</abbr> \n \t"
				+ "<abbr class=\\\"dtend\\\">" + getDateEnd() + "</abbr>, at the  \n \t"
				+ "<span class=\\\"location\\\">" + getLocation() + "</span> \n"
				+ "</div> \n";

	} 

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return summary + " : " + description + "\n Debute le : " + dateStart + "\n Fini le : " 
				+ dateEnd + "\n A : " + location + "\n\n";
	}

}
