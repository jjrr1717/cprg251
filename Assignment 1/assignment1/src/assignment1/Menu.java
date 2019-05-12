package assignment1;

import java.util.Scanner;

public class Menu 
{
	
	Scanner keyboard;
	
	public Menu(Report report, Competition competition) throws Exception 
	{
		
		keyboard = new Scanner(System.in);
		runMenu(report, competition);
		
	}
	
	//Operational Methods
	private void displayMenu()
	{
		System.out.println("Welcome to the Dog Competition App!");
		System.out.println("Please make your selection.");
		System.out.println("[1] Create Report\n" + 
						   "[2] Add Dog\n" + 
					       "[3] Exit Program");
		System.out.print("Your Selection: ");
		
			
	}
		
	private int obtainSelection()
	{
		return keyboard.nextInt();
			
	}
		
	public void runMenu(Report report, Competition competition) throws Exception 
	{
		boolean rerun = true;
		while(rerun)
		{
			displayMenu();
			int selection = obtainSelection();
			
			
		
		
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
