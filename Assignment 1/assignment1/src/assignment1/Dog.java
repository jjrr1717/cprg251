/**Dog will create instances of dogs competing in the
 * dog competition.
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
	 * penalties is the dog's total penalty in seonds
	 */
	private int penalties;
	
	/**
	 * course is the course code the dog participated in
	 */
	private char course;
	
	//constructors
	/**
	 * Default Constructor
	 */
	public Dog() {
		this.id = "";
		this.name = "";
		this.runningTime = 0;
		this.penalties = 0;
		this.course = 'z';
		
	}
	
	/**
	 * Constructor
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
	 * 
	 * @param id is a string for dog's id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the dog's id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @param name is a string of the dog's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the dog's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param runningTime is a double of running time in seconds
	 */
	public void setRunningTime(double runningTime) {
		this.runningTime = runningTime;
	}
	
	/**
	 * 
	 * @return the dog's running time in seconds
	 */
	public double getRunningTime() {
		return runningTime;
	}
	
	/**
	 * 
	 * @param penalties is an int of the dog's penalties in seconds
	 */
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	
	/**
	 * 
	 * @return the dog's penalties in seconds
	 */
	public int getPenalties() {
		return penalties;
	}
	
	/**
	 * 
	 * @param course is a char of the course code
	 */
	public void setCourse(char course) {
		this.course = course;
	}
	
	/**
	 * 
	 * @return the course code
	 */
	public char getCourse() {
		return course;
	}
	
	//methods
	/**
	 * 
	 * @return the dog's total time in seconds
	 */
	public double getTotalTime() {
		return getRunningTime() + getPenalties(); 
	}
	
	/**
	 * 
	 * @return the difference between the total course time and the dog's time
	 */
	public double getOverOrUnder() {
		return (getTotalTime() - theCourse.getMaxTime());
	}
	
	public String toString() {
		String theString = "ID: " + getId() + 
				"\nName: " + getName() + 
				"\nRun Time: " + getRunningTime() + 
				"\nTotal Penalties: " + getPenalties() + 
				"\nCourse Code: " + getCourse() + "\n\n";
		
		return theString;
	}

}
