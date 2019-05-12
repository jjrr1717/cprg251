/**
 * Exception class that is thrown when number of bathrooms
 * is invalid. It should be in decimal format of either
 * .0 or .5. 
 * @author Jocelyn Wegen
 * @version April 2, 2019
 */
package saitMLS.exceptions;

public class InvalidNumberOfBathroomsException extends Exception {

	/**
	 * Default Constructor, accepts no parameters
	 */
	public InvalidNumberOfBathroomsException() {}
	
	/**
	 * Constructor for exception message
	 * @param msg a string for exception message
	 */
	public InvalidNumberOfBathroomsException(String msg) {
		super(msg);
	}
}
