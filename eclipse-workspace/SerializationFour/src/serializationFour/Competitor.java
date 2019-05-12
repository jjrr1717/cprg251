package serializationFour;

import java.io.Serializable;

public class Competitor implements Serializable {
	
	private String name;
	private Event event;
	private String yob;
	private static final long serialversionUID = 64861317L;
	
	public Competitor() {}
	
	public Competitor(String name, Event event, String yob) 
	{
		this.name = name;
		this.event = event;
		this.yob = yob;
	}
	
	public String toString() 
	{
		return "Name: " + getName() + 
				"\nEvent: " + getEvent() + 
				"\nYear Of Birth: " + getYob();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getYob() {
		return yob;
	}

	public void setYob(String yob) {
		this.yob = yob;
	}
}
