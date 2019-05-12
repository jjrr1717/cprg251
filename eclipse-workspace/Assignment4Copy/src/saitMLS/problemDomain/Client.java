/**
 * Client represents a Client for SAITMLS.  
 * It will contain all the attributes
 * contained in the database for a Client.  
 * It will hold the Client id, last name, 
 * first name,address, postal code, 
 * phone number, client type and if the record 
 * is active or not.  
 * 
 * @author Jocelyn Wegen
 * @version March 16, 2019
 */

package saitMLS.problemDomain;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;

public class Client {
	
	//Attrbutes
	
	/**
	 * A boolean indicating if the object
	 * is logically deleted or not.
	 */
	private boolean active;
	
	/**
	 * A long holding the Client's id.
	 */
	private long clientId;
	
	/**
	 * A String holding the Client's first name.
	 * 20 characters.
	 */
	private String firstName;
	
	/**
	 * A String holding the Client's last name.
	 * 20 characters.
	 */
	private String lastName;
	
	/**
	 * A String holding the Client's address.
	 * 50 characters. 
	 */
	private String address;
	
	/**
	 * A String holding the Client's postal code.
	 * 7 characters.
	 */
	private String postalCode;
	
	/**
	 * A String holding the Client's phoneNumber.
	 * 12 characters
	 */
	private String phoneNumber;
	
	/**
	 * A char holding the Client's type
	 */
	private char clientType;
	
	/**
	 * A long for serial version UID
	 */
	private long uId;

	/**
	 * An int representing how many bytes
	 * the client object will be in a
	 * RandomAccessFile
	 */
	public static final int SIZE = 130;
	
	/*************Constructors***************************/
	


	/**
	 * Default constructor.  Does not accept any
	 * parameters.
	 */
	public Client() {}
	
	/**
	 * Constructors that accepts 7 parameters
	 * to create the client. 
	 * @param clientId is a long for the client's id.
	 * @param firstName is a String for the client's first name.
	 * @param lastName is a String for the client's last name.
	 * @param address is a String for the client's address.
	 * @param postalCode is a String for the client's postal code.
	 * @param phoneNumber is a String for the client's phone #.
	 * @param clientType is a char for the client's type.
	 * R = residential, C = commercial and B = both.
	 */
	public Client(long clientId,
				  String firstName,
				  String lastName, 
				  String address,
				  String postalCode,
				  String phoneNumber,
				  char clientType) 
	{
		setClientId(clientId);
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.clientType = clientType;
		//set this client as active
		setActive(true);
	}
	
	/**
	 * Constructor that accepts a line (String)
	 * that contains the attributes needed
	 * to create a client. 
	 * The format must be in:
	 * "id firstName lastName address postalCode phoneNumber clientType"
	 * @param line a String representing the Client information
	 */
	public Client(String line) 
	{
		//split up the line and put into String Array
		String [] theLine = line.split(" ");
		
		/*
		 * take the splitted line and assign each 
		 * item to an attribute of the client.
		 */
		setClientId(Integer.parseInt(theLine[0]));
		this.firstName = theLine[1];
		this.lastName = theLine[2];
		this.address = theLine[3];
		this.postalCode = theLine[4];
		this.phoneNumber = theLine[5];
		this.clientType = theLine[6].charAt(0);
		
		//set this client as active
		setActive(true);
		
		
	}
	
	/*************Public Methods*************************/
	
	/**
	 * Method to display attributes in desired format.
	 */
	public String toString() 
	{
		return (getClientId() + " " +
				getFirstName() + " " +
				getLastName() + " " + 
				getAddress() + " " + 
				getPostalCode() + " " + 
				getPhoneNumber() + " " + 
				getClientType());
	}
	
	/**
	 * Method to compare two clients. If they are 
	 * the same client then equals will return true. 
	 * If they are not the same client then equals will 
	 * return false. 
	 * @param c is a Client object to be compared
	 * @return A boolean. If the client's have the same
	 * id number than true is returned.  If the clients 
	 * have a different id then false is returned. 
	 */
	public boolean equals(Client c) 
	{
		//variable to hold boolean
		boolean areEqual = false;
		
		/*
		 * If the clientId and client's firstname and lastname are
		 * equal then the clients are the same. If these do not match
		 * then the clients are not the same.  
		 */
		if(c.getClientId() == getClientId() || 
				(c.getFirstName().equalsIgnoreCase(getFirstName()) && c.getLastName().equalsIgnoreCase(getLastName()))) 
		{
			areEqual = true;
		}
		else 
		{
			areEqual = false;
		}
		
		return areEqual;
	}
	
	/*************Private Methods************************/
	
