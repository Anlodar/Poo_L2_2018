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

/**
 * @author Yael
 *
 */
public class HCard implements java.io.Serializable {

	/**
	 * Serial version UID for this class
	 */
	private static final long serialVersionUID = -3292298168924048584L;

	/**
	 * Name of the person. This field can be changed
	 *@see HCard#getName()
	 *@see HCard#setName(String)
	 *
	 */
	private String name;

	/**
	 * Personal address of the person. This field can be changed
	 * @see HCard#getAdressHome()
	 * @see HCard#setAdressHome(String)
	 * 
	 */
	private String adressHome;

	/**
	 * Work address of the person. This field can be changed
	 * @see HCard#getAdressWork()
	 * @see HCard#setAdressWork(String)
	 * 
	 */
	private String adressWork;

	/**
	 * Personal number of the person. This field can be changed
	 * @see HCard#getNumberHome()
	 * @see HCard#setNumberHome(String)
	 * 
	 */
	private String numberHome;

	/**
	 * Work number of the person. This field can be changed
	 * @see HCard#getNumberWork()
	 * @see HCard#setNumberWork(String)
	 * 
	 */
	private String numberWork;

	/**
	 * Email address of the person. This field can be changed
	 * @see HCard#getMail()
	 * @see HCard#setMail(String)
	 * 
	 */
	private String mail;

	/**
	 * 	Constructor HCard
	 * <p>When an object is builded, all fields are initialized by using initParemeter method.</p>
	 *   
	 * @param fileNameVCard the name of the file in reading
	 * @see HCard#name
	 * @see HCard#adressHome
	 * @see HCard#adressWork
	 * @see HCard#numberHome
	 * @see HCard#numberWork
	 * @see HCard#mail
	 * @see HCard#initParameter(String, String)
	 */
	public HCard(File fileNameVCard) {
		name = adressHome = adressWork = numberHome = numberWork = mail = "";
		name += initParameter(fileNameVCard, "FN:");
		adressHome += initParameter(fileNameVCard, "ADR;TYPE=HOME:;;");
		adressWork += initParameter(fileNameVCard, "ADR;TYPE=WORK:;;");
		numberHome += initParameter(fileNameVCard, "TEL;TYPE=HOME:");
		numberWork += initParameter(fileNameVCard, "TEL;TYPE=WORK:");
		mail += initParameter(fileNameVCard, "EMAIL:");
	}

	/**
	 * Return a String to initialize a field
	 * @param fileNameVCard the name of the file in reading
	 * @param separator the tag indicating the field to initialize
	 * @return a String to initialize a field
	 * @see BufferedReader
	 * @see FileReader#FileReader(String)
	 */
	public String initParameter(File fileNameVCard, String separator) {
		String str;
		String[] parameterArray = null;
		String parameter = "";
		int i = 0;
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(fileNameVCard));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while ((str = bf.readLine()) != null) {
				if (str.startsWith(separator)) {
					parameterArray = str.split(separator);
					for (i=1 ; i < parameterArray.length; i++) {
						parameter += parameterArray[i];
					}
				}
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return parameter;
	}

	/**
	 * Remove all semicolon from a String
	 * 
	 * @param parameter the String we want to clean
	 * @return a String without any semicolon
	 */
	public String cleanString(String parameter) {
		return parameter.replaceAll(";", ", ");

	}

