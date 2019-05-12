/**
 * AppDriver contains the main method and is used to run
 * the menu/program
 * @author Jocelyn Wegen
 * @version February 3, 2019
 * @throws Exception for IO
 */
package itemManagementDomain;

public class AppDriver {
	public static void main (String [] args) throws Exception 
	{
		//create menu object
		Menu mnu = new Menu();
		//run the main menu
		mnu.runMainMenu();

	}

}
