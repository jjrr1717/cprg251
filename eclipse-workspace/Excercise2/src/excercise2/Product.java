package excercise2;

public class Product {
	
	private int productId;
	private String productName;
	private double price;
	
	public Product(int id, String name, double price) 
	{
		productId = id;
		productName = name;
		this.price = price;
	}
	
	public void setId(int id) 
	{
		productId = id;
	}
	
	public int getId() 
	{
		return productId;
	}
	
	public void setProductName(String name) 
	{
		productName = name;
	}
	
	public String getProductName() 
	{
		return productName;
	}
	
	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	public double getPrice() 
	{
		return price;
	}
	
	public String toString() 
	{
		return ("Product Id: " + getId() +
				"\nProduce Name : " + getProductName() + 
				"\nPrice: " + getPrice());
	}

}
