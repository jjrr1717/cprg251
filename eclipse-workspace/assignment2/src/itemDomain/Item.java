/**
 * Item class is an abstract superclass that contains attributes
 * and methods common to a radio show's playlist. 
 * @author Jocelyn Wegen
 * @version February 3, 2019
 * @throws  
 */
package itemDomain;

public abstract class Item {

	/**
	 * id is an int for an item's id number
	 */
	private int id;
	
	/**
	 * category is a char for the category an item belongs to
	 */
	private char category;

	/**
	 * minutes is an int for the minutes portion of play time
	 */
	private int minutes;
	
	/**
	 * seconds is an int for the seconds portion of play time
	 */
	private int seconds;
	
	/**
	 * audioFile is a string that represents the file type of
	 * an item 
	 */
	private String audioFile;
	
	/**
	 * Default constructor. Accepts no parameters. 
	 */
	public Item() {}
	
	/**
	 * Constructor that accepts five parameters.
	 * @param id is an int for an items it number.
	 * @param category is a char representing what category an
	 * item belongs to.
	 * @param minutes is an int representing the minutes of the
	 * play time for an item.
	 * @param seconds is an int representing the seconds of the
	 * play time for an item.
	 * @param audioFile is a String that represents and item's
	 * file type.
	 */
	public Item(int id, char category, int minutes, int seconds, 
				String audioFile) 
	{
		this.id = id;
		this.category = category;
		this.minutes = minutes;
		this.seconds = seconds;
		this.audioFile = audioFile;
	}
	
	/**
	 * Method to set the id number of an item.
	 * @param id is a int for an item's id number
	 */
	public void setId(int id) 
	{
		this.id = id;
	}
	
	/**
	 * Method to get the id number of an item
	 * @return an int representing the id number of an item
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * Method to set the category type of an item.
	 * @param category is a char representing the category
	 * and item belongs to.
	 */
	public void setCategory(char category) 
	{
		this.category = category;
	}
	
	/**
	 * Method to get the category and item belongs to
	 * @return a char representing the category an item belongs
	 * to
	 */
	public char getCategory() 
	{
		return category;
	}
	
	/**
	 * Method to set the minutes of the play time for an item.
	 * @param minutes is an int that represents the minutes
	 * of the play time for an item.
	 */
	public void setMinutes(int minutes) 
	{
		this.minutes = minutes;
	}
	
	/**
	 * Method to get minutes of the play time for an item.
	 * @return an int that represents the minutes of the
	 * play time for an item.
	 */
	public int getMinutes() 
	{
		return minutes;
	}
	
	/**
	 * Method to set the seconds of the play time for an item.
	 * @param seconds is an int that represents the seconds
	 * of the play time for an item.
	 */
	public void setSeconds(int seconds) 
	{
		this.seconds = seconds;
	}
	
	/**
	 * Method to get seconds of the play time for an item.
	 * @return an int that represents the seconds of the
	 * play time for an item.
	 */
	public int getSeconds() 
	{
		return seconds;
	}
	
	/**
	 * Method to set the audio file type of an item. 
	 * @param audioFile is a String representing the
	 * audio file type of an item.
	 */
	public void setAudioFile(String audioFile) 
	{
		this.audioFile = audioFile;
	}
	
	/**
	 * Method to get the audio file type of an item.
	 * @return a String that represents the audio file type
	 * of an item.
	 */
	public String getAudioFile() 
	{
		return audioFile;
	}
	
	/**
	 * Override the toString Method.
	 * @return a String of attributes of the class
	 */
	public String toString() 
	{
		return ("ID: " + getId() + "\n" + 
				"Category: " + getCategory() + "\n" +
				"Play Time: " + getMinutes() + ":" + 
								getSeconds() + "\n" +
				"Audio File: " + getAudioFile() + "\n");
	}
	
	
}
