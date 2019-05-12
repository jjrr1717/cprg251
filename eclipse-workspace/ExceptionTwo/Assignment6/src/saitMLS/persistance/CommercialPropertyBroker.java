/**
 *  CommercialPropertyBroker is a class to handle SAIT's
 *  Commercial properties. It implements Broker.  
 *  It gets commerical property information from
 *  a text file, and saves and handles the data
 *  in a serial file.  It uses a sinlgly linked list.  
 *  @author Jocelyn Wegen
 *  @version April 3, 2019
 */

package saitMLS.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;
import saitMLS.problemDomain.CommercialProperty;
import saitMLS.problemDomain.ResidentialProperty;
import java.util.List;
import utilities.SLL;

public class CommercialPropertyBroker implements Broker {

	/**
	 * broker to represent the CommercialPropertyBroker
	 */
	private static CommercialPropertyBroker broker;
	/**
	 * Input file that holds the commercial property information
	 */
	private static final String INPUT_FILE = "res/comprop.txt";
	/**
	 * Serialized file of the commercial properties
	 */
	private static final String SERIAL_FILENAME = "res/commercialProperty.ser";

	/**
	 * highId holds the highest property id number
	 */
	public long highId = 0;

	/**
	 * Singly Linked List to hold all the commercial data.
	 */
	private SLL propertyList;

	/*************** Constructor ********************/

	/**
	 * private constructor to only allow one object of the CommercialProperty.
	 * Accepts no parameters.
	 */
	private CommercialPropertyBroker() {
	}

	/**
	 * Method to give access to the broker.
	 * 
	 * @return the broker for the broker
	 */
	public static CommercialPropertyBroker getBroker() {
		// create an instance of the serialized file
		File serialFile = new File(SERIAL_FILENAME);

		/*
		 * if file exists create just create a CommericalProperty object, otherwise
		 * create CommericalPropertyBroker and create the serialized file. Only create
		 * CommericalPropertyBroker if it doesn't exist - when it is null.
		 */
		if (serialFile.exists()) {
			if (broker == null) {
				// create CommericalBroker object
				broker = new CommercialPropertyBroker();
				// read from the serial file that already exists
				broker.readFromSerialFile();
				// put the highId to the last id in the record
				broker.findHighestCurrentId();

			}
		} else {
			if (broker == null) {
				// create CommercialPropertyBroker object
				broker = new CommercialPropertyBroker();
				// Create a linked list from the txt file
				broker.loadLinkedList();
				// put the highId to the last id in the record
				broker.findHighestCurrentId();

			}
		}

		return broker;

	}

	/************************* public methods *********************/

	/**
	 * Method to print the contents in the linked list. Used for testing purposes
	 * only.
	 */
	public void printProperty() {
		// loop through list and print everything to console
		for (int i = 0; i < propertyList.size(); i++) {

			System.out.println(propertyList.get(i));

		}

	}

	/**
	 * Method to save a new CommercialProperty when the id is at zero. If the id is
	 * not at zero than it update the property if there were any changes made.
	 * 
	 * @return a boolean if the property was successfully updated or added.
	 */
	public boolean persist(Object o) {
		boolean propertyAdded = false;
		boolean found = false;

		// create client object
		CommercialProperty cp = (CommercialProperty) o;

		// if property id is 0 then add property to end of propertyList
		if (cp.getId() == 0l) {

			
			//make sure high id is correct
			findHighestCurrentId();
			//assign the highest Id to property
			cp.setId(++highId);
			propertyList.append(cp);
			propertyAdded = true;
		}
		/*
		 * if the property id is not 0 then it already exists and the propertyList
		 * should be updated.
		 */
		else if (cp.getId() > 0l) {
			// search for the id in the propertyList
			for (int i = 0; i < propertyList.size() || !found; i++) {
				CommercialProperty propertyInList = (CommercialProperty) propertyList.get(i);

				if (propertyInList.getId() == cp.getId()) {
					propertyList.set(cp, i);
					found = true;
				}
			}
		}
		return propertyAdded;
	}

	/**
	 * Method to remove a property from the propertyList.
	 * 
	 * @param o is an Object the user wishes to remove
	 */
	public boolean remove(Object o) {
		boolean removed = false;

		// create a property object
		CommercialProperty cp = (CommercialProperty) o;

		// find the property by matching id numbers
		for (int i = 0; i < propertyList.size() || !removed; i++) {
			// create property for property in list
			CommercialProperty propertyInList = (CommercialProperty) propertyList.get(i);

			// remove property from propertyList if matches id
			if (propertyInList.getId() == cp.getId()) {
				propertyList.remove(i);
				removed = true;

			}
		}

		return removed;
	}

	/**
	 * Method to search for a property based on user entry of search type and and
	 * item.
	 * 
	 * @param searchItem is a String of the user input to search for
	 * @param type is the type of data the user is searching for.
	 */
	public List search(String searchItem, String type) {
		// create an array of Clients found in search
		ArrayList<CommercialProperty> searchResult = new ArrayList<CommercialProperty>();

		// perform different search based on user input for type
		switch (type) {
		case "id":
			searchResult = searchId(searchItem);
			break;
		case "legal description":
			searchResult = searchLegalDescription(searchItem);
			break;
		case "quadrant":
			searchResult = searchQuadrant(searchItem);
			break;
		case "price":
			searchResult = searchAskingPrice(searchItem);
			break;
		default:
			System.out.println("Invalid");
		}

		return searchResult;
	}

