package employees;

import java.util.Scanner;

public class AppDriver {
	public static void main (String [] args) throws Exception {
		Office myOffice = new Office();
		Menu menu = new Menu(myOffice);	
	}
		

}
