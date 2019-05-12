package assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Competition {
	
	final String DOG_FILE = "res/dogs.txt";
	
	private String newDogId;
	private String newDogName;
	private double newDogTime;
	private int newDogPenalties;
	private char newDogCourseCode;
	private ArrayList<Dog> updatedDogList;
	Scanner keyboard;
	Dog newDog;
	
	

	
	//Constructor
	public Competition(Report report) throws Exception {
		newDog = new Dog();
		keyboard = new Scanner(System.in);
		updatedDogList = new ArrayList<Dog>();
		
	}
	
	public void obtainDogInformation () {
		
	
		boolean correctId = true;
		boolean correctRunTime = true;
		boolean correctPenalties = true;
		boolean correctCourseCode = true;
		boolean stop = false;

		
		System.out.println("\nPlease enter the following information" +
				" to add a dog to the competition.\n");
		
		
		do
		{
			correctId = true;
			int digitCounter = 0;
			System.out.print("Dog's ID (only letters and numbers and contains 3 digits): ");
			newDogId = keyboard.nextLine();
			
			for(int j = 0; j < newDogId.length(); j++) 
			{
				if(Character.isDigit(newDogId.charAt(j))) 
				{
					digitCounter++;
				}
			}
			
			if(digitCounter != 3) 
			{
				correctId = false;
			}
			
			for(int i = 0; i < newDogId.length(); i++) 
			{
				
					
				if(Character.isLetterOrDigit(newDogId.charAt(i)) == false)
				{
					correctId = false;


				}

			}
			
			if(correctId ==false) 
			{
				System.out.print("Invalid Entry. Please try again.\n");

			}
			
		} while(correctId == false);
		

		newDog.setId(newDogId);
		

		System.out.print("Dog's Name: ");
		newDogName = keyboard.next();
		
		newDog.setName(newDogName);
		
		do 
		{
			correctRunTime = true;
			System.out.print("Dog's Running Time: ");
			newDogTime = keyboard.nextDouble();
			
			if(newDogTime < 0) 
			{
				correctRunTime = false;
				System.out.print("Invalid Entry. Please try again.\n");
			}
			

		} while(correctRunTime == false);
		
		newDog.setRunningTime(newDogTime);
		
		do 
		{
			correctPenalties = true;
			System.out.print("Dog's Penality Time: ");
			newDogPenalties = keyboard.nextInt();
			if(newDogPenalties < 0) 
			{
				correctPenalties = false;
				System.out.print("Invalid Entry. Please try again.\n");
			}

			
		}while(correctPenalties == false);
		
		newDog.setPenalties(newDogPenalties);
		
		do 
		{
			correctCourseCode = true;
			System.out.print("Course Code (J for Jumpers, G for Gamblers, T for): " +
					"Titling: ");
			newDogCourseCode = keyboard.next().charAt(0);
			newDogCourseCode = Character.toUpperCase(newDogCourseCode);
			
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
		
		newDog.setCourse(newDogCourseCode);
		
		}
	
	public void addDog(Report report) 
	{
		updatedDogList = report.getDogList();
		updatedDogList.add(newDog);
	}
	
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
		System.out.print("\nDog is Added!\n");
		dogFile.close();
	}
	
	

}
