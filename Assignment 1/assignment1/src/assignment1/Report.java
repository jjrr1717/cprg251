package assignment1;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Report {
	
	final String DOG_FILE = "res/dogs.txt";
	final String COURSE_FILE = "res/courses.txt";
	final String REPORT_FILE = "res/report.txt";
	
	//Attributes
	private ArrayList<Dog> dogList;
	private ArrayList<Course> courseList;
	char courseSelection;
	
	
	Scanner keyboard;
	
	public Report( ) throws Exception {
		keyboard = new Scanner(System.in);
		dogList = new ArrayList<Dog>();
		courseList = new ArrayList<Course>();
		createDogList();
		


		
		
		
	}
	
	
	public void createDogList() throws Exception {
		//open dog.txt
		File dogFile = new File(DOG_FILE);
		Scanner scanDogFile = new Scanner(dogFile);

		while(scanDogFile.hasNext()) {
			String dogId = scanDogFile.next();
			String dogName = scanDogFile.next();
			double dogRunningTime = scanDogFile.nextDouble();
			int dogPenalties = scanDogFile.nextInt();
			char dogCourseCode = scanDogFile.next().charAt(0);
			
		
			//create the dog instance
			Dog theDog = new Dog(dogId, dogName, dogRunningTime, 
					dogPenalties, dogCourseCode);

			//the the dog to the dogList
			dogList.add(theDog);
			
		}
		scanDogFile.close();
	}
	

	
	private void createCourseList() throws Exception {
		//open course.txt
		File courseFile = new File(COURSE_FILE);
		Scanner scanCourseFile = new Scanner(courseFile);
		

		while(scanCourseFile.hasNext()) {
			String courseName = scanCourseFile.next();
			double courseMaxTime = scanCourseFile.nextDouble();
			
			//create the course instance
			Course theCourse = new Course(courseName, courseMaxTime);
			
			//add the course to the courseList
			courseList.add(theCourse);	
		}
		scanCourseFile.close();
	}
	
	public ArrayList<Dog> getDogList() 
	{
		return dogList; 
	}
	
	private void displayCourseMenu() {

		System.out.println("\nWhat course would you like the report on?\n" +
				"[J] for Jumpers\n" +
				"[G] for Gamblers\n" +
				"[T] for Titling");
		System.out.print("Your Selection: ");
	
	}
	
	private char obtainCourseSelection() 
	{
		return keyboard.next().charAt(0);
	}


	public void printReport()throws Exception {
		
		displayCourseMenu();
		courseSelection = obtainCourseSelection();
		courseSelection = Character.toUpperCase(courseSelection);
		String courseName = "";
		double courseTime = -1;
		createDogList();
		createCourseList();
		ArrayList<Dog>sortedDogList = new ArrayList<Dog>();
		double winningDogTime = -1;
		String winningDogName = "";
		int highestPenalityTime = 0;  
		String highestPenalityName = ""; 
		
		

		
		//date object to insert into report
		Date currentDate = new Date();
		
		for(int i = 0; i < courseList.size(); i++) 
		{
			if(courseSelection == courseList.get(i).getName().charAt(0))
			{
				courseName = courseList.get(i).getName();
				courseTime = courseList.get(i).getMaxTime();
			}
		}
	
		
		//create output report
		PrintWriter dogReport = new PrintWriter(REPORT_FILE);
		dogReport.printf("%s %28s-CourseTime: %4.1f %33s\r\n", "Report", courseName.toUpperCase(), courseTime, currentDate);
		dogReport.printf("\r\n%s %15s %18s %20s %15s %15s", "ID", "Name", "Running", "Penalty", "Total", "Over/");
		dogReport.printf("\r\n%34s %55s", "Time", "Under");
		

		for(int i = 0; i < dogList.size(); i++) 
		{

			if(courseSelection == dogList.get(i).getCourse()) 
			{
				
				sortedDogList.add(dogList.get(i));
			}
		}
		
		winningDogTime = sortedDogList.get(0).getTotalTime();
		winningDogName = sortedDogList.get(0).getName();
		highestPenalityTime = sortedDogList.get(0).getPenalties();  
		highestPenalityName = sortedDogList.get(0).getName();
		
		for(int i = 0; i < sortedDogList.size(); i++) 
		{
			dogReport.printf("\r\n%-12s %-16s %-20.1f %-17d %-15.1f %3.1f", sortedDogList.get(i).getId(), sortedDogList.get(i).getName(), 
						sortedDogList.get(i).getRunningTime(), sortedDogList.get(i).getPenalties(), sortedDogList.get(i).getTotalTime(), 
					    (sortedDogList.get(i).getTotalTime() - courseTime));
			
				
			if(sortedDogList.get(i).getTotalTime() < winningDogTime) 
			{
				winningDogTime = sortedDogList.get(i).getTotalTime();
				winningDogName = sortedDogList.get(i).getName();
			}
				
			if(sortedDogList.get(i).getPenalties() > highestPenalityTime) 
			{
				highestPenalityTime = sortedDogList.get(i).getPenalties();
				highestPenalityName = sortedDogList.get(i).getName();
			}
				
		}
		
		
		dogReport.printf("\r\n\r\nWinning Dog: %25s %10s %.1f", winningDogName, "Time", winningDogTime);
		if(highestPenalityTime > 0) 
		{
			dogReport.printf("\r\nHighest Penality Time: %15s %10s %d", highestPenalityName, "Time",  highestPenalityTime);
		}
		else 
		{
			dogReport.printf("\r\nHighest Penality Time: %22s", "No Penalities");
		}
		
		System.out.print("\nReport has been created!");
		dogReport.close();
		}
}