	/**
	 * Method to validate the postal code. Must be in 
	 * format A9A 9A9. Will throw an exception if it 
	 * is not in this format. 
	 * @param pc is the postal code as a String
	 * @throws InvalidPostalCodeException when the 
	 * format is incorrect. 
	 */
	private void validatePostalCode(String pc)  throws InvalidPostalCodeException
	{
		//put the postal code (pc) into an array by each character
		char [] eachChar = pc.toCharArray();
		
		//First character must be a letter, if not exception is thrown.
		if(!Character.isLetter(eachChar[0])) 
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}
		//Second character must be a digit, if not exception is thrown.
		if(!Character.isDigit(eachChar[1])) 
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}
		//Third character must be a letter, if not exception is thrown.
		if(!Character.isLetter(eachChar[2])) 
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}
		//Fourth character must be a space, if not exception is thrown.
		if(!Character.isSpaceChar(eachChar[3]))
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}
		//Fifth character must be a digit, if not exception is thrown.
		if(!Character.isDigit(eachChar[4])) 
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}
		//Sixth character must be a letter, if not exception is thrown.
		if(!Character.isLetter(eachChar[5])) 
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}
		//Seventh character must be a digit, if not exception is thrown.
		if(!Character.isDigit(eachChar[6])) 
		{
			throw new InvalidPostalCodeException("Invalid Postal Code Entered");
		}	
	}
	
	/**
	 * Method to validate the phone number. Must be in 
	 * format 111-111-1111. Will throw an exception if it 
	 * is not in this format. 
	 * @param pn is the phone number as a String
	 * @throws InvalidPhoneNumberException is thrown when it
	 * is not in the proper format.
	 */
	private void validatePhoneNumber(String pn) throws InvalidPhoneNumberException
	{
		//put the phone number (pn) into an array by each character
		char [] eachChar = pn.toCharArray();
		
		//First three characters must be digit, if not an exception is thrown.
		if(!Character.isDigit(eachChar[0]) || 
				!Character.isDigit(eachChar[1]) || 
				!Character.isDigit(eachChar[2])) 
		{
			throw new InvalidPhoneNumberException("Invalid Phone Number Entered");
		}
		//Fourth character must be a '-', if not an exception is thrown.
		if(eachChar[3] != '-') 
		{
			throw new InvalidPhoneNumberException("Invalid Phone Number Entered");
		}
		//Next three characters must be digit, if not an exception is thrown.
		if(!Character.isDigit(eachChar[4]) || 
				!Character.isDigit(eachChar[5]) || 
				!Character.isDigit(eachChar[6])) 
		{
			throw new InvalidPhoneNumberException("Invalid Phone Number Entered");
		}
		//Eighth character must be a '-', if not an exception is thrown.
		if(eachChar[7] != '-') 
		{
			throw new InvalidPhoneNumberException("Invalid Phone Number Entered");
		}
		//Next four characters must be digit, if not an exception is thrown.
		if(!Character.isDigit(eachChar[8]) || 
				!Character.isDigit(eachChar[9]) || 
				!Character.isDigit(eachChar[10]) || 
				!Character.isDigit(eachChar[11])) 
		{
			throw new InvalidPhoneNumberException("Invalid Phone Number Entered");
		}
	}
	
	
	/*************Setters and getters*******************/
	
	/**
	 * Method to get if the object is either
	 * active or not (logically deleted 
	 * or not).
	 * @return A boolean, true = active, 
	 * false = logically deleted.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Method to set an object to either active
	 * or not (logically deleted or not).
	 * @param active is a boolean, true = active, 
	 * false = logically deleted.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Method to get the client's id.
	 * @return A long that is the client's id.
	 */
	public long getClientId() {
		return clientId;
	}

	/**
	 * Method to set the client's id
	 * @param clientId a long that is the client's id.
	 */
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	/**
	 * Method to get the client's first name.
	 * @return A String that is the client's id
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to set the client's first name.
	 * @param firstName a String that is the client's
	 * first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method to get the client's last name.
	 * @return A String that is the client's last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to set the client's last name.
	 * @param lastName a String for the client's
	 * last name. 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method to get the client's address.
	 * @return a String that is the client's address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method to set the client's address.
	 * @param address a String that is the 
	 * client's address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method to get the client's postal code.
	 * @return a String that is the client's 
	 * postal code. 
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Method to set the client's postal code
	 * @param postalCode a String that is the
	 * client's postal code. 
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Method to get the client's phone number.
	 * @return A String that is the client's phone 
	 * number. 
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Method to set the client's phone number
	 * @param phoneNumber a String that is the 
	 * client's phone number. 
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Method to get the client's type.
	 * @return A char that is the client's type. 
	 */
	public char getClientType() {
		return clientType;
	}

	/**
	 * Method to set the client's type.
	 * @param clientType a char that is the client's type. 
	 */
	public void setClientType(char clientType) {
		this.clientType = clientType;
	}
	
	/**
	 * Method to get the size of the client record in bytes
	 * @return an int of the size of the client record in bytes.
	 */
	public static int getSize() {
		return SIZE;
	}
	
}
