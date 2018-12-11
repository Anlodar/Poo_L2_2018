/**
 * 
 */
package files;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Yael
 *
 */
public class HEvent implements java.io.Serializable {
	
	/**
	 * Start date of the event. This field can be changed.
	 * @see HEvent#getDateStart()
	 * @see HEvent#setDateStart(String)
	 * 
	 */
	private String dateStart;
	
	/**
	 * End date of the event. This field can be changed.
	 * @see HEvent#getDateEnd()
	 * @see HEvent#setDateEnd(String)
	 * 
	 */
	private String dateEnd;
	
	/**
	 * Summary of the event. This field can be changed.
	 * @see HEvent#getSummary()
	 * @see HEvent#setSummary(String)
	 * 
	 */
	private String summary;
	
	/**
	 * Location of the event. This field can be changed.
	 * @see HEvent#getLocation()
	 * @see HEvent#setLocation(String)
	 * 
	 */
	private String location;
	
	/**
	 * URL of the event. This field can be changed.
	 * @see HEvent#getUrl()
	 * @see HEvent#setUrl(String)
	 * 
	 */
	private String url; 

	/**
	 * 	Constructor HEvent
	 * <p>When an object is builded, all fields are initialized by using a combination of initParemeter method and cleanString method.</p> 
	 * @param bf a BufferedReader from which the fields are initialized 
	 * @see HEvent#cleanString(String)
	 * @see HEvent#initParameter(BufferedReader, String)
	 * @see BufferedReader
	 */
	public HEvent(BufferedReader bf) {
		try {
			while(bf.readLine()!= "END:VEVENT") {
				summary = cleanString(initParameter(bf, "SUMMARY:"));
				dateStart = cleanString(initParameter(bf, "DSTART:"));
				dateEnd = cleanString(initParameter(bf, "DTEND:"));
				location = cleanString(initParameter(bf, "LOCATION:"));
				url = cleanString(initParameter(bf, "URL:"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Return a String to initialize a field
 * @param bf the BufferedReader from which the String is created 
 * @param separator the tag indicating the field to initialize
 * @return a String to initialize a field
 * @see BufferedReader
 */
	public String initParameter(BufferedReader bf, String separator) {
		String parameter = null;
		String[] parameterArray = null;
		char[] dateArray = null;
		int i = 0;

		if(separator.startsWith("D")) {
			try {
				if (bf.readLine().startsWith(separator)) {
					parameterArray = bf.readLine().split(separator);
					for (i = 0; i < parameterArray.length; i++) {
						parameter += parameterArray[i] + " \n";
					}
					dateArray = parameter.toCharArray();
					dateArray[10] = dateArray[8];
					dateArray[9] = dateArray[7];
					dateArray[8] = '-';
					dateArray[7] = dateArray[6];
					dateArray[6] = dateArray[5];
					dateArray[5] = '-';
					parameter = new String(dateArray) + " \n";						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 

		else {
			try {
				if (bf.readLine().startsWith(separator)) {
					parameterArray = bf.readLine().split(separator);
					for (i = 0; i < parameterArray.length; i++) {
						parameter += parameterArray[i];
					}
					parameter += " \n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return parameter;

	}

	/**
	 * Remove all semicolon from a String
	 * @param parameter the String we want to clean
	 * @return a String without any semicolon
	 */
	public String cleanString(String parameter) {
		return parameter.replace(';', ' ');

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
		return location;
	}

	/**
	 * @param location the location to set
	 * @see HEvent#location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 * @see HEvent#url
	 * 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

/**
 * Return an HTML fragment for a vEvent
 * @return an HTML fragment for a vEvent 
 * @see HEvent#getSummary()
 * @see HEvent#getDateStart()
 * @see HEvent#getDateEnd()
 * @see HEvent#getLocation()
 * @see HEvent#getUrl()
 * 
 */
	public String toHtmlEvent() {
		return "<div class=\\\"vevent\\\">  \n \t"
				+ "<a class=\\\"url\\\" href=\\\"" + getUrl() + "\\\"></a> \n \t"
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
		return summary + "\n Debute le:" + dateStart + "\n Fini le:" + dateEnd + "\n A:" + location + "\n " + url + "\n\n";
	}

}
