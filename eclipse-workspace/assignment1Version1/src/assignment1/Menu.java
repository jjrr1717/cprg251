/**
 * The Menu class is responsible to creating 
 * the initial menu for the competition AppDriver.
 * It creates the display menu, obtains the 
 * user's selection, and runs the menu for the
 * AppDriver. 
 * 
 * @author Jocelyn Wegen
 * @version January 18, 2019
 */
package assignment1;

import java.util.Scanner;

public class Menu 
{
	//Scanner for keyboard input
	Scanner keyboard;
	
	/**
	 * Constructor to assign the report class and the 
	 * competition class
	 * @param report is a class containing the dog report
	 * @param competition is a class containing other features of the App
	 * @throws Exception for IO
	 */
	public Menu(Report report, Competition competition) throws Exception 
	{
		//need Scanner for input and runMenu
		keyboard = new Scanner(System.in);
		runMenu(report, competition);
		
	}
	

	/**
	 * DisplayMenu creates the menu for the App
	 * It asks the user to select create report,
	 * add dog, or exit program.
	 */
	private void displayMenu()
	{
		//Print out the menu to console
		System.out.println("Welcome to the Dog Competition App!");
		System.out.println("Please make your selection.");
		System.out.println("[1] Create Report\n" + 
						   "[2] Add Dog\n" + 
					       "[3] Exit Program");
		System.out.print("Your Selection: ");
		
			
	}
	
	/**
	 * obtainSelection asks the user to select which option they
	 * want from the menu.  Selection 1 prints the report, 
	 * 2 adds a dog to the file, and 3 exits the program.
	 * @return is an int of the user's selection
	 */
	private int obtainSelection()
	{
		//return the user's selection
		return keyboard.nextInt();
			
	}
	
	/**
	 * runMenu is used to perform the tasks based on the user's
	 * selection from obtainSelection. 	
	 * @param report is a class containing the dog report
	 * @param competition is a class containing other features of the App
	 * @throws Exception for IO
	 */
	public void runMenu(Report report, Competition competition) throws Exception 
	{
		boolean rerun = true;
		
		/*
		 * while user does not enter 3 it will continue displaying menu
		 * and obtain the user's selection.
		 */
		while(rerun)
		{
			displayMenu();
			int selection = obtainSelection();
			
			/* 
			 * Case 1 will print the report.
			 * Case 2 will add a dog to the file
			 * Case 3 will exit the program
			 */
			switch(selection) 
			{
				case 1:
					report.printReport();
					break;
					
				case 2:
					competition.obtainDogInformation();
					competition.addDog(report);
					competition.saveNewDogList(report);
					break;
						
				case 3:
					rerun = false;
					break;
						
				default:
					System.out.println("Please enter a valid option.\n");
			}
			System.out.println();
					
		}
	}
}
