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
	
	public InvalidPhoneNumberException() {}
	
	public InvalidPhoneNumberException(String msg) 
	{
		super(msg);
	}
}
