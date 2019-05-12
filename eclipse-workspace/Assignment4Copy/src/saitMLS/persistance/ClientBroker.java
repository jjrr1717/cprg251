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


import saitMLS.problemDomain.Client;

public class ClientBroker implements Broker {
	
	private static ClientBroker broker;
	private static final String INPUT_FILE = "res/clients.txt";
	private static final String RANDOM_FILE = "res/clients.bin";
	private static final String MODE = "rw";
	private ArrayList<Client> clientList;
    public long highId = 0;
	private long serializedUId;
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
	 * @return
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
	 * Method to see if binary file is created
	 * and holds the proper data.  
	 */
	public void printClient() 
	{
		long i;
		try {
			raf.seek(0);
			System.out.print(raf.length());
			for(i = 0; i<raf.length(); i+=Client.getSize()) 
			{
				System.out.println(raf.getFilePointer());
				Client c = readBinaryFile();
				
				if(c.isActive()) {
					System.out.print(c);
					System.out.println();
				}
			}
			System.out.print(i - Client.SIZE);
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
		//once the client is found end the loop
		boolean found = false;
		/*
		 * if the client already exists change the 
		 * active boolean to true - instead of adding
		 * a new client. 
		 */
		boolean theSame = false;
		long clientPosition = 0;
		
		//Add a new Client to raf
		
		//create client object
		Client c = (Client) o;
		
		//put the pointer to the last position of the raf file. 
		if(c.getClientId() == 0) 
		{
			try {
				
				//search to make sure client doesn't already exists
				for(int i = 0; i<raf.length() && !found; i++) 
				{
					/*
					 * create a client off each entry in the binary 
					 * file to compare with the client the user
					 * entered
					 */
					Client comparedClient = readBinaryFile();
					//compare the clients to see if they are the same
					theSame = comparedClient.equals(c);
					clientPosition = findClientId(comparedClient.getClientId());
				}
				
				//if the clients
				if(!theSame) 
				{
					raf.seek(Client.SIZE);
				
					//write the client information to the raf file
					raf.writeBoolean(true);
					raf.writeLong(highId + 1);
					raf.writeUTF(padString(c.getFirstName(),20));
					raf.writeUTF(padString(c.getLastName(), 20));
					raf.writeUTF(padString(c.getAddress(), 50));
					raf.writeUTF(padString(c.getPostalCode(),7));
					raf.writeUTF(padString(c.getPhoneNumber(),12));
					raf.writeChar(c.getClientType());
					
					clientAdded = true;
				}
				else 
				{
					raf.seek(clientPosition);
					raf.writeBoolean(true);
					clientAdded = true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else 
		{
			//write the client information to the raf file
			try {
				raf.writeBoolean(true);
				raf.writeLong(c.getClientId());
				raf.writeUTF(padString(c.getFirstName(),20));
				raf.writeUTF(padString(c.getLastName(), 20));
				raf.writeUTF(padString(c.getAddress(), 50));
				raf.writeUTF(padString(c.getPostalCode(),7));
				raf.writeUTF(padString(c.getPhoneNumber(),12));
				raf.writeChar(c.getClientType());
				
				clientAdded = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return clientAdded;
	}

	@Override
	public boolean remove(Object o) {
		boolean found = false;
		//create a client object 
		Client c = (Client) o;
		
		//go through the raf file to find this client
		try {
			for(int i = 0; i < raf.length() && !found; i++) 
			{
				//create a client object for each entry to compare 
				Client clientToCompare = readBinaryFile();
				raf.seek(0);
				if(clientToCompare.getClientId() == c.getClientId()) 
				{
					raf.writeBoolean(false);
					found = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public List search(String searchItem, String type) {
		ArrayList<Client> searchResult = new ArrayList<>();
		
		
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
		if(inputString.length() >= length) 
		{
			return inputString;
		}
		
		StringBuilder sb = new StringBuilder();
		while(sb.length() < length - inputString.length()) 
		{
			sb.append(' ');
		}
		
		sb.append(inputString);
		
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
	 * @return
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
	
	private long findClientId(long id)
	{
		boolean found = false;
		long thePosition = 0;
		int i = 0;
		
		try {
			raf.seek(0);
			for(i = 0; i < raf.length() && !found; i+=Client.SIZE) 
			{
				Client c = readBinaryFile();
				
				if(c.getClientId() == id) 
				{
					thePosition = i;
					found = true;
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(i);
		return thePosition;
	}
	
	/**
	 * searchId searches for the client id entered
	 * @param searchItem is the client it the user
	 * entered.
	 * @return an ArrayList<Client> of the results
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
					found = false;
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
				if(searchItem.equalsIgnoreCase(c.getLastName())) 
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
				if(searchItem.equalsIgnoreCase(c.getFirstName())) 
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
				if(searchItem.equalsIgnoreCase(c.getPostalCode())) 
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
				if(searchItem.equalsIgnoreCase(c.getPhoneNumber())) 
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
				if(type == c.getClientType()) 
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
