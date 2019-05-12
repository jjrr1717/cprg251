/** Course will create instances of the type of courses
 * in the dog competition
 * @author Jocelyn Wegen
 * @version January 14, 2019
 */
package assignment1;

public class Course {
	//attributes
	/**
	 * name is the name of the course
	 */
	private String name;
	
	/**
	 * maxTime is the maximum time for the course
	 */
	private double maxTime;
	
	//constructors
	/**
	 * Default constructor with name set at "" and
	 * maxTime set at 0
	 */
	public Course() {
		this.name = "";
		this.maxTime = 0;
	}
	
	/**
	 * Constructor
	 * @param name is a String for course name
	 * @param maxTime is a double for course's max time
	 */
	public Course(String name, double maxTime) {
		this.name = name;
		this.maxTime = maxTime;
	}
	
	//getters and setters
	/**
	 * 
	 * @param name is a string to get course name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the course name as a string
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param maxTime is a double to set courses max time
	 */
	public void setMaxTime(double maxTime) {
		this.maxTime = maxTime;
	}
	
	/**
	 * 
	 * @return the course's max time as a double in seconds
	 */
	public double getMaxTime() {
		return maxTime;
	}

}
