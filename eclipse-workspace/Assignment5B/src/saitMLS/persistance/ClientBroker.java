/**
 * ClientBroker is a class that contains all 
 * the methods that will handles all the 
 * interactions with a RandomAccessFile
 * database. The RandomAccessFile database
 * contains information about a Client. 
 * 
 *  @author Jocelyn Wegen
 *  @version March 16, 2019
 */

package saitMLS.persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;
import saitMLS.problemDomain.Client;

public class ClientBroker implements Broker {
	
	/**
	 * broker to represent the ClientBroker
	 */
	private static ClientBroker broker;
	/**
	 * INPUT_FILE holds the file path of the client file
	 */
	private static final String INPUT_FILE = "res/clients.txt";
	/**
	 * RANDOM_FILE holds the file path to the binary file
	 */
	private static final String RANDOM_FILE = "res/clients.bin";
	/**
	 * MODE holds the mode read and write for the RandomAccessFile
	 */
	private static final String MODE = "rw";
	/**
	 * highId holds the higest Client id number
	 */
    public long highId = 0;
    /**
     * raf is the RandomAccessFile Object
     */
	private RandomAccessFile raf;

	/***************Constructor********************/
	
	/**
	 * private constructor to only allow one
	 * object of the ClientBroker.  Accepts no
	 * parameters. 
	 */
	private ClientBroker() 
	{
		try {
			raf = new RandomAccessFile(RANDOM_FILE, MODE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to give access to the clientBroker.
	 * @return the broker for the ClientBroker
	 */
	public static ClientBroker getClientBroker() 
	{

		//open random/binary file
		File inputFile = new File(RANDOM_FILE);
		
		/*
		 * if file exists create just create a ClientBroker object, 
		 * else create ClientBroker and create the RandomAccessFile.
		 * Only create ClientBroker if it doesn't exist - when it is
		 * null. 
		 */
		if(inputFile.exists()) 
		{
			if(broker == null) 
			{
				//create ClientBroker object
				broker = new ClientBroker();
				//put the highId to the last id in the record
				broker.findHighestCurrentId();
			}
		}
		else 
		{
			if(broker == null) 
			{
				//create ClientBroker object
				broker = new ClientBroker();
				//create the RamdomAccessFile 
				broker.loadBinaryFile();
				//put the highId to the last id in the record
				broker.findHighestCurrentId();
				
			}
		}
		
		return broker;
		
	}
	
	
	/*************************public methods*********************/
	
	
	/**
	 * Method to print the binary file. Used for
	 * testing purposes. 
	 */
	public void printClient() 
	{
		long i;
		try {
			//put pointer to beginning of file
			raf.seek(0);
			//loop through binary file to print all the Clients
			for(i = 0; i<raf.length(); i+=Client.getSize()) 
			{
				//System.out.println(raf.getFilePointer());
				
				//create Client to hold record
				Client c = readBinaryFile();
				
				//if record is active print it
				if(c.isActive()) {
					System.out.print(c);
					System.out.println();
				}
			}
			//System.out.print(i - Client.SIZE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	/**
	 * Method to save a new client when the id is at zero.
	 * If the id is not at zero than it update the
	 * client if there were any changes made.  
	 * @return a boolean is the client was successfully updated
	 * or added.
	 */
	public boolean persist(Object o) {
		boolean clientAdded = false;
		
		//create client object
		Client c = (Client) o;
		
		//if client id is 0 then add client to end of raf file
		if(c.getClientId() == 0l) 
		{
			//put the point at the end of raf file
			try {
				raf.seek(raf.length());
				//make sure next highId will be correct
				findHighestCurrentId();
				//set the client id to hight id + 1
				c.setClientId(++highId);
				//write Client to raf file
				writeLineToBinary(c);
				
				clientAdded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/*
		 * if the client id is not 0 then it already exists 
		 * and raf file should be updated to current 
		 * information.
		 */
		else if(c.getClientId() > 0l) 
		{
			try {
				//set the pointer to the location of the client
				raf.seek(findClientId(c.getClientId()));
				
				//write the client to the raf file
				writeLineToBinary(c);
				
				clientAdded = true;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clientAdded;
		}
		

		

	/**
	 * Method to remove (logically delete) a client from the binary file.
	 * @param o is an Object the user wishes to remove
	 */
	public boolean remove(Object o) {
		boolean clientRemoved = false;
		//create a client object 
		Client c = (Client) o;
		
		
		try {
			//put the pointer to the position of the Client to be removed
			raf.seek(findClientId(c.getClientId()));
			
			//change the active attribute for that client to false (logical delete)
			raf.writeBoolean(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return clientRemoved;
	}

	/**
	 * Method to search for a Client based on user
	 * entry of search type and and item. 
	 * @param searchItem is a String of the user input to search for
	 * @param type is the type of data the user is searching for. 
	 */
	public List search(String searchItem, String type) {
		//create an array of Clients found in search
		ArrayList<Client> searchResult = new ArrayList<>();
		
		//perform different search based on user input for type
		switch(type) 
		{
		case "Id":
			searchResult = searchId(searchItem);
			break;
		case "Firstname":
			searchResult = searchFirstName(searchItem);
			break;
		case "Lastname":
			searchResult = searchLastName(searchItem);
			break;
		case "Phone Number":
			searchResult = searchPhoneNumber(searchItem);
			break;
		case "Postal Code":
			searchResult = searchPostalCode(searchItem);
			break;
		case "Type":
			searchResult = searchClientType(searchItem);
			break;
		default:
			System.out.println("Invalid");
		}
		
		return searchResult;
	}
	

	/**
	 * Method to close the RandomAccessFile and 
	 * the broker
	 */
	@Override
	public void closeBroker() {
		// TODO Auto-generated method stub
		
		try {
			raf.close();
			broker = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
/*****************private methods***********************/
	
	/**
	 * Method to put padding on the String data for the
	 * RandomAccessFile. 
	 * @param inputString is the String to be padded
	 * @param length is the number of padding
	 * @return a String that is padded
	 */
	private String padString(String inputString, int length) 
	{
		//if inputString is greater or equal to string no need to pad
		if(inputString.length() >= length) 
		{
			return inputString;
		}
		
		//create StringBuilder to hold new string with padding
		StringBuilder sb = new StringBuilder();
		//append ' ' to sb the length minus the inputString length
		while(sb.length() < length - inputString.length()) 
		{
			sb.append(' ');
		}
		
		//append the actual inputString to sb
		sb.append(inputString);
		
		//return the String with the padding
		return sb.toString();
	}
	
	/**
	 * Method to read each line of the client txt file
	 * and write it to the binary file.  
	 */
	private void loadBinaryFile() 
	{
		//open input file
		File inputFile = new File(INPUT_FILE);
		Scanner readFile = null;
		try {
			//create Scanner object to scan through file
			readFile = new Scanner(inputFile);
			//create an Array containing each attribute
			while(readFile.hasNextLine()) 
			{
				String [] line = readFile.nextLine().split(";");
				//put this line into a ClientBroker
	
				//make a Client out of this line
				//but first create the Id's for the Clients. 
				++highId;
				Client c = new Client(highId, 
								     line[0], 
								     line[1],
								     line[2],
								     line[3],
								     line[4],
								     line[5].charAt(0));
				
				//write the line to the RandomAccessFile 
				writeLineToBinary(c);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidPostalCodeException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		System.out.println("Created");
		readFile.close();
		
	}
	
	/**
	 * Method to write a line to the RandomAccessFile.
	 * @param c is a Client object where the 
	 * data for the RandomAccessFile will come from.  
	 */
	private void writeLineToBinary(Client c) 
	{

		try {
			
			//write the client info to the RandomAccessFile
			raf.writeBoolean(c.isActive());
			raf.writeLong(c.getClientId());
			raf.writeUTF(padString(c.getFirstName(),20));
			raf.writeUTF(padString(c.getLastName(), 20));
			raf.writeUTF(padString(c.getAddress(), 50));
			raf.writeUTF(padString(c.getPostalCode(),7));
			raf.writeUTF(padString(c.getPhoneNumber(),12));
			raf.writeChar(c.getClientType());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to read what is stored in the
	 * RandomAccessFile.  The data is put
	 * into a Client object.  
	 * @return a Client 
	 * @throws InvalidPhoneNumberException
	 * @throws InvalidPostalCodeException}
	 */
	private Client readBinaryFile() 
	{
		//create Client object to store client data
		Client c = null;
		
		try {
			c = new Client();
			/*
			 * set all the Client's attributes to what 
			 * is in the RandomAccessFile.
			 */
			c.setActive(raf.readBoolean());
			c.setClientId(raf.readLong());
			c.setFirstName(raf.readUTF().trim());
			c.setLastName(raf.readUTF().trim());
			c.setAddress(raf.readUTF().trim());
		    c.setPostalCode(raf.readUTF().trim());
			c.setPhoneNumber(raf.readUTF().trim());
			c.setClientType(raf.readChar());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return c;
	}
	
	/**
	 * Method to find the highest id, 
	 * and assigns it to the highId.
	 */
	private void findHighestCurrentId() 
	{
		//to store the high id
		long highId = 0;
		//create client to hold record
		Client c = null;
		try {
			//Get to the last record in the raf file
			long position = raf.length() - Client.getSize();
			//set the pointer to the last record
			raf.seek(position);
			//create a Client Object of the last record
			c = readBinaryFile();
			//assign the highId as the id value found in the last record.
			highId = c.getClientId();
			this.highId = highId;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	/**
	 * Method to find the Byte location of a Client
	 * id in the binary file. 
	 * @param id a long that represents the Client to find
	 * @return the binary location of the Client found.
	 */
	private long findClientId(long id)
	{
		boolean found = false;
		long thePosition = 0;
		int i = 0;
		
		try {
			//set pointer to the beginning of the file
			raf.seek(0);
			//while not found or to the end of the file, loop through file
			for(i = 0; i < raf.length() && !found; i+=Client.SIZE) 
			{
				//create Client object to hold client found
				Client c = readBinaryFile();
				
				//if Client's id matches the parameter id, the Client had been found
				if(c.getClientId() == id) 
				{
					//the position of the Client in the binary file is the i
					thePosition = i;
					found = true;
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(i);
		return thePosition;
	}
	
	/**
	 * searchId searches for the client id entered
	 * @param searchItem is the client it the user
	 * entered.
	 * @return an ArrayList of the results
	 */
	private ArrayList<Client> searchId(String searchItem)
	{
		//ArrayList to hold results
		ArrayList<Client> searchResults = new ArrayList<>();
		//parse searchItem to a long
		long id = Long.parseLong(searchItem);
		//stop searching through RandsomAccessFile once id is found
		boolean found = false;
		
		try {
			//put pointer at the beginning of the file
			raf.seek(0);
			/*
			 * while id is not found and not at the end of RandomAccess File, 
			 * search for id. 
			 */
			for(int i = 0; i < raf.length() && !found; i +=Client.SIZE) 
			{
				//client object for the client with id that matches
				Client c = readBinaryFile();
				/*
				 * if searchItem matches client id and record is active
				 * add Client to searchResult Array. 
				 */
				if(id == c.getClientId() && c.isActive()) 
				{
					found = true;
					searchResults.add(c);
					
				}
				else if(id == c.getClientId() && !c.isActive()) 
				{
					found = true;
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
		
	}
	
	/**
	 * Method to search for clientLastName in 
	 * the RandomAccessFile. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchLastName(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		
		try {
			//start point at beginning of raf file
			raf.seek(0);
			//For the length of the file search for the criteria
			for(int i = 0; i < raf.length(); i += Client.SIZE) 
			{
				Client c = readBinaryFile();
				//if search criteria found add to clientList ArrayList
				if(searchItem.equalsIgnoreCase(c.getLastName()) && c.isActive()) 
				{
					searchResults.add(c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
		
	}
	
	/**
	 * Method to search for clientFirstName in 
	 * the RandomAccessFile. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchFirstName(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		
		try {
			//start point at beginning of raf file
			raf.seek(0);
			//For the length of the file search for the criteria
			for(int i = 0; i < raf.length(); i += Client.getSize()) 
			{
				Client c = readBinaryFile();
				//if search criteria found add to clientList ArrayList
				if(searchItem.equalsIgnoreCase(c.getFirstName()) && c.isActive()) 
				{
					searchResults.add(c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
		
	}
	
	/**
	 * Method to search for postalCode in 
	 * the RandomAccessFile. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchPostalCode(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		
		try {
			//start point at beginning of raf file
			raf.seek(0);
			//For the length of the file search for the criteria
			for(int i = 0; i < raf.length(); i += Client.getSize()) 
			{
				Client c = readBinaryFile();
				//if search criteria found add to clientList ArrayList
				if(searchItem.equalsIgnoreCase(c.getPostalCode()) && c.isActive()) 
				{
					searchResults.add(c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
		
	}
	
	/**
	 * Method to search for phoneNumber in 
	 * the RandomAccessFile. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchPhoneNumber(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		
		try {
			//start point at beginning of raf file
			raf.seek(0);
			//For the length of the file search for the criteria
			for(int i = 0; i < raf.length(); i += Client.getSize()) 
			{
				Client c = readBinaryFile();
				//if search criteria found add to clientList ArrayList
				if(searchItem.equalsIgnoreCase(c.getPhoneNumber()) && c.isActive()) 
				{
					searchResults.add(c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
		
	}
	
	/**
	 * Method to search for clientType in 
	 * the RandomAccessFile. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchClientType(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		char type = searchItem.charAt(0);
		
		try {
			//start point at beginning of raf file
			raf.seek(0);
			//For the length of the file search for the criteria
			for(int i = 0; i < raf.length(); i += Client.getSize()) 
			{
				Client c = readBinaryFile();
				//if search criteria found add to clientList ArrayList
				if(type == c.getClientType() && c.isActive()) 
				{
					searchResults.add(c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
		
	}
	
}
