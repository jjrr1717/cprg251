/**
 * An exception that is thrown when
 * an invalid postal code is entered. Must be
 * in format T5B 4L7. 
 * 
 * @author Jocelyn Wegen
 * @version March 16, 2019
 */

package saitMLS.exceptions;

public class InvalidPostalCodeException extends Exception {
	
	/*************Constructors***************************/
	public InvalidPostalCodeException() {}
	
	public InvalidPostalCodeException(String msg) 
	{
		super(msg);
	}

}
