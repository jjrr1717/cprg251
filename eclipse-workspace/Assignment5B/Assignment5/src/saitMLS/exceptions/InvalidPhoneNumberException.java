/**
 * An exception that is thrown when 
 * an invalid phone number is entered. 
 * 
 * @author Jocelyn Wegen
 * @version March 16, 2019
 */

package saitMLS.exceptions;

public class InvalidPhoneNumberException extends Exception {
	
	
	/*************Constructors***************************/
	/**
	 * Default Constructor, accepts no parameters
	 */
	public InvalidPhoneNumberException() {}
	
	/**
	 * Constructor to assign message for exception
	 * @param msg a String for the exception message
	 */
	public InvalidPhoneNumberException(String msg) 
	{
		super(msg);
	}
}
