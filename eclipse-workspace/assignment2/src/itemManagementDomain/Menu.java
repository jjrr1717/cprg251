/**
 * The Menu class manages the menu's for WKRP Radio Station's
 * PlayList App. 
 * @author Jocelyn Wegen
 * @version February 3, 2019
 */
package itemManagementDomain;

import java.util.Scanner;

public class Menu {
	/**
	 * mainSelection is an int to select the main menu options
	 */
	private int mainSelection;
	
	/**
	 * printSelection is an int to select what should be 
	 * printed
	 */
	private int printSelection;
	

	 /**
	  * categorySelection is a char to hold the user's
	  * category selection.
	  */
	private char categorySelection;
	
	Scanner input = new Scanner(System.in);
	PlayListManager plm = new PlayListManager();
	
	/**
	 * Default constructor. Accepts no parameters.
	 * @throws Exception for IO
	 */
	public Menu() throws Exception 
	{
		//create the itemList for the menu
		plm.createItemList();
	}
	
	/**
	 * displayMainMenu is a method to show the main method in the
	 * console. It's return type is void. 
	 */
	private void displayMainMenu() 
	{
		//print out options for main menu
		System.out.println("Welcome to WKRP Radio Station's" +
						   " Playlist App!");
		System.out.println("Please make a selection.");
		System.out.print("[1]Add an item\n" + 
						 "[2]Search for an item by id\n" +
						 "[3]Remove an item by id\n" +
						 "[4]Create a random playlist\n" + 
						 "[5]Print\n" +
						 "[6]Exit\n");
		
	}

	/**
	 * displayPrintSubMenu is a method to show the print menu in the
	 * console.  It's return method is void.
	 */
	private void displayPrintSubMenu() 
	{
		//print out menu for the print sub menu
		System.out.println("Please select what you want to print.");
		System.out.print("[1]Complete list\n" + 
				 		 "[2]Songs\n" +
				 		 "[3]Talk Shows\n" +
				 		 "[4]Commercials\n" + 
				 		 "[5]Return to main menu\n");
	}
	
	/**
	 * displaySongSubMenu will show the song menu in the console.
	 * It's return type is void. 
	 */
	private void displaySongSubMenu() 
	{
		//print out menu for song sub menu
		System.out.println("Please select what song category " +
						 " you want to print.");
		System.out.print("[L]Classical\n" + 
		 		 		 "[C]Country\n" +
		 		 		 "[R]Rock\n" +
		 		 		 "[P]Pop\n" + 
						 "[A]Alternative\n" + 
		 		 		 "[E]Return to print menu\n");
	}
	
	/**
	 * displayTalkShowSubMenu will show the talk show menu in the
	 * console. It's return type is void.
	 */
	private void displayTalkShowSubMenu() 
	{
		//print out menu for talk show sub menu
		System.out.println("Please select what talk show category " +
						 " you want to print.");
		System.out.print("[S]Science\n" + 
		 		 		 "[P]Politics\n" +
		 		 		 "[M]Miscellaneous\n" +
		 		 		 "[E]Return to print menu\n");
	}
	
	/**
	 * displayCommericalSubMenu will show the commercial menu in the 
	 * console. It's return type is void.
	 */
	private void displayCommercialSubMenu() 
	{
		//print out menu for commercial sub menu
		System.out.println("Please select what commercial category " +
						 " you want to print.");
		System.out.print("[V]Vehicle dealers\n" + 
		 		 		 "[H]Household products\n" +
		 		 		 "[C]Computers\n" +
		 		 		 "[M]Miscellaneous\n" +
		 		 		 "[E]Return to print menu\n");
	}
	
	/**
	 * getUserIntSelection is a method to obtain the user's selection
	 * for the menu's that ask for an int.
	 * @return an int representing the user's choice on a menu.
	 */
	private int getUserIntSelection() 
	{
		int choice;
		System.out.print("Selection: ");
		choice = input.nextInt();
		System.out.println(); //print extra line for appearance
		return choice;
	}
	