	/**
	 * Method to close the the broker
	 */
	@Override
	public void closeBroker() {
		// make broker null
		broker = null;
		// save contents to the serial file
		writeToSerialFile();
	}

	/***************** private methods ***********************/
	/**
	 * Method to find the highest id, and assigns it to the highId.
	 */
	private void findHighestCurrentId() {
		// create ResidentialProperty to hold last item
		CommercialProperty cp = (CommercialProperty) propertyList.getLast();
		// to store the high id
		long highId = cp.getId();

		// assign highId to attribute
		this.highId = highId;

	}

	/**
	 * searchId searches for the property id entered
	 * 
	 * @param searchItem is the property the user entered.
	 * @return a SSL of the results
	 */
	private ArrayList<CommercialProperty> searchId(String searchItem) {
		// ArrayList to hold results
		ArrayList<CommercialProperty> searchResults = new ArrayList<CommercialProperty>();
		// parse searchItem to a long
		long id = Long.parseLong(searchItem);
		// stop searching through SLL once id is found
		boolean found = false;

		for (int i = 0; i < propertyList.size() || !found; i++) {
			// create property object
			CommercialProperty cp = (CommercialProperty) propertyList.get(i);
			if (cp.getId() == id) {
				searchResults.add(cp);
				found = true;
			}
		}

		return searchResults;

	}

	/**
	 * Method to search for Legal Description in the SLL. If found it will put
	 * property into a SLL.
	 * 
	 * @param searchItem the item to be searched for
	 * @return the SLL containing the properties found.
	 */
	private ArrayList<CommercialProperty> searchLegalDescription(String searchItem) {
		// SLL list to hold results
		ArrayList<CommercialProperty> searchResults = new ArrayList<CommercialProperty>();

		// loop through SLL
		for (int i = 0; i < propertyList.size(); i++) {
			CommercialProperty cp = (CommercialProperty) propertyList.get(i);
			if (cp.getLegalDescription().equalsIgnoreCase(searchItem))
				
			searchResults.add(cp);
		}

		return searchResults;

	}

	/**
	 * Method to search for property quadrant
	 * 
	 * @param searchItem the item to be searched for
	 * @return the SLL containing the search results.
	 */
	private ArrayList<CommercialProperty> searchQuadrant(String searchItem) {
		// SLL to hold the search results
		ArrayList<CommercialProperty> searchResults = new ArrayList<CommercialProperty>();

		// loop through SLL
		for (int i = 0; i < propertyList.size(); i++) {
			CommercialProperty cp = (CommercialProperty) propertyList.get(i);
			if (cp.getQuadrant().equalsIgnoreCase(searchItem))
				
			searchResults.add(cp);
		}

		return searchResults;

	}

	/**
	 * Method to search for prices greater than what user entered.
	 * 
	 * @param searchItem the item to be searched for
	 * @return the SLL containing the search results
	 */
	private ArrayList<CommercialProperty> searchAskingPrice(String searchItem) {
		// SLL to hold the search results
		ArrayList<CommercialProperty> searchResults = new ArrayList<CommercialProperty>();

		double price = Double.parseDouble(searchItem);

		// loop through SLL
		for (int i = 0; i < propertyList.size(); i++) {
			CommercialProperty cp = (CommercialProperty) propertyList.get(i);
			if (cp.getAskingPrice() >= price) {
				searchResults.add(cp);
			}
		}

		return searchResults;

	}

	/**
	 * Method to load property data from the
	 * text file into a singly linked list. 
	 */
	private void loadLinkedList() {
		// create the linked list
		propertyList = new SLL();

		// open the text file
		File inputFile = new File(INPUT_FILE);

		// scanner
		try {
			Scanner readFile = new Scanner(inputFile);

			// loop through file
			while (readFile.hasNextLine()) {
				// put the lines in an Array
				String[] line = readFile.nextLine().split(";");

				// increase the id by one per each property
				++highId;

				// create a residentialProperty from each line
				CommercialProperty cp = new CommercialProperty(highId, line[0], line[1], line[2], line[3],
						Double.parseDouble(line[4]), line[5], line[6], Integer.parseInt(line[7]));

				// append rp to the linked list
				propertyList.append(cp);

			}

			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLegalDescriptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to write contents in the singly linked list
	 * into a serial file. 
	 */
	private void writeToSerialFile() {
		try {
			//create file
			FileOutputStream theFile = new FileOutputStream(SERIAL_FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(theFile);

			//loop through list to add each object to serial file
			for (int i = 0; i < propertyList.size(); i++) {
				out.writeObject(propertyList.get(i));
			}
			//close serial file
			theFile.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to read the contents of the serial file
	 * and put into a singly linked list. 
	 * @return a singly linked list containing objects
	 * from the serial file. 
	 */
	private SLL readFromSerialFile() {
		//flag to stop reading from serial file
		boolean continueReading = true;
		//start propertyList fresh
		propertyList = new SLL();
		try {
			//open serial file
			FileInputStream file = new FileInputStream(SERIAL_FILENAME);
			ObjectInputStream in = new ObjectInputStream(file);

			// create an property
			CommercialProperty cp = null;
			//loop through file until every object is read
			do 
			{
				//read each object to put in linked list. 
				cp = (CommercialProperty) in.readObject();
				if(cp != null) 
				{
					propertyList.append(cp);
				}
				else 
				{
					continueReading = false;
				}
			}while(continueReading);

			//close the serial file
			file.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return propertyList;

	}
}