	public void modify(String name, String adressHome, String adressWork, String numberHome, String numberWork, String mail) {
		setAdressHome(adressHome);
		setAdressWork(adressWork);
		setMail(mail);
		setName(name);
		setNumberHome(numberHome);
		setNumberWork(numberWork);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 * @see HCard#name
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the personal address
	 * @see HCard#cleanString(String)
	 */
	public String getAdressHome() {
		return cleanString(adressHome);
	}



	/**
	 * @param adressHome the personal address to set
	 * @see HCard#adressHome
	 */
	public void setAdressHome(String adressHome) {
		this.adressHome = adressHome;
	}



	/**
	 * @return the office address
	 * @see HCard#cleanString(String)
	 */
	public String getAdressWork() {
		return cleanString(adressWork);
	}



	/**
	 * @param adressWork the office address to set
	 * @see HCard#adressWork
	 */
	public void setAdressWork(String adressWork) {
		this.adressWork = adressWork;
	}



	/**
	 * @return the personal number
	 */
	public String getNumberHome() {
		return numberHome;
	}



	/**
	 * @param numberHome the personal number to set
	 * @see HCard#numberHome
	 */
	public void setNumberHome(String numberHome) {
		this.numberHome = numberHome;
	}



	/**
	 * @return the office number 
	 */
	public String getNumberWork() {
		return numberWork;
	}



	/**
	 * @param numberWork the office number to set
	 * @see HCard#numberWork
	 */
	public void setNumberWork(String numberWork) {
		this.numberWork = numberWork;
	}



	/**
	 * @return the email address 
	 */
	public String getMail() {
		return mail;
	}



	/**
	 * @param mail the email address to set
	 * @see HCard#mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Create a file containing an html fragment corresponding to a business card
	 * @param fileName the name of the html file to create
	 * @see HCard#getName()
	 * @see HCard#getAdressHome()
	 * @see HCard#getAdressWork()
	 * @see HCard#getNumberHome()
	 * @see HCard#getNumberWork()
	 * @see HCard#getMail()
	 * @see FileWriter
	 * 
	 */
	public void toHtml(String fileName) {
		File file = new File(fileName);
		FileWriter writer = null;
		String[] strAdrH;
		String[] strAdrW;
		String str = null;

		strAdrH = getAdressHome().split(", ");
		strAdrW = getAdressWork().split(", ");
		try {
			writer = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			str += "<div class=\\\"vcard\\\"> \n \t"
					+ "<span class=\\\"fn\\\">" + getName() + "</span> \n \t"
					+ "<div class=\\\"adr\\\"> \n \t \t"
					+ "<span class=\\\"type\\\">Work</span>: \n \t \t"
					+ "<div class=\\\"street-address\\\">" + strAdrW[0] + "</div> \n \t \t"
					+ "<span class=\\\"locality\\\">" + strAdrW[1] + "</span> \n \t \t"
					+ "<abbr class=\\\"region\\\">" + strAdrW[2] + "</span> \n \t \t"
					+ "<span class=\\\"postal-code\\\">" + strAdrW[3] + "</span> \n \t \t"
					+ "<div class=\\\"country-name\\\">" + strAdrW[4] + "</div> \n \t"
					+ "</div> \n \t"

				  	+ "<div class=\\\"adr\\\"> \n \t \t"
				  	+ "<span class=\\\"type\\\">Home</span>: \n \t \t"
				  	+ "<div class=\\\"street-address\\\">" + strAdrH[0] + "</div> \n \t \t"
				  	+ "<span class=\\\"locality\\\">" + strAdrH[1] + "</span> \n \t \t"
				  	+ "<abbr class=\\\"region\\\">" + strAdrH[2] + "</span> \n \t \t"
				  	+ "<span class=\\\"postal-code\\\">" + strAdrH[3] + "</span> \n \t \t"
				  	+ "<div class=\\\"country-name\\\">" + strAdrH[4] + "</div> \n \t"
				  	+ "</div> \n \t"

					+ "<div class=\\\"tel\\\"> \n \t \t"
					+ "<span class=\\\"type\\\">Work</span>" + getNumberWork() + " \n \t"
					+ "</div> \n \t"

					+ "<div class=\\\"tel\\\"> \n \t \t"
					+ "<span class=\\\"type\\\">Home</span>" + getNumberHome() + " \n \t"
					+ "</div> \n \t"

					+ "<a class=\\\"email\\\" href=\\\"mailto:" + getMail() + "\\\">@</a> \n </div>";
			writer.write(str);
			writer.close();
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
		return getName() + "\n Address (work): " + getAdressWork() + "\n Address (home): " + getAdressHome() + "\n Number (work): "
				+ getNumberWork() + "\n Number (home): " + getNumberHome() + "\n Mail:" + getMail();
	}

}