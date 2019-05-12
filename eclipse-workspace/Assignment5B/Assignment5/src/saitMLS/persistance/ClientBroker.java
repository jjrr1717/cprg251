/**
 * ClientBroker is a class that contains all 
 * the methods that will handles all the 
 * interactions with a database. The database
 * contains information about Clients. 
 * 
 *  @author Jocelyn Wegen
 *  @version April 16, 2019
 */

package saitMLS.persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * highId holds the highest Client id number
	 */
    public long highId = 0;
    /**
     * A Connection with the Database
     */
    private Connection con;
    /**
     * A Statement used for queries and updates
     */
    private Statement stmt;
    /**
     * A ResultSet that will contain the row data
     */
    private ResultSet result;
    /**
     * SQL statement to insert row
     */
    private static final String INSERT_STMT = "INSERT INTO CLIENTS (CLIENT_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE,PHONE_NUMBER, CLIENT_TYPE) VALUES(?,?,?,?,?,?,?)";
    /**
     * SQL statement to delete row
     */
    private static final String DELETE_STMT = "DELETE FROM CLIENTS WHERE CLIENT_ID = ?";
    /**
     * SQL statement to update row
     */
    private static final String UPDATE_STMT = "UPDATE CLIENTS SET FIRST_NAME = ?, LAST_NAME = ?, ADDRESS = ?, POSTAL_CODE = ?,PHONE_NUMBER = ?, CLIENT_TYPE = ? WHERE CLIENT_ID = ?";
    /**
     * SQL statement to select client_id
     */
    private static final String SELECT_ID_STMT = "SELECT * FROM CLIENTS WHERE CLIENT_ID = ?";
    /**
     * SQL statement to select last_name
     */
    private static final String SELECT_LASTNAME_STMT = "SELECT * FROM CLIENTS WHERE UPPER(LAST_NAME) = ?";
    /**
     * SQL statement to select first_name
     */
    private static final String SELECT_FIRSTNAME_STMT = "SELECT * FROM CLIENTS WHERE UPPER(FIRST_NAME) = ?";
    /**
     * SQL statement to select client type
     */
    private static final String SELECT_CLIENT_TYPE_STMT = "SELECT * FROM CLIENTS WHERE UPPER(CLIENT_TYPE) = ?";
    /**
     * SQL statement to select postal_code
     */
    private static final String SELECT_POSTAL_CODE_STMT = "SELECT * FROM CLIENTS WHERE POSTAL_CODE = ?";
    /**
     * SQL statement to get high id
     */
    private static final String SELECT_HIGH_ID_STMT = "SELECT MAX(CLIENT_ID) FROM CLIENTS";
	/***************Constructor********************/
	
	/**
	 * private constructor to only allow one
	 * object of the ClientBroker.  Accepts no
	 * parameters. 
	 */
	private ClientBroker() 
	{
		//set the connection
		setConnection();
		
		if(!tableExists()){
			//create table if it doesn't exist
			createTable();
			//load data after you create the table
			loadData();
		}	
	}
	

	
	/*************************public methods*********************/
	
	/**
	 * Method to give access to the clientBroker.
	 * @return the broker for the ClientBroker
	 */
	public static ClientBroker getClientBroker() 
	{
		//create an instance of ClientBroker
		broker = new ClientBroker();
		
		return broker;
	}
	
	/**
	 * Method to save a new client when the id is at zero.
	 * If the id is not at zero than it updates the
	 * client if there were any changes made.  
	 * @return a boolean if the client was successfully updated
	 * or added.
	 */
	public boolean persist(Object o) {
		boolean clientAdded = false;
		
		//create client object
		Client c = (Client) o;
		
		//if client id is 0 then add client to the database
		if(c.getClientId() == 0l) 
		{
			//make sure correct id is in highId
			findHighestCurrentId();
			//insert the new client record
			insertRecord(++highId, 
					c.getFirstName(),
					c.getLastName(),
					c.getAddress(),
					c.getPostalCode(),
					c.getPhoneNumber(),
					c.getClientType());
				//set the client_id so it is visible in GUI
				c.setClientId(highId);
				clientAdded = true;
			}
			
		
		/*
		 * if the client id is not 0 then it already exists 
		 * and record should be updated to current 
		 * information.
		 */
		else if(c.getClientId() > 0l) 
		{
			//update all fields with the correct changes
			updateClient(c.getFirstName(), c.getLastName(), c.getAddress(), c.getPostalCode(), c.getPhoneNumber(), c.getClientType(), c.getClientId());
			clientAdded = true;
		}
		return clientAdded;
		}
		
	/**
	 * Method to remove a client from the database.
	 * @param o is an Object the user wishes to remove
	 */
	public boolean remove(Object o) {
		boolean clientRemoved = false;
		//create a client object 
		Client c = (Client) o;
		//delete the row that matches the client_id
		deleteRecord(c.getClientId());
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
	
/*****************private methods***********************/
	/**
	 * Method to load the data from a text file to the
	 * Oracle database. 
	 */
	private void loadData(){
		//Array to hold components of line from txt file
		String[] line;
		//Open text file
		File inputFile = new File(INPUT_FILE);
		
		//Read text file
		Scanner readFile;
		
		try {
			readFile = new Scanner(inputFile);
			//loop through txt file
			while(readFile.hasNextLine()){
				//put each line into line array
				line = readFile.nextLine().split(";");
			
				//find highest current id
				findHighestCurrentId();
				//increase id by 1 (for the next client)
				++highId;
				//add client to the database
				insertRecord(highId, 
						line[0], 
						line[1], 
						line[2], 
						line[3], 
						line[4], 
						line[5].charAt(0));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to update the clients fields
	 * @param first_name a String for the client's first name.
	 * @param last_name a String for the client's last name.
	 * @param address a String for the client's address.
	 * @param postal_code a String for the client's postal code.
	 * @param phone_number a String for the client's phone number.
	 * @param client_type a char for the client's type
	 * @param id a long for the client's id number
	 */
	private void updateClient(String first_name, 
			String last_name, 
			String address, 
			String postal_code, 
			String phone_number, 
			char client_type,
			long id){
		//prepare the SQL statement
		PreparedStatement pstmt;
		//create that statement with appropriate data and execute
		try {
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1,first_name);
			pstmt.setString(2, last_name);
			pstmt.setString(3, address);
			pstmt.setString(4, postal_code);
			pstmt.setString(5, phone_number);
			pstmt.setString(6,String.valueOf(client_type));
			pstmt.setLong(7, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to insert a new record in the database.
	 * @param id a long for a client's id.
	 * @param first_name a String for the client's first name.
	 * @param last_name a String for the client's last name.
	 * @param address a String for the client's address.
	 * @param postal_code a String for the client's postal code.
	 * @param phone_number a String for the client's phone number.
	 * @param client_type a char for the client's type.
	 */
	private void insertRecord(long id, 
			String first_name, 
			String last_name, 
			String address, 
			String postal_code, 
			String phone_number, 
			char client_type){
		
			//prepare the SQL statement
			PreparedStatement pstmt;
			//enter the data for the statement and execute the update
			try {
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setLong(1, id);
				pstmt.setString(2,first_name);
				pstmt.setString(3, last_name);
				pstmt.setString(4, address);
				pstmt.setString(5, postal_code);
				pstmt.setString(6, phone_number);
				pstmt.setString(7,String.valueOf(client_type));
			
			pstmt.executeUpdate();
			
			pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Method to delete a row from the database.
	 * @param id a long for the client's id. 
	 */
	private void deleteRecord(long id){
		//prepare the SQL statement
		PreparedStatement pstmt;
		//put the data in the statement and execute update.
		try {
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setLong(1, id);
			int rows = pstmt.executeUpdate();
			System.out.println("Rows Deleted: " + rows);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to create the connection to the Oracle database
	 */
	private void setConnection(){
		try {
			//get driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//get connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:SAIT", "CPRG250", "password");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to test if the table exists.
	 * @return a boolean, true is it exits, false if it doesn't.
	 */
	private boolean tableExists(){
		boolean exists = false;
		
		try {
			stmt = con.createStatement();
			//if the select statement causes an exception than exits will never be true.
			String sqlQuery = "SELECT * FROM CLIENTS";
			result = stmt.executeQuery(sqlQuery);
			exists = true;
			
			//close connection
			stmt.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
	/**
	 * Method to create the Clients table if it does not exist.
	 */
	private void createTable(){
		//statement to create client's table
		String createClientsTable = "CREATE TABLE CLIENTS (CLIENT_ID NUMBER, FIRST_NAME VARCHAR2(20), LAST_NAME VARCHAR2(20), ADDRESS VARCHAR2(50), POSTAL_CODE VARCHAR2(7),PHONE_NUMBER VARCHAR2(12), CLIENT_TYPE CHAR(1))";
		//execute update to create table
		try {
			PreparedStatement pstmt = con.prepareStatement(createClientsTable);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to find the highest id, 
	 * and assigns it to the highId.
	 */
	private void findHighestCurrentId() 
	{	
		//prepare SQL statement
		PreparedStatement pstmt;
		//execute query
		try {
			pstmt = con.prepareStatement(SELECT_HIGH_ID_STMT);
			result = pstmt.executeQuery();
			while(result.next()){
				//assign the highId as the result of the statement
				this.highId = result.getLong(1);
				//System.out.println("find " + highId);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
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
		//prepare statement
		PreparedStatement pstmt;
		//enter data into statement and execute query
		try {
			pstmt = con.prepareStatement(SELECT_ID_STMT);
			//pstmt.setString(1, "CLIENT_ID");
			pstmt.setLong(1, id);
			//System.out.println(id);
			result = pstmt.executeQuery();
			//CLIENT_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE,PHONE_NUMBER, CLIENT_TYPE
			while(result.next()){
				//put the results into variables
				long theId = result.getLong(1);
				//System.out.println(theId);
				String theFirstName = result.getString(2);
				String theLastName = result.getString(3);
				String theAddress = result.getString(4);
				String thePostalCode = result.getString(5);
				String thePhoneNumber = result.getString(6);
				char theClientType = result.getString(7).charAt(0);
				//create a client from the results
				Client aClient = new Client(theId, theFirstName, theLastName, theAddress, thePostalCode, thePhoneNumber, theClientType);
				//add client to searchResults
				searchResults.add(aClient);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		return searchResults;
		
	}
	
	/**
	 * Method to search for clientLastName in 
	 * the database. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchLastName(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		PreparedStatement pstmt;
		//make it case insensitive
		searchItem = searchItem.toUpperCase();
		
		try {
			pstmt = con.prepareStatement(SELECT_LASTNAME_STMT);
			pstmt.setString(1, searchItem);
			result = pstmt.executeQuery();
			//CLIENT_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE,PHONE_NUMBER, CLIENT_TYPE
			while(result.next()){
				long theId = result.getLong(1);
				String theFirstName = result.getString(2);
				String theLastName = result.getString(3);
				String theAddress = result.getString(4);
				String thePostalCode = result.getString(5);
				String thePhoneNumber = result.getString(6);
				char theClientType = result.getString(7).charAt(0);
				//create client
				Client aClient = new Client(theId, theFirstName, theLastName, theAddress, thePostalCode, thePhoneNumber, theClientType);
				//add client to searchResults
				searchResults.add(aClient);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		return searchResults;
		
	}
	
	/**
	 * Method to search for clientFirstName in 
	 * the database. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchFirstName(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		
		searchItem = searchItem.toUpperCase();
		
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(SELECT_FIRSTNAME_STMT);
			//pstmt.setString(1, "FIRST_NAME");
			pstmt.setString(1, searchItem);
			result = pstmt.executeQuery();
			//CLIENT_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE,PHONE_NUMBER, CLIENT_TYPE
			while(result.next()){
				long theId = result.getLong(1);
				String theFirstName = result.getString(2);
				String theLastName = result.getString(3);
				String theAddress = result.getString(4);
				String thePostalCode = result.getString(5);
				String thePhoneNumber = result.getString(6);
				char theClientType = result.getString(7).charAt(0);
				//create new client
				Client aClient = new Client(theId, theFirstName, theLastName, theAddress, thePostalCode, thePhoneNumber, theClientType);
				//add client to searchResults
				searchResults.add(aClient);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		return searchResults;
		
	}
	
	/**
	 * Method to search for postalCode in 
	 * the database. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchPostalCode(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(SELECT_POSTAL_CODE_STMT);
			pstmt.setString(1, searchItem);
			result = pstmt.executeQuery();
			//CLIENT_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE,PHONE_NUMBER, CLIENT_TYPE
			while(result.next()){
				long theId = result.getLong(1);
				String theFirstName = result.getString(2);
				String theLastName = result.getString(3);
				String theAddress = result.getString(4);
				String thePostalCode = result.getString(5);
				String thePhoneNumber = result.getString(6);
				char theClientType = result.getString(7).charAt(0);
				//create client
				Client aClient = new Client(theId, theFirstName, theLastName, theAddress, thePostalCode, thePhoneNumber, theClientType);
				//add client to searchResults
				searchResults.add(aClient);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		return searchResults;
		
	}

	
	/**
	 * Method to search for clientType in 
	 * the database. If found it will
	 * put Client into an Array. 
	 * @param searchItem the item to be searched for
	 * @return the Array containing the Client(s) found. 
	 */
	private ArrayList<Client> searchClientType(String searchItem)
	{
		//ArrayList to hold the search results
		ArrayList<Client> searchResults = new ArrayList<>();
		//change searchItem to char
		char type = searchItem.charAt(0);
		//make it case insensitive
		type = Character.toUpperCase(type);
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(SELECT_CLIENT_TYPE_STMT);
			pstmt.setString(1, String.valueOf(type));
			result = pstmt.executeQuery();
			//CLIENT_ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE,PHONE_NUMBER, CLIENT_TYPE
			while(result.next()){
				long theId = result.getLong(1);
				String theFirstName = result.getString(2);
				String theLastName = result.getString(3);
				String theAddress = result.getString(4);
				String thePostalCode = result.getString(5);
				String thePhoneNumber = result.getString(6);
				char theClientType = result.getString(7).charAt(0);
				//create client
				Client aClient = new Client(theId, theFirstName, theLastName, theAddress, thePostalCode, thePhoneNumber, theClientType);
				//add client to searchResultss
				searchResults.add(aClient);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InvalidPhoneNumberException e) {
			e.printStackTrace();
		} catch (InvalidPostalCodeException e) {
			e.printStackTrace();
		}
		return searchResults;
		
	}

	/**
	 * method to close broker and make broker null.  
	 */
	@Override
	public void closeBroker() {
		broker = null;
		
	}
	
}
