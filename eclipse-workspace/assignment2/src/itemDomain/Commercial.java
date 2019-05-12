/**
 * Commercial class represents a commercial in the radio 
 * station's playlist. It is a subclass of the Item class.
 * @author Jocelyn Wegen
 * @version February 3, 2019
 */
package itemDomain;

public class Commercial extends Item {
	
	/**
	 * company is a String that represents the company for the
	 * commercial
	 */
	private String company;
	
	/**
	 * Default constructor. Does not accept any parameters.
	 */
	public Commercial() {}
	
	/**
	 * Constructor that accepts six parameters
	 * @param id is an int for an items it number.
	 * @param category is a char representing what category an
	 * item belongs to.
	 * @param minutes is an int representing the minutes of the
	 * play time for an item.
	 * @param seconds is an int representing the seconds of the
	 * play time for an item.
	 * @param audioFile is a String that represents and item's
	 * file type.
	 * @param company is a String representing the company for 
	 * the commercial.
	 */
	public Commercial(int id, char category, int minutes, int seconds, 
			String audioFile, String company) 
	{
		super(id, category, minutes, seconds, audioFile);
		this.company = company;
	}
	
	/**
	 * Method to set the company for the commercial.
	 * @param company is a String representing the company for 
	 * the commercial.
	 */
	public void setCompany(String company) 
	{
		this.company = company;
	}
	
	/**
	 * Method to get the company for the commercial.
	 * @return a String that represents the company for the
	 * commercial.
	 */
	public String getCompany() 
	{
		return company;
	}
	
	/**
	 * Override the toString method.
	 *  @return a String of attributes of the class
	 */
	public String toString() 
	{
		return (super.toString() + 
				"Company: " + getCompany() + "\n\n");
	}

}
