/**
 * competition will handle all other required functions for the
 * AppDriver that does not fit in the Menu or Report class. It 
 * handles adding a dog to the dog file and validates the user
 * input. 
 * 
 * @author Jocelyn Wegen
 * @version January 18, 2019
 */

package assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Competition {
	/**
	 * DOG_FILE is a constant String containing the dog file location
	 */
	final String DOG_FILE = "res/dogs.txt";
	
	/**
	 * String to hold new dog id
	 */
	private String newDogId;
	/**
	 * String to hold new dog name
	 */
	private String newDogName;
	/**
	 * String to hold new dog time
	 */
	private double newDogTime;
	/**
	 * int to hold new dog penalties
	 */
	private int newDogPenalties;
	/**
	 * char to hold new dog course code
	 */
	private char newDogCourseCode;
	/**
	 * ArrayList to hold the new dog information
	 */
	private ArrayList<Dog> updatedDogList;
	Scanner keyboard;
	Dog newDog;
	
	

	
	/**
	 * Constructor needs to pull in information from report
	 * @param report needed to pull information from report
	 * @throws Exception for IO
	 */
	public Competition(Report report) throws Exception {
		newDog = new Dog();
		keyboard = new Scanner(System.in);
		updatedDogList = new ArrayList<Dog>();
		
	}
	
	/**
	 * prompt user to enter new dog information and validate data
	 */
	public void obtainDogInformation () {
		
		/*
		 * variables to control when a user can move on to the next prompt.
		 * They must enter the correct credentials.
		 */
		boolean correctId = true;
		boolean correctRunTime = true;
		boolean correctPenalties = true;
		boolean correctCourseCode = true;
		boolean stop = false;

		//Print out a header
		System.out.println("\nPlease enter the following information" +
				" to add a dog to the competition.\n");
		
		/*ask user to enter dog's id. Must be only letters and digits and contains 3 digits.
		 * 
		 */
		do
		{
			correctId = true;
			int digitCounter = 0;
			System.out.print("Dog's ID (only letters and numbers and contains 3 digits): ");
			newDogId = keyboard.nextLine();
			
			//count number of digits 
			for(int j = 0; j < newDogId.length(); j++) 
			{
				if(Character.isDigit(newDogId.charAt(j))) 
				{
					digitCounter++;
				}
			}
			
			//if number of digits is not 3 they must enter again
			if(digitCounter != 3) 
			{
				correctId = false;
			}
			
			//go through user input
			for(int i = 0; i < newDogId.length(); i++) 
			{
				
				//if character is not a letter or digit they must enter again	
				if(Character.isLetterOrDigit(newDogId.charAt(i)) == false)
				{
					correctId = false;


				}

			}
			
			//print error message if they don't enter correct information
			if(correctId ==false) 
			{
				System.out.print("Invalid Entry. Please try again.\n");

			}
			
		} while(correctId == false);
		
		//set the id for newDog
		newDog.setId(newDogId);
		
		//ask user for dog's name from user input
		System.out.print("Dog's Name: ");
		newDogName = keyboard.next();
		
		//set the name for newDog
		newDog.setName(newDogName);
		
		//ask user for dog's run time until they enter correct information
		do 
		{
			correctRunTime = true;
			System.out.print("Dog's Running Time: ");
			newDogTime = keyboard.nextDouble();
			
			/*if user enters a number less than 0 it will ask user 
			 * to enter number again. Will also print an
			 * an error message
			 */
			if(newDogTime < 0) 
			{
				correctRunTime = false;
				System.out.print("Invalid Entry. Please try again.\n");
			}
			

		} while(correctRunTime == false);
		
		//set the run time for newDog
		newDog.setRunningTime(newDogTime);
		
		//ask user for dog's penalties until they enter correct information
		do 
		{
			correctPenalties = true;
			System.out.print("Dog's Penality Time: ");
			newDogPenalties = keyboard.nextInt();
			
			/*if user enters a number less than 0 it will 
			 * ask user again to enter number. It will
			 * also print an error. 
			 */
			if(newDogPenalties < 0) 
			{
				correctPenalties = false;
				System.out.print("Invalid Entry. Please try again.\n");
			}

			
		}while(correctPenalties == false);
		
		//set penalties to newDog
		newDog.setPenalties(newDogPenalties);
		
		//ask user for course code until they enter correct information
		do 
		{
			correctCourseCode = true;
			System.out.print("Course Code (J for Jumpers, G for Gamblers, T for " +
					"Titling: ");
			newDogCourseCode = keyboard.next().charAt(0);
			
			//Change char to uppercase to make it case insensitive
			newDogCourseCode = Character.toUpperCase(newDogCourseCode);
			
			/*if does not enter J, j, G, g, T, or t it will ask
			 * user again to enter the course code. 
			 * It will also print an error message. 
			 */
			if(newDogCourseCode == 'J') 
			{
				correctCourseCode = true;
			}
			else if(newDogCourseCode == 'G') 
			{
				correctCourseCode = true;
			}
			else if(newDogCourseCode == 'T') 
			{
				correctCourseCode = true;
				
			}
			else 
			{
				correctCourseCode = false;
				System.out.print("Invalid Entry. Please try again.\n");
			}
			
		} while(correctCourseCode == false);
		
		//set course code to newDog
		newDog.setCourse(newDogCourseCode);
		
		}
	
	/**
	 * add existing dogs to updatedDogList and add newDog
	 * @param report to obtain information from report class
	 */
	public void addDog(Report report) 
	{
		updatedDogList = report.getDogList();
		updatedDogList.add(newDog);
	}
	
	/**
	 * save the updatedDogList to the file, so file contains
	 * all the dogs
	 * @param report to obtain information from report class
	 * @throws Exception for IO
	 */
	public void saveNewDogList (Report report) throws Exception 
	{
		FileWriter openDogFile = new FileWriter(DOG_FILE, false);
		PrintWriter dogFile = new PrintWriter(openDogFile);
		
		for(Dog theDog: updatedDogList) 
		{
			dogFile.print(theDog.getId() + " ");
			dogFile.print(theDog.getName() + " ");
			dogFile.print(theDog.getRunningTime() + " ");
			dogFile.print(theDog.getPenalties() + " ");
			dogFile.print(theDog.getCourse() + " \r\n");

			
		}
		
		//Display message that dog has been added to file
		System.out.print("\nDog is Added!\n");
		
		//close the dog file
		dogFile.close();
	}
}
