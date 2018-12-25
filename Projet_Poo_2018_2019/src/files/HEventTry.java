/**
 * 
 */
package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Yael
 *
 */
public class HEventTry implements java.io.Serializable {
	
	/**
	 * Start date of the event. This field can be changed.
	 * @see HEventTry#getDateStart()
	 * @see HEventTry#setDateStart(String)
	 * 
	 */
	private String dateStart;
	
	/**
	 * End date of the event. This field can be changed.
	 * @see HEventTry#getDateEnd()
	 * @see HEventTry#setDateEnd(String)
	 * 
	 */
	private String dateEnd;
	
	/**
	 * Summary of the event. This field can be changed.
	 * @see HEventTry#getSummary()
	 * @see HEventTry#setSummary(String)
	 * 
	 */
	private String summary;
	
	/**
	 * Location of the event. This field can be changed.
	 * @see HEventTry#getLocation()
	 * @see HEventTry#setLocation(String)
	 * 
	 */
	private String location;
	
	/**
	 * Description of the event. This field can be changed.
	 * @see HEventTry#getUrl()
	 * @see HEventTry#setUrl(String)
	 * 
	 */
	private String description; 

	/**
	 * 	Constructor HEvent
	 * <p>When an object is builded, all fields are initialized by using a combination of initParemeter method and cleanString method.</p> 
	 * @param bf a BufferedReader from which the fields are initialized 
	 * @see HEventTry#cleanString(String)
	 * @see HEventTry#initParameter(BufferedReader, String)
	 * @see BufferedReader
	 */
	public HEventTry(String[] str){
		summary = dateEnd = dateStart = location = description = "";
		try {
			//summary = dateEnd = dateStart = location = description = "";
			System.out.println(description+ dateEnd + dateStart + location + summary + "num 1*********************");
			for(String line: str) {
			if(line != null && (line.isEmpty() == false)) {
				System.out.println("COUCOU TA MEReQIFJOSF,F,Q,FPQEF,PF,OE,F");
				//System.out.println(description+ dateEnd + dateStart + location + summary + "num 1*********************");
				summary+=initParameter(line, "SUMMARY:");
				location+=initParameter(line, "LOCATION:");
				dateStart+=initParameter(line, "DTSTART;");
				dateEnd+=initParameter(line, "DTEND;");
				description+=initParameter(line, "DESCRIPTION:");
				//System.out.println(description+ dateEnd + dateStart + location + summary + "num 2*********************");}
			}}System.out.println("*********PARAMETRE FINAUX DE LEVENT ====" + summary + dateEnd + dateStart + location + description);
		}catch (NullPointerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


/**
 * Return a String to initialize a field
 * @param bf the BufferedReader from which the String is created 
 * @param separator the tag indicating the field to initialize
 * @return a String to initialize a field
 * @throws IOException 
 * @see BufferedReader
 */
	public String initParameter(String line, String separator) throws NullPointerException, IOException {
		String parameter = "";
		String[] parameterArray = {};
		char[] dateArray = {};
		int i = 0;


		System.out.println(line);

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
					//parameter += " \n";
				}
			} 
		System.out.println("*****PARAMETRE CREE === "+ parameter);return parameter;

	}

	/**
	 * Remove all semicolon from a String
	 * @param parameter the String we want to clean
	 * @return a String without any semicolon
	 */
	public String cleanString(String parameter) {
		return parameter.replaceAll(";", ", ");

	}


	/**
	 * @return the dateStart
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * @param dateStart the dateStart to set
	 * @see HEventTry#dateStart
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
	 * @see HEventTry#dateEnd
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
	 * @see HEventTry#summary
	 * 
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return cleanString(location);
	}

	/**
	 * @param location the location to set
	 * @see HEventTry#location
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
	 * @param url the description to set
	 * @see HEventTry#url
	 * 
	 */
	public void setUrl(String description) {
		this.description = description;
	}

/**
 * Return an HTML fragment for a vEvent
 * @return an HTML fragment for a vEvent 
 * @see HEventTry#getSummary()
 * @see HEventTry#getDateStart()
 * @see HEventTry#getDateEnd()
 * @see HEventTry#getLocation()
 * @see HEventTry#getUrl()
 * 
 */
	public String toHtmlEvent() {
		return "<div class=\\\"vevent\\\">  \n \t"
				+ "<span class=\\\"description\\\">" + getDescription() + "</span> \n \t"
				+ "<span class=\\\"summary\\\">" + getSummary() + "</span> \n \t"
				+ "<abbr class=\\\"dtstart\\\">" + getDateStart() + "</abbr>- \n \t"
				+ "<abbr class=\\\"dtend\\\">" + getDateEnd() + "</abbr>, at the  \n \t"
				+ "<span class=\\\"location\\\">" + getLocation() + "</span> \n"
				+ "</div> \n";

	} 

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return summary + " : " + description + "\n Debute le : " + dateStart + "\n Fini le : " + dateEnd + "\n A : " + location + "\n\n";
	}

}
