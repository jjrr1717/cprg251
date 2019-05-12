package Model;



public class Order {
	
	private int orderId;
	private int customerId;
	private int [] items;
	
	public Order (int id, int cid, int [] items) 
	{
		orderId = id;
		customerId = cid;
		this.items = items;
	}
	
	public void setOrderId (int id) 
	{
		orderId = id;
	}
	
	public int getOrderId() 
	{
		return orderId;
	}
	
	public void setCustomerId (int cid) 
	{
		customerId = cid;
	}
	
	public int getCustomerId() 
	{
		
		return customerId;
	}
	
	public void setItems(int [] items) 
	{
		this.items = items;
	}
	
	public int [] getItems() 
	{
		return items;
	}
	
	public String toString () 
	{
		
		String theItems= "";
		for(int item : items) 
		{
			theItems += item + " ";
		}
		
		return ("Item Id: "+ getOrderId() + "\nCustomer Id: " + getCustomerId() + "\nItems: " + theItems);

	}

}