	/**
	 * getUserCharSelection is a method to obtain the user's selection
	 * for the menu's that ask for a char. 
	 * @return a char that represents the user's choice on a menu.
	 */
	private char getUserCharSelection() 
	{
		char choice;
		System.out.print("Selection: ");
		choice = input.next().charAt(0);
		System.out.println(); //print extra line for appearance
		return choice;
	}
	

	
	/**
	 * runPrintSubMenu is a method to run the print menu to the 
	 * console. The return type is void. 
	 * @throws Exception  for IO
	 */
	private void runPrintSubMenu() throws Exception 
	{
		printSelection = -1;
		while(printSelection != 5) 
		{
			//show print menu
			displayPrintSubMenu();
			//obtain user selection
			printSelection = getUserIntSelection();
			
			switch(printSelection) 
			{
				case 1:
					plm.printFullList();
					break;
				case 2:
					runSongSubMenu();
					break;
				case 3:
					runTalkShowSubMenu();
					break;
				case 4:
					runCommercialSubMenu();
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid Entry! " +
									   "Please try again.");
						
			}
		}
	}
	
	/**
	 * runSongSubMenu is a method to run the song menu to the 
	 * console. It's return type is void. 
	 * @throws Exception 
	 */
	private void runSongSubMenu() throws Exception 
	{
		categorySelection = ' ';
		
		//user can exit Song sub menu by entering 'E'
		while(categorySelection != 'E') 
		{
			//print Song sub menu
			displaySongSubMenu();
			//get user selection
			categorySelection = getUserCharSelection();
			//make user selection case insensitive
			categorySelection = Character.toUpperCase(categorySelection);
			
			switch(categorySelection) 
			{
				case 'L':
				case 'C':
				case 'R':
				case 'P':
				case 'A':
					plm.printFilteredSongList(categorySelection);
					break;
				case 'E':
					break;
				default:
					System.out.println("Invalid Entry! " +
							   "Please try again.");
			}
		}
		
		
	}
	
	/**
	 * runTalkShowSubMenu is a method to run the talk show menu
	 * to the console. It's return type is void. 
	 * @throws Exception 
	 */
	private void runTalkShowSubMenu() throws Exception 
	{
		categorySelection = ' ';
		
		//user can exit talk show sub menu by entering 'E'
		while(categorySelection != 'E') 
		{	
			//print talk show sub menu
			displayTalkShowSubMenu();
			//get user selection
			categorySelection = getUserCharSelection();
			//make user selection case insensitive
			categorySelection = Character.toUpperCase(categorySelection);
			
			switch(categorySelection) 
			{
				case 'S':
				case 'P':
				case 'M':
					plm.printFilteredTalkShowList(categorySelection);
					break;
				case 'E':
					break;
				default:
					System.out.println("Invalid Entry! " +
							   "Please try again.");
			}
		}
	}
	
	/**
	 * runCommercialSubMenu is a method to run the commercial menu to
	 * the console. It's return type is void. 
	 * @throws Exception for IO
	 */
	private void runCommercialSubMenu() throws Exception
	{
		categorySelection = ' ';
		
		//user can enter 'E' to exit commercial sub menu
		while(categorySelection != 'E') 
		{
			//print commercial sub menu
			displayCommercialSubMenu();
			//get user selection
			categorySelection = getUserCharSelection();
			//make user selection case insensitive
			categorySelection = Character.toUpperCase(categorySelection);
			
			switch(categorySelection) 
			{
				case 'V':
				case 'H':
				case 'M':
				case 'C':
					plm.printFilteredCommercialList(categorySelection);
					break;
				case 'E':
					break;
				default:
					System.out.println("Invalid Entry! " +
							   "Please try again.");
			}
		}
	}
	
	/**
	 * runMainMenu is a method to run the main menu to the console. 
	 * It's return type is void. 
	 * @throws Exception for IO
	 */
	public void runMainMenu() throws Exception
	{
		mainSelection = -1;
		//user can enter 6 to exit program and save changes
		while(mainSelection != 6) 
		{
			//print main menu
			displayMainMenu();
			//get user selection
			mainSelection = getUserIntSelection();
			
			switch(mainSelection) 
			{
				case 1:
					plm.addItem();
					break;
				case 2:
					plm.showSearchedId();
					break;
				case 3:
					plm.deleteItem();
					break;
				case 4:
					System.out.println(plm.getRandList());
					plm.createRandPlayListFile();
					System.out.println(); //print extra line for appearance
					break;
				case 5:
					runPrintSubMenu();
					break;
				case 6:
					System.out.println("Changes Saved!");
					plm.saveChanges();
					break;
				default:
					System.out.println("Invalid Entry! " +
									   "Please try again.");
			}
		}	
	}
}
