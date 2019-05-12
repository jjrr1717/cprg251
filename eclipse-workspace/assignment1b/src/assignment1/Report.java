/**
 * Report Class is responsible for creating the report.
 * The user can select what course they want the report on. 
 * Courses include Jumpers, Gamblers, and Titling. The report
 * will print to an output file called report.txt.
 * 
 * @author Jocelyn Wegen
 * @version January 18, 2019
 */

package assignment1;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Report {
	
	
	/*
	 * REPORT_FILE String for output file location res/report.txt
	 */
	final String REPORT_FILE = "res/report.txt";
	
	//Attributes
	
	/**
	 * courseSelection used to determine user's course selection
	 * for report.
	 */
	private char courseSelection;
	
	Competition competition = new Competition();
	
	
	Scanner keyboard = new Scanner(System.in);
	
	/**
	 * Default Constructor. Contains Scanner keyboard, dogList, 
	 * CourseList and createDogList.
	 * @throws Exception for IO
	 */
	public Report( ) throws Exception 
	{

	
	}
	
	
	/**
	 * displayCourseMenu will display the courses
	 * available to view in the report. 
	 */
	private void displayCourseMenu() {

		System.out.println("\nWhat course would you like the report on?\n" +
				"[J] for Jumpers\n" +
				"[G] for Gamblers\n" +
				"[T] for Titling");
		System.out.print("Your Selection: ");
	
	}
	
	/**
	 * get the course selection from the user
	 * @return a char for course selection
	 */
	private char obtainCourseSelection() 
	{
		return keyboard.next().charAt(0);
	}

	/**
	 * this will print a report of the dogs who
	 * participated in a course that the user selected. It
	 * will show the dog's id, name, time, 
	 * penalties, total time, time over and under course
	 * max time.  The report will summarize the winning dog 
	 * and the dog with the most penalties at the bottom
	 * of report.
	 * @throws Exception for IO
	 */
	public void printReport()throws Exception {
		
		
		displayCourseMenu();
		courseSelection = obtainCourseSelection();
		
		//change courseSelection to upperCase to make it case insensitive
		courseSelection = Character.toUpperCase(courseSelection);
		
		//courseName and courseTime is used to hold the course name and time from courseList
		String courseName = "";
		double courseTime = -1;
		
		
		//create a new ArrayList that only contains dogs of specific course
		ArrayList<Dog>sortedDogList = new ArrayList<Dog>();
		
		//To hold the information of winning dog and dog with most penalties
		double winningDogTime = -1;
		String winningDogName = "";
		int highestPenalityTime = 0;  
		String highestPenalityName = ""; 
		
		//date object to insert into report
		Date currentDate = new Date();
		
		//create and get course List from competition 
		competition.createCourseList();
		ArrayList<Course>courseList = competition.getCourseList();
		//obtain the course name and max time based off user's selection
		for(int i = 0; i < courseList.size(); i++) 
		{
			//if courseSelection matches first letter in course file it is that course information
			if(courseSelection == courseList.get(i).getName().charAt(0))
			{
				courseName = courseList.get(i).getName();
				courseTime = courseList.get(i).getMaxTime();
			}
		}
	
		
		//create header for report
		FileWriter openReportFile = new FileWriter(REPORT_FILE, false);
		PrintWriter dogReport = new PrintWriter(openReportFile);
		
		dogReport.printf("%s %28s-CourseTime: %4.1f %33s\r\n", "Report", courseName.toUpperCase(), courseTime, currentDate);
		dogReport.printf("\r\n%s %15s %18s %20s %15s %15s", "ID", "Name", "Running", "Penalty", "Total", "Over/");
		dogReport.printf("\r\n%34s %55s", "Time", "Under");
		
		//create and get dogList from Competition
		competition.createDogList();
		ArrayList<Dog> dogList = competition.getDogList();
		//create the sortedDogList based on user selection
		for(int i = 0; i < dogList.size(); i++) 
		{

			if(courseSelection == dogList.get(i).getCourse()) 
			{
				
				sortedDogList.add(dogList.get(i));
			}
		}
		
		//set the initial winning dog and dog with most penalties to first dog to compare to all other dogs
		winningDogTime = sortedDogList.get(0).getTotalTime();
		winningDogName = sortedDogList.get(0).getName();
		highestPenalityTime = sortedDogList.get(0).getPenalties();  
		highestPenalityName = sortedDogList.get(0).getName();
		
		//print all dogs that participated in user's selected course
		for(int i = 0; i < sortedDogList.size(); i++) 
		{
			dogReport.printf("\r\n%-12s %-16s %-20.1f %-17d %-15.1f %3.1f", sortedDogList.get(i).getId(), sortedDogList.get(i).getName(), 
						sortedDogList.get(i).getRunningTime(), sortedDogList.get(i).getPenalties(), sortedDogList.get(i).getTotalTime(), 
					    (sortedDogList.get(i).getTotalTime() - courseTime));
			
			//calculate winning dog	
			if(sortedDogList.get(i).getTotalTime() < winningDogTime) 
			{
				winningDogTime = sortedDogList.get(i).getTotalTime();
				winningDogName = sortedDogList.get(i).getName();
			}
			
			//calculate dog with the most penalties
			if(sortedDogList.get(i).getPenalties() > highestPenalityTime) 
			{
				highestPenalityTime = sortedDogList.get(i).getPenalties();
				highestPenalityName = sortedDogList.get(i).getName();
			}
				
		}
		
		//print summary of winning dog and dog with most penalties at bottom of report
		dogReport.printf("\r\n\r\nWinning Dog: %25s %10s %.1f", winningDogName, "Time", winningDogTime);
		if(highestPenalityTime > 0) 
		{
			dogReport.printf("\r\nHighest Penality Time: %15s %10s %d", highestPenalityName, "Time",  highestPenalityTime);
		}
		else 
		{
			dogReport.printf("\r\nHighest Penalty Time: %22s", "No Penalities");
		}
		
		System.out.print("\nReport has been created!\n");
		dogReport.close();
		}
}
