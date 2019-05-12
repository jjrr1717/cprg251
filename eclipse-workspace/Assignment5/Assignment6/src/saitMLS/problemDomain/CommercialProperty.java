/**
 * CommercialProperty is a class for a
 * commercial property for SAIT's MLS.
 * It extends Property and implements Serializable.
 * In addition to the Property information it also
 * holds the number of floors and type of
 * commercial property.  
 * @author Jocelyn Wegen
 * @version April 2, 2019
 */
package saitMLS.problemDomain;

import java.io.Serializable;

import saitMLS.exceptions.InvalidLegalDescriptionException;

public class CommercialProperty extends Property implements Serializable {

	/**
	 * A long for serial version id for the serial file
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * An int for the number of floors.
	 */
	private int noFloors;
	/**
	 * A String for the type of property.
	 */
	private String type;
	
	
	/******************Constructors**************************/
	
	/**
	 * Default constructor. Accepts no parameters.
	 */
	public CommercialProperty() {}
	
	/**
	 * Constructor to initialize a commerical property. 
	 * @param id a long for a unique id.
	 * @param legalDescription a String for the legal description
	 * @param address a String for the address
	 * @param quadrant a String for the quadrant of the city
	 * @param zone a String for the zone.
	 * @param askingPrice a double for the asking price.
	 * @param comments a String for any comments on the property.
	 * @param type a String for the type
	 * @param noFloors an int for the number of floors.
	 * @throws InvalidLegalDescriptionException thrown if the legal description is not valid. 
	 */
	public CommercialProperty(long id, String legalDescription,
							  String address, String quadrant,
							  String zone, double askingPrice, 
							  String comments, String type, 
							  int noFloors) throws InvalidLegalDescriptionException 
	{
		super(id, legalDescription, address, quadrant, zone, 
				askingPrice, comments);
		this.type = type;
		this.noFloors = noFloors;
	}
	
	/******************Public methods**************************/
	/**
	 * Method to override the toString method. Format the
	 * commerical property data. 
	 */
	public String toString() 
	{
		return super.toString() + 
				"\nType: " + getType() + 
				"\nNumber of Floors: " + getNoFloors();
	}

	/******************Setters & Getters**********************/
	/**
	 * Method to get number of floors.
	 * @return an int for the number of floors.
	 */
	public int getNoFloors() {
		return noFloors;
	}

	/**
	 * Method to set the number of floors.
	 * @param noFloors an int for the number of floors.
	 */
	public void setNoFloors(int noFloors) {
		this.noFloors = noFloors;
	}

	/**
	 * Method to get the commercial property type.
	 * @return a String for the commercial property type. 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method to set the type of commercial property.
	 * @param type a String for the commercial property type. 
	 */
	public void setType(String type) {
		this.type = type;
	}
}
