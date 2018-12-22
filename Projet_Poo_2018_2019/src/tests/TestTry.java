package tests;

import java.io.File;

import files.HCalendarTry;

public class TestTry {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("././votrecalendrier.ics");
		HCalendarTry calendar = new HCalendarTry(file);
		
		System.out.println(calendar.toString());
		
		

	}

}
