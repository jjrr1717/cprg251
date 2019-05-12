package employees;

import java.util.Scanner;

public class Menu {
	
	//Attributes
	Scanner keyboard;
	
	//Constructor
	public Menu(Office office) throws Exception {
		keyboard = new Scanner(System.in);
		runMenu(office);
		
	}
	
	//private methods
	private void displayMenu() {
		System.out.print("Please make your selection.\n");
		System.out.print("[1] Display Employees\n" + 
						 "[2] Add Employee\n" + 
				         "[3] Remove Employee\n" +
						 "[4] Exit\n");
		System.out.print("Your Selection: ");
	}
	
	private int promptUser() {
		return keyboard.nextInt();
	}
	
	private void runMenu (Office office) throws Exception {
		int choice;
		boolean repeat = true;
		
		while(repeat == true) {
			displayMenu();
			choice = promptUser();
		
			switch(choice){
				case 1:
					office.printEmployees();
					break;
				
				case 2:
					office.addEmployee();
					break;
				
				case 3:
					office.removeEmployee();
					break;
					
				
				case 4:
					office.saveEmployees();
					repeat = false;
					break;
				
				default:
					System.out.println("Invalid Input, please try again.");
				
			}
		}
		
	}

}
