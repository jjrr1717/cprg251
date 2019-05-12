/**
 * An interface that interacts with a database. Will contain 
 * all the abstract methods needed for the database
 * interaction. 
 * @author Jocelyn Wegen
 * @version March 16, 2019
 */

package saitMLS.persistance;

import java.util.List;

public interface Broker {

	/**
	 * An abstract method that permanently writes
	 * the new or modified information to the
	 * database
	 * @param o is an Object that holds the information to be added 
	 * to the database.
	 * @return a boolean indicating if the modification or added object
	 * has been added to the database correctly. 
	 */
	public boolean persist(Object o);
	
	/**
	 * An abstract method that removes an object from the database. 
	 * @param o is the Object that should be removed from the database
	 * @return a boolean indicating if the removal of the object
	 * was successful or not. 
	 */
	public boolean remove(Object o);
	
	/**
	 * An abstract method that searches the database for a specified
	 * object using a specific criteria. 
	 * @param o is the Object(s) that are being searched for that 
	 * matches the criteria given. 
	 * @return a List of all the Objects that match the criteria, to
	 * be displayed in the front-end GUI. 
	 */
	public List search(String searchItem, String type);
	
	/**
	 * An abstract method used to save all the changes made
	 * to the database. Releases resources allocated to the broker. 
	 */
	public void closeBroker();
	

}
