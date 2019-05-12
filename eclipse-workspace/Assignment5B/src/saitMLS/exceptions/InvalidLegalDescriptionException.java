/**
 * Exception class that is thrown when the legal
 * description is invalid. 
 * @author Jocelyn Wegen
 * @version April 2, 2019
 */
package saitMLS.exceptions;

public class InvalidLegalDescriptionException extends Exception {
	/**
	 * Default Constructor, accepts no parameters
	 */
	public InvalidLegalDescriptionException() {}
	
	/**
	 * Constructor for exception message
	 * @param msg a String for exception message
	 */
	public InvalidLegalDescriptionException(String msg) {
		super(msg);
	}

}
