/**
 * TalkShow class represents a talk show for the radio station.
 * It extends from the Item class.
 * @author Jocelyn Wegen
 * @version February 3, 2019
 */
package itemDomain;

public class TalkShow extends Item {
	
	/**
	 * title is a String representing the title of the 
	 * talk show.
	 */
	private String title;
	
	/**
	 * host is a String representing the host of the talk 
	 * show.
	 */
	private String host;
	
	/**
	 * Default constructor. Accepts no parameters.
	 */
	public TalkShow() {}
	
	/**
	 * Constructor that accepts seven parameters.
	 * @param id is an int for an items it number.
	 * @param category is a char representing what category an
	 * item belongs to.
	 * @param minutes is an int representing the minutes of the
	 * play time for an item.
	 * @param seconds is an int representing the seconds of the
	 * play time for an item.
	 * @param audioFile is a String that represents and item's
	 * file type.
	 * @param title is a String representing the title of the
	 * talk show.
	 * @param host is a String representing the host of the 
	 * talk show.
	 */
	public TalkShow(int id, char category, int minutes, int seconds, 
			String audioFile, String title, String host) 
	{
		super(id, category, minutes, seconds, audioFile);
		this.title = title;
		this.host = host;
	}
	
	/**
	 * Method to set the title of the talk show
	 * @param title is a String that represents the talk show's
	 * title.
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * Method to get the talk show's title
	 * @return a String that represents the talk show's title
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Method to set the host of the talk show.
	 * @param host is a String that represents the
	 * host of the talk show.
	 */
	public void setHost(String host) 
	{
		this.host= host;
	}
	
	/**
	 * Method to get the host of the talk show.
	 * @return a String that represents the host of a talk show.
	 */
	public String getHost() 
	{
		return host;
	}
	
	/**
	 * Override the toString Method.
	 *  @return a String of attributes of the class
	 */
	public String toString() 
	{
		return (super.toString() + 
				"Talk Show's Title: " + getTitle() + "\n\n");
	}

}
