/**
 * 
 */
package tests;

import java.io.File;

import files.HCard;

/**
 * @author Yael
 *
 */
public class TestHCard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file = new File("././votrecarte.vcf");
		HCard card = new HCard(file);
		
		System.out.println(card.toString());
		
		System.out.println(card.getName());
		System.out.println(card.getNumberHome());
		System.out.println(card.getNumberWork());
		System.out.println(card.getMail());
		System.out.println(card.getAdressHome());
		System.out.println(card.getAdressWork());
		
		card.toHtml();
		System.out.println("HTML done");
	}

}
