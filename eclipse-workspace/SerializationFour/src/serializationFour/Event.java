package serializationFour;

import java.io.Serializable;

public class Event implements Serializable {
	
	private String description;
	private Location location;
	private static final long serialversionUID = 64861316L;

	public Event() {}
	
	public Event(String description, Location location) 
	{
		this.description = description;
		this.location = location;
	}
	
	public String toString() 
	{
		return "Description: " + getDescription() + 
				"\nLocation: " + getLocation();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
