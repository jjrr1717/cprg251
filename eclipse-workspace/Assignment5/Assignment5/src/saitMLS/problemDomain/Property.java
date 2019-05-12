/**
 * Abstract class that contains the it, address, asking price,
 * comments, legal description, quadrant, and zone for
 * properties for SAIT MLS.  It implements Serializable. 
 * @author Jocelyn Wegen
 * @version March 31, 2019
 */
package saitMLS.problemDomain;

import java.io.Serializable;

import saitMLS.exceptions.InvalidLegalDescriptionException;

public abstract class Property implements Serializable {

	/**
	 * String for property address.
	 */
	private String address;
	/**
	 * double for asking price.
	 */
	private double askingPrice;
	/**
	 * String for comments.
	 */
	private String comments;
	/**
	 * Long for unique id.
	 */
	private long id;
	/**
	 * String for legal description.
	 */
	private String legalDescription;
	/**
	 * String for quadrant of city.
	 */
	private String quadrant;
	/**
	 * String for zone.
	 */
	private String zone;
	/**
	 * serial version Id for the serial file.
	 */
	private static long serialVersionUID = 95845L;

	/************ Constructors ****************************/
	/**
	 * Default constructor. Accepts no parameters.
	 */
	public Property() {}
	
	/**
	 * Constructor to initialize and assign attributes to property.
	 * @param id a long for the properties unique id.
	 * @param legalDescription a String for the properties legal description.
	 * @param address a String for the properties address.
	 * @param quadrant a quadrant for the properties quadrant in the city
	 * @param zone a String for the properties zone. 
	 * @param askingPrice a double for the properties asking price. 
	 * @param comments a String for any comments on the properties
	 * @throws InvalidLegalDescriptionException Legal description must be in valid format.
	 */
	public Property(long id, String legalDescription, String address, String quadrant, String zone, double askingPrice,
			String comments) throws InvalidLegalDescriptionException {
		this.id = id;
		validateLegalDescription(this.legalDescription = legalDescription);
		this.address = address;
		this.quadrant = quadrant;
		this.zone = zone;
		this.askingPrice = askingPrice;
		this.comments = comments;
	}

	/************ public methods **************************/

	/**
	 * Method to compare two properties and determine if they 
	 * are the same or not. 
	 * @param p is a Property to compare. 
	 * @return a boolean of true if they are equal or 
	 * false if they are not equal. 
	 */
	public boolean equals(Property p) {
		// variable to hold boolean
		boolean areEqual = false;

		//if property id's match they are equal. 
		if (p.getId() == getId()) {
			areEqual = true;
		}

		return areEqual;
	}

	/**
	 * Method to override the toString method.
	 * Formats the property information. 
	 */
	public String toString() {
		return "Id: " + getId() + "\nLegal Description: " + getLegalDescription() + "\nAddress: " + getAddress()
				+ "\nQuadrant: " + getQuadrant() + "\nZone: " + getZone() + "\nAsking Price: " + getAskingPrice()
				+ "\nComments: " + getComments();
	}

	/************ Setters & Getters ***********************/

	/**
	 * Method to get the address.
	 * @return the address as a String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method to set the address. 
	 * @param address a String representing the address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method to get asking price.
	 * @return the asking price as a double
	 */
	public double getAskingPrice() {
		return askingPrice;
	}

	/**
	 * Method to set the asking price
	 * @param askingPrice a double representing the asking price.
	 */
	public void setAskingPrice(double askingPrice) {
		this.askingPrice = askingPrice;
	}

	/**
	 * Method to get comments
	 * @return a String of the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Method to set comments
	 * @param comments a String for the comments.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Method to get the id
	 * @return a the id as a long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Method to set the id
	 * @param id a long for the id. 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Method to get the legal description
	 * @return the legal description as a String
	 */
	public String getLegalDescription() {
		return legalDescription;
	}

	/**
	 * Method to set the legal description
	 * @param legalDescription a String for the legal description
	 * @throws InvalidLegalDescriptionException legal description must be in valid format
	 */
	public void setLegalDescription(String legalDescription) throws InvalidLegalDescriptionException {
		validateLegalDescription(this.legalDescription = legalDescription);
	}

	/**
	 * Method to get the quadrant
	 * @return the quadrant as a String
	 */
	public String getQuadrant() {
		return quadrant;
	}

	/**
	 * Method to set the quadrant.
	 * @param quadrant a String for the quadrant.
	 */
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	/**
	 * Method to get the zone
	 * @return the zone as a String
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * Method to set the zone. 
	 * @param zone a String for the zone. 
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/************ Private methods **************************/

	/**
	 * Method to validate the legal description. It must follow the 
	 * pattern [0-9]{1,4}[A-Z][0-9]{1,4}[-][0-9]{1,2}.  
	 * @param desc a String of the legal description
	 * @throws InvalidLegalDescriptionException thrown when legal description is not valid. 
	 */
	private void validateLegalDescription(String desc) throws InvalidLegalDescriptionException {
		//Got regular expression info from https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
		//legal description must match a specific pattern.
		boolean valid = desc.matches("[0-9]{1,4}[A-Z][0-9]{1,4}[-][0-9]{1,2}");

		//if not valid, throw InvalidLegalDescriptionException. 
		if (!valid) {
			throw new InvalidLegalDescriptionException("Legal Description format is invalid!");
		}
	}

}
