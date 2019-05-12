package serializationFour;

import java.io.Serializable;

public class Location implements Serializable {
	
	private String postalCode;
	private String address;
	private static final long serialversionUID = 64861315L;
	
	public Location() 
	{
		
	}
	
	public Location(String postalCode, String address) 
	{
		this.postalCode = postalCode;
		this.address = address;
	}
	
	public String toString() 
	{
		return "Postal Code: " + getPostalCode() + 
				"\nAddress: " + getAddress();
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
