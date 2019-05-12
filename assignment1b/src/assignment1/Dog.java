/**Dog will create instances of dogs competing in the
 * dog competition. An instance of this class will 
 * contain the dog's ID, Name, RunningTime, 
 * Penalties, and Course. Total Time and the amount
 * of time over or under course max time will also
 * be available. 
 * @author Jocelyn Wegen
 * @version January 14, 2019
 */
package assignment1;

public class Dog {
	//Create course instance to use in getOverUnder method
	Course theCourse = new Course();
	
	//attributes
	
	/**
	 * id is the id of the dog
	 */
	private String id;
	
	/**
	 * name is the name of the dog
	 */
	private String name;
	
	/**
	 * runTime is the running time in seconds
	 */
	private double runningTime;
	
	/**
	 * penalties is the dog's total penalties in seconds
	 */
	private int penalties;
	
	/**
	 * course is the course code the dog participated in
	 */
	private char course;
	
	//constructors
	/**
	 * Default Constructor. Accepts no parameters
	 */
	public Dog() {
		this.id = "";
		this.name = "";
		this.runningTime = 0;
		this.penalties = 0;
		this.course = 'z';
		
	}
	
	/**
	 * Constructor assigns id, name, runningTime, penalties, and course
	 * of a dog.
	 * @param id is a String for id of dog
	 * @param name is a String for name of dog
	 * @param runningTime is a double for dog's running time in seconds
	 * @param penalties is an int for dog's total penalty in seconds
	 * @param course is a char for course code
	 */
	public Dog(String id, String name, double runningTime, 
			int penalties, char course) {
		this.id = id;
		this.name = name;
		this.runningTime = runningTime;
		this.penalties = penalties;
		this.course = course;
	}
	
	//getters and setters
	
	/**
	 * Set id for dog
	 * @param id is a string for dog's id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get id for dog
	 * @return the dog's id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set name for dog
	 * @param name is a string of the dog's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get name for dog
	 * @return the dog's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set runningTime for dog
	 * @param runningTime is a double of running time in seconds
	 */
	public void setRunningTime(double runningTime) {
		this.runningTime = runningTime;
	}
	
	/**
	 * Get runningTime for dog
	 * @return the dog's running time in seconds
	 */
	public double getRunningTime() {
		return runningTime;
	}
	
	/**
	 * Set Penalties for dog
	 * @param penalties is an int of the dog's penalties in seconds
	 */
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	
	/**
	 * Get penalties for dog
	 * @return the dog's penalties in seconds
	 */
	public int getPenalties() {
		return penalties;
	}
	
	/**
	 * Set Course for Dog
	 * @param course is a char of the course code
	 */
	public void setCourse(char course) {
		this.course = course;
	}
	
	/**
	 * Get course for dog
	 * @return the course code
	 */
	public char getCourse() {
		return course;
	}
	
	//methods
	/**
	 * Get total time for dog
	 * @return the dog's total time in seconds
	 */
	public double getTotalTime() {
		return getRunningTime() + getPenalties(); 
	}
	
	/**
	 * Get difference between dog's total time and course's max time
	 * @return the difference between the total course time and the dog's time
	 */
	public double getOverOrUnder() {
		return (getTotalTime() - theCourse.getMaxTime());
	}
	
	/**
	 * Create formatted toString method
	 * @return a formated string of the dog class
	 */
	public String toString() {
		String theString = "ID: " + getId() + 
				"\nName: " + getName() + 
				"\nRun Time: " + getRunningTime() + 
				"\nTotal Penalties: " + getPenalties() + 
				"\nCourse Code: " + getCourse() + "\n\n";
		
		return theString;
	}

}
