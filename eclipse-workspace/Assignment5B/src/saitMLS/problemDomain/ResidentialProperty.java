/**
 * ResidentialProperty is a class for a Residential Property
 * for SAIT MLS.
 * It extends Property and implements Serializable.
 * Along with all the property information it also 
 * has area, number of bathrooms, number of bedrooms, 
 * and garage type.  
 * 
 * @author Jocelyn Wegen
 * @version April 2, 2019
 */
package saitMLS.problemDomain;

import java.io.Serializable;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;

public class ResidentialProperty extends Property implements Serializable {
	
	/**
	 * A long for the serial version Id for the serial file
	 */
	private static final long serialVersionUID = 2L;
	/**
	 * A double for the properties area.
	 */
	private double area;
	/**
	 * A double for the number of bathrooms.
	 */
	private double bathrooms;
	/**
	 * An int for the number of bedrooms,
	 */
	private int bedrooms;
	/**
	 * A char for the garage type. 
	 */
	private char garage;
	
	
	/************ Constructors ****************************/
	
	/**
	 * Default constructor. Accepts no parameters. 
	 */
	public ResidentialProperty() {}
	
	/**
	 * Constructor to initialize a Residential Property.
	 * @param id a long for the properties id.
	 * @param legalDescription a String for the legal description.
	 * @param address a String for the address.
	 * @param quadrant a String for the quadrant.
	 * @param zone a String for the zone.
	 * @param askingPrice a double for the asking price.
	 * @param comments a string for any comments.
	 * @param area a double for the properties area.
	 * @param bathrooms a double for the bathrooms.
	 * @param bedrooms an int for the number of bedrooms.
	 * @param garage a char for the garage type.
	 * @throws InvalidLegalDescriptionException legalDescription must be in a valid format.
	 * @throws InvalidNumberOfBathroomsException bathrooms must be in a valid format. 
	 */
	public ResidentialProperty(long id, String legalDescription, 
							   String address, String quadrant,
							   String zone, double askingPrice,
							   String comments, double area,
							   double bathrooms, int bedrooms,
							   char garage) throws InvalidLegalDescriptionException, InvalidNumberOfBathroomsException 
	{
		
			super(id, legalDescription, address, quadrant, zone, askingPrice, comments);
		
			this.area = area;
			validateNumberOfBathrooms(this.bathrooms = bathrooms);
			this.bedrooms = bedrooms;
			this.garage = garage;
	}
	
	/************ public methods ****************************/
	/**
	 * Method to override the toString method. To fomrat the
	 * residential properties data. 
	 */
	public String toString() 
	{
		return super.toString() + 
				"\nArea: " + getArea() + 
				"\nBathrooms: " + getBathrooms() + 
				"\nBedrooms: " + getBedrooms();
	}

	/************ getters & setters***********************/
	/**
	 * Method to get properties area. 
	 * @return a double for the area. 
	 */
	public double getArea() {
		return area;
	}

	/**
	 * Method to set the properties area.
	 * @param area a double for the properities area.
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * Method to get the number of bathrooms.
	 * @return a double for the number of bathrooms.
	 */
	public double getBathrooms() {
		return bathrooms;
	}

	/**
	 * Method to set the number of bathrooms.
	 * @param bathrooms a double for the number of bathrooms.
	 * @throws InvalidNumberOfBathroomsException bathrooms must be in proper format.  
	 */
	public void setBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException {
		validateNumberOfBathrooms(this.bathrooms = bathrooms);
	}

	/**
	 * Method to get the number of bedrooms.
	 * @return an int for the number of bedrooms.
	 */
	public int getBedrooms() {
		return bedrooms;
	}

	/**
	 * Method to set the number of bedrooms.
	 * @param bedrooms an int for the number bedrooms. 
	 */
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	/**
	 * Method to get the garage.
	 * @return a char for the garage type. 
	 */
	public char getGarage() {
		return garage;
	}

	/**
	 * Method to set the garage type.
	 * @param garage a char for the garage type. 
	 */
	public void setGarage(char garage) {
		this.garage = garage;
	}
	
	/************private methods***********************/
	/**
	 * Method to validate the number of bathrooms. It 
	 * must be a double that either ends with x.0 or
	 * x.5. 
	 * @param nob is a double for the number of bathrooms.
	 * @throws InvalidNumberOfBathroomsException thrown when number of bathrooms is not in valid format. 
	 */
	private void validateNumberOfBathrooms(double nob) throws InvalidNumberOfBathroomsException 
	{
		String nobString = nob + "";
		boolean valid = nobString.matches("[0-9]*[.][05]{1}");
		if(!valid) 
		{
			throw new InvalidNumberOfBathroomsException("Invlaid number of bathrooms entered!");
		}
	}
}
