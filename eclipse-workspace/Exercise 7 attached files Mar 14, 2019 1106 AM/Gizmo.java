package gizmoRAF_Example;

import java.util.*;

/**
 *	Gizmo.java
 *
 * @author dwatson, Maryam
 * @version 2.0
 *
 * Data container class for a Gizmo object.
 * 
 */

public class Gizmo
{
	//Constants
	protected static final int			SIZE = 40;
	
	// Attributes
	private int				id; 			// 4 bytes
	private String			description; 	// 21 bytes for characters plus 2 bytes overhead
	private int				quantity; 		// 4 bytes
	private double			price;  		// 8 bytes
	private boolean			active; 		// 1 byte
	
	//Constructors
	/**
	 * Creates a Gizmo object and sets all attributes of Gizmo to their default values.
	 * @throws InvalidIdException 
	 */
	public Gizmo() throws InvalidIdException
	{
		setId(1000);
		active = true;
	}
	
	/**
     * Creates a Gizmo object and sets all attributes of Gizmo to specified values.
	 * @param id the id number of Gizmo
	 * @param description description of Gizmo
	 * @param quantity number of this Gizmo currently in-stock
	 * @param price the price of this Gizmo
	 * @throws InvalidIdException 
	 */
	public Gizmo(int id, String desc, int quantity, double price) throws InvalidIdException
	{		
		//this.id = id;
		setId(id);
		this.description = desc;
		this.quantity = quantity;
		this.price = price;
		active = true;
	}
	
	/**
    * Creates a Gizmo object and sets all attributes of Gizmo to specified values specified
    * by the supplied String. The supplied String must have format: 1000;Knock Bits;88;12.67 
	 * @param line String representation of Gizmo
	 * @throws InvalidIdException 
	 */
	public Gizmo(String line) throws InvalidIdException
	{
		String fields[] = line.split(";");
		
		//id = Integer.parseInt(fields[0]);
		setId(Integer.parseInt(fields[0]));
		description = fields[1];
		quantity = Integer.parseInt(fields[2]);
		price = Double.parseDouble(fields[3]);
		active = true;
	}
	
	/**
	 * Gets the id of Gizmo.
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Sets the id of Gizmo
	 * @param id the id to set
	 */
	public void setId(int id)throws InvalidIdException
	{
		if(id < 1000)
		{
			throw new InvalidIdException();
		}
		this.id = id;
	}

	/**
	 * Gets the description of Gizmo.
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the of description of Gizmo.
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the quantity of Gizmo.
	 * @return the quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Sets the quantity of Gizmo.
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Gets the price of Gizmo.
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * Sets the of price of Gizmo.
	 * @param price the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * Indicates whether a Gizmo is active or not. In case the Gizmo is logically deleted returns false.
	 * @return true if Gizmo is active, otherwise false
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * Sets active value of Gizmo. In case the Gizmo is deleted (logically) it's active field is set
	 * to false. Otherwise set to true.
	 * @param active field of Gizmo
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	// Operational Methods
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{		
		return String.format("%6s%15s%6d%8.2f",id,description,quantity,price);
	}
}
