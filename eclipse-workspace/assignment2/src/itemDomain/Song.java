/**
 * The Song class represents a song in the radio stations 
 * playlist. It extends from the superclass Items.
 * @author Jocelyn Wegen
 * @version February 3, 2019
 */
package itemDomain;

public class Song extends Item {
	
	/**
	 * title is a String that represents a song's title
	 */
	private String title;
	
	/**
	 * artistGroup is a String that represents either the
	 * artist or group that performs the song.
	 */
	private String artistGroup;
	
	/**
	 * Default constructor that accepts no parameters
	 */
	public Song() {}
	
	/**
	 * Constructor that accepts seven parameters
	 * @param id is an int for an items it number.
	 * @param category is a char representing what category an
	 * item belongs to.
	 * @param minutes is an int representing the minutes of the
	 * play time for an item.
	 * @param seconds is an int representing the seconds of the
	 * play time for an item.
	 * @param audioFile is a String that represents and item's
	 * file type.
	 * @param title is a String that represents a song's title
	 * @param artistGroup is a String that represents artist or
	 * group that performed the song
	 */
	public Song(int id, char category, int minutes, int seconds, 
				String audioFile, String title, String artistGroup) 
	{
		super(id, category, minutes, seconds, audioFile);
		this.title = title;
		this.artistGroup = artistGroup;
	}
	
	/**
	 * Method to set the title of the song
	 * @param title is a String that represents a song's
	 * title.
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/**
	 * Method to get the song's title
	 * @return a String that represents the song's title
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Method to set the artist or group of the song
	 * @param artistGroup is a String that represents the
	 * artist or group that performed the song.
	 */
	public void setArtistGroup(String artistGroup) 
	{
		this.artistGroup = artistGroup;
	}
	
	/**
	 * Method to get the artist or group of the song.
	 * @return a String that represents the artist or group of
	 * a song.
	 */
	public String getArtistGroup() 
	{
		return artistGroup;
	}
	
	/**
	 * Override the toString Method.
	 *  @return a String of attributes of the class
	 */
	public String toString() 
	{
		return (super.toString() + 
				"Song's Title: " + getTitle() + "\n" +
				"Song's Artist or Group: " + getArtistGroup() + "\n\n");
	}

}
