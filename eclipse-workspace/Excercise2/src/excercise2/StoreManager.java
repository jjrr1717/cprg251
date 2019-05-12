package excercise2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreManager {
	
	final String CUSTOMER_FILE = "res/customers.txt";
	final String PRODUCT_FILE = "res/products.txt";
	final String ORDER_FILE = "res/orders.txt";
	private ArrayList<Customer> customerList;
	private ArrayList<Product> productList;
	private ArrayList<Order> orderList;
	
	Scanner keyboard = new Scanner(System.in);

	/*makeCustomerList();
	makeProductList();
	makeOrderList();*/
	
	public StoreManager() throws Exception
	{
		customerList = new ArrayList<Customer>();
		productList = new ArrayList<Product>();
		orderList = new ArrayList<Order>();

	}
	
	public void makeCustomerList() throws Exception
	{
		File inputFile = new File(CUSTOMER_FILE);
		Scanner scanInputFile = new Scanner(inputFile);
		
		while(scanInputFile.hasNext()) 
		{
			int id = scanInputFile.nextInt();
			String name = scanInputFile.next();
			String address = scanInputFile.next();
			long phone = scanInputFile.nextLong();
			
			Customer theCustomer = new Customer(id, name, address, phone);
			customerList.add(theCustomer);
			
		}
		
		scanInputFile.close();
		
	}
	
	public void printCustomers() throws Exception
	{
		makeCustomerList();
		System.out.println("Customer List");
		System.out.println("=============");
		for(Customer aCustomer: customerList) 
		{
			System.out.println(aCustomer);
			System.out.println();
		}
	}
	
	public void makeProductList() throws Exception
	{
		File inputFile = new File(PRODUCT_FILE);
		Scanner scanInputFile = new Scanner(inputFile);
		
		while(scanInputFile.hasNext()) 
		{
			int id = scanInputFile.nextInt();
			String name = scanInputFile.next();
			double price = scanInputFile.nextDouble();
			
			Product theProduct = new Product(id, name, price);
			
			productList.add(theProduct);	
		}
		
		scanInputFile.close();
		
	}
	
	public void makeOrderList() throws Exception
	{
		File inputFile = new File(ORDER_FILE);
		Scanner scanInputFile = new Scanner(inputFile);
		
		
		while(scanInputFile.hasNext()) 
		{
			
	
			int counter = 0;
			int orderId = scanInputFile.nextInt();
			int customerId = scanInputFile.nextInt();
			String [] splitted = scanInputFile.next().split(";");
			
			int [] item = new int[splitted.length];
			for(String split: splitted) 
			{
				item[counter] = Integer.parseInt(split);
				counter++;
			}
			
			
			
			Order order = new Order(orderId, customerId, item);
			orderList.add(order);
			
		}
		
		scanInputFile.close();
	}
	
	public void printProducts() throws Exception
	{
		makeProductList();
		System.out.println("Product List");
		System.out.println("============");
		
		for(Product aProduct: productList) 
		{
			System.out.println(aProduct);
			System.out.println();
		}
	}
	
	public void printOrders() throws Exception
	{
		makeOrderList();
		System.out.println("Order List");
		System.out.println("==========");
		
		for(Order anOrder : orderList) 
		{
			System.out.println(anOrder);
			System.out.println();
			
		}
	}
	
	public void addCustomer() throws Exception
	{
		int customerId;
		String customerAddress;
		String customerName;
		long customerPhone;
		
		FileWriter openFile = new FileWriter(CUSTOMER_FILE, true);
		PrintWriter writeFile = new PrintWriter(openFile);
		
		System.out.println("\nWelcome to the Adding Customer Wizard!");
		System.out.println("Please add the following information " + 
						   "to add a customer.");
		System.out.print("Enter customer ID: ");
		customerId = keyboard.nextInt();
		
		System.out.print("\nEnter customer address: ");
		customerAddress = keyboard.next();
		
		System.out.print("\nEnter customer name: ");
		customerName = keyboard.next();
		
		System.out.print("\nEnter customer phone number: ");
		customerPhone = keyboard.nextLong();
		
		writeFile.println();
		
		writeFile.println(customerId + " " + customerName + 
						" " + customerAddress + " " + customerPhone);
		
		System.out.println("Customer has been added!");
		
		writeFile.close();
	}
	
	public void addProduct() throws Exception
	{
		int productId;
		String productName;
		double productPrice;

		FileWriter openFile = new FileWriter(PRODUCT_FILE, true);
		PrintWriter writeFile = new PrintWriter(openFile);
		
		System.out.println("\nWelcome to the Adding Product Wizard!");
		System.out.println("Please add the following information " + 
						   "to add a product.");
		
		System.out.print("Enter product ID: ");
		productId = keyboard.nextInt();
		
		System.out.print("\nEnter product name: ");
		productName = keyboard.next();
		
		System.out.print("\nEnter product price: ");
		productPrice = keyboard.nextDouble();
		
		writeFile.println();
		
		writeFile.println(productId + " " + productName + " " + productPrice);
		
		System.out.println("Product has been added!");
		
		writeFile.close();
			
	}
	
	public void removeProduct() throws Exception
	{
		int productId;
		ArrayList<Product> currentProductList = new ArrayList<Product>();
		System.out.println("\nWelcome to the Remove Product Wizard!");
		System.out.println("Please enter the following information " +
						   "to remove a product");
		System.out.print("Please enter the product id: ");
		productId = keyboard.nextInt();
		
		File openFile = new File(PRODUCT_FILE);
		Scanner scanFile = new Scanner(openFile);
		
		while(scanFile.hasNext()) 
		{
			int id = scanFile.nextInt();
			String name = scanFile.next();
			double price = scanFile.nextDouble();
			
			Product newProduct = new Product(id, name, price);
			currentProductList.add(newProduct);	
		}
		
		for(Product product: currentProductList) 
		{
			if(product.getId() == productId) 
			{
				currentProductList.remove(product);
			}
		}
		
		scanFile.close();
		
		FileWriter openFileAgain = new FileWriter(PRODUCT_FILE, false);
		PrintWriter writeFile = new PrintWriter(openFileAgain);
		for(Product product: currentProductList)
			{
				writeFile.println(product.getId() + " " + product.getProductName() + " " +
						         product.getPrice());
			}
		
		System.out.print("Product has been removed");
		writeFile.close();
	}
	
	public void addOrder() throws Exception
	{
		int orderId;
		int customerId;
		int anItem;
		int [] items = new int[20];
		int counter = 0;
		boolean continueAdding = true;
		String answer;
		String itemString = "";

		FileWriter openFile = new FileWriter(ORDER_FILE, true);
		PrintWriter writeFile = new PrintWriter(openFile);
		
		System.out.println("\nWelcome to the Adding Orders Wizard!");
		System.out.println("Please add the following information " + 
						   "to add a product.");
		System.out.print("Enter the order ID: ");
		orderId = keyboard.nextInt();
		
		System.out.print("\nEnter customer ID: ");
		customerId = keyboard.nextInt();
		
		while(continueAdding != false || counter > 20) 
		{
			System.out.print("Enter items ordered (up to 20): ");
			anItem = keyboard.nextInt();
			
			items[counter] = anItem;
			
			System.out.print("Would you like to enter another item? (Y/N)");
			answer = keyboard.next();
			
			if(answer.equalsIgnoreCase("N")) 
			{
				continueAdding = false;
			}
				
		}
		
		for(int theItem: items) 
		{
			itemString += theItem + ";";
		}
		
		writeFile.println();
		
		writeFile.println(orderId + " " + customerId + " " + itemString);
		
		System.out.println("Order has been added!");
		writeFile.close();
	}

}
