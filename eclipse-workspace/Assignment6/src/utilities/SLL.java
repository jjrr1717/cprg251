/**
 * Class that contains functionality for a singly linked list.
 * @author Jocelyn Wegen
 * @version March 31, 2019
 */
package utilities;

public class SLL implements List {

	// attributes
	/**
	 * SLLNode for the head of the list
	 */
	private SLLNode head;
	/**
	 * int for the size of the list
	 */
	private int size;

	/********************** Constructor *********************/

	/**
	 * Constructor to initialize the list
	 */
	public SLL() {
		// start empty list
		head = null;
		// start list at size 0
		size = 0;
	}

	/**************************** Public Methods ***********/

	/**
	 * Method to append an element to the end of this list.
	 * 
	 * @return a boolean if element added = true, otherwise false
	 */
	public boolean append(Object element) {
		boolean appended = false;
		// create a node for the new element
		SLLNode newNode = new SLLNode(element);

		// if list is not empty must traverse to end of list
		if (!isEmpty()) {
			// create a node for the current position - starts at head
			SLLNode current = head;

			// while the next node is not null
			while (current.getNext() != null) {
				// go through list
				current = current.getNext();
			}

			// now that current node is at end, assign the next node as newNode
			current.setNext(newNode);

			// node appended
			appended = true;

			// increase size of list
			size++;
		} else {
			// if list is empty than newNode become the head
			head = newNode;

			// node appended
			appended = true;

			// increase size of list
			size++;
		}
		return appended;
	}

	/**
	 * Method to add an element to the first of the list
	 * 
	 * @return boolean true is element added, otherwise false
	 */
	public boolean add(Object element) {
		boolean status = false;

		// create a node for the element
		SLLNode newNode = new SLLNode(element);

		// if element is not empty
		if (!isEmpty()) {
			// create current position - start at head
			SLLNode current = head;

			// newNode will now become the head
			head = newNode;

			// point the newNode to the current head
			newNode.setNext(current);
			status = true;
			size++;
		} else {
			// if list is empty then newNode is head
			head = newNode;
			status = true;
			size++;
		}
		return status;
	}

	/**
	 * Method to add element to specified index position
	 * 
	 * @return boolean true is element added, otherwise false
	 */
	public boolean add(Object element, int position) throws IndexOutOfBoundsException {
		boolean status = false;

		// make sure position is not out of bounds
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Out of Bounds!");
		}

		// create a node for the element
		SLLNode newNode = new SLLNode(element);

		// if list is not empty traverse through list until hit position
		if (!isEmpty()) {
			// if position is zero it is just adding it
			if (position == 0) {
				add(element);
			} else if (position == size) {
				// if position is the size then just appending it
				append(element);
			} else {
				// position of the current node - start at head
				SLLNode current = head;
				// position of the node after the current node
				SLLNode nextOfCurrent = head.getNext();

				// traverse until position -1 because want nextOfCurrent in position
				for (int i = 0; i < position - 1; i++) {
					// move the current position
					current = current.getNext();
					// move the nextOfCurrent position
					nextOfCurrent = nextOfCurrent.getNext();

				}

				// the next node of current will be new node
				current.setNext(newNode);
				// the next node of newNode will be what was nextOfCurrent
				newNode.setNext(nextOfCurrent);

				status = true;
				size++;
			}
		} else {
			// if list is empty then newNode is the head
			head = newNode;
			status = true;
			size++;
		}
		return status;
	}

	/**
	 * Method to clear the whole list
	 */
	public void clear() {
		// set head as null = no links
		head = null;

		// reset size to 0
		size = 0;

	}

	/**
	 * Method to remove first element from the list
	 * 
	 * @return an Object of the removed item
	 */
	public Object remove() {
		// if list is not empty
		if (!isEmpty()) {
			// get current node
			SLLNode current = head;
			// returns an object so...
			Object deletedItem = current.getElement();
			// assign the head to the next node
			head = head.getNext();
			// decrease size
			size--;
			return deletedItem;
		}
		// if nothing in the list then return null
		return null;
	}

	/**
	 * Method to remove the last element from the list
	 */
	public Object removeLast() {

		Object deletedItem = null;
		// if not empty and only one item in list
		if (!isEmpty() && size == 1) {
			// put the head (the only item) into the object
			deletedItem = head.getElement();
			// delete by making the head null
			head = null;
			// decrease size
			size--;
		} else if (!isEmpty()) {
			// get the current position and the next element of current
			SLLNode current = head;
			SLLNode nextOfCurrent = current.getNext();
			// traverse through list until it reaches last element
			while (nextOfCurrent.getNext() != null) {
				current = current.getNext();
				nextOfCurrent = nextOfCurrent.getNext();
			}
			// returns an object
			deletedItem = nextOfCurrent.getElement();
			current.setNext(null);
			// decrease size
			size--;

			return deletedItem;

		}
		// if list is empty return null
		return null;
	}

	/**
	 * Method to remove element at specified position
	 * 
	 * @return the Object that has been removed
	 */
	public Object remove(int position) throws IndexOutOfBoundsException {
		// check to see if position is out of bounds
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Out of Bounds!");
		}

		// first get current node - starts at head
		SLLNode current = head;

		// create object to hold deleted item
		Object deletedItem = null;
		if (!isEmpty()) {
			// if position is 0 then remove the first node
			if (position == 0) {
				remove();
				size--;
			} else if (position == size) {
				// if position is the last element removeLast
				removeLast();
				size--;
			} else {
				// if position is somewhere in the middle of the list
				SLLNode previous = head;
				SLLNode delete = head.getNext();

				// traverse through list
				for (int i = 0; i < position-1; i++) {
					previous = previous.getNext();
					delete = delete.getNext();
				}

				// assign the references to new node
				previous.setNext(delete.getNext());
				deletedItem = delete.getElement();
				size--;
				return deletedItem;
			}

		}
		return null;
	}

	/**
	 * Method to return the first element in the list
	 */
	public Object get() {

		if (!isEmpty()) {
			// create an object to hold the element
			Object theElement = head.getElement();
			return theElement;
		}
		return null;
	}

	/**
	 * Method to get the last element in the list
	 * 
	 * @return the last Object in the list
	 */
	public Object getLast() {
		// create an object to hold the element
		Object theElement = null;

		// get the current node - start at the head
		SLLNode current = head;

		if (!isEmpty()) {

			// loop through the whole list to the last node
			for (int i = 0; i < size-1; i++) {
				current = current.getNext();
			}

			// assign the last node to the Object
			theElement = current.getElement();

		}
		return theElement;
	}

	/**
	 * Method to get the element at a specified position in the list
	 * 
	 * @return an Object at the specified position
	 */
	public Object get(int position) throws IndexOutOfBoundsException {
		// check to make sure position is not out of bounds
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Out of Bounds!");
		}

		// create an object to hold the element
		Object theElement = null;

		// create the current node - start at head
		SLLNode current = head;

		if (!isEmpty()) {
			// loop through the list to the position
			for (int i = 0; i < position; i++) {
				current = current.getNext();
			}

			// assign the current node to the element
			theElement = current.getElement();
			return theElement;
		}

		return null;
	}

	/**
	 * Method that sets/replaces an element in the list at a specified position
	 * @return the replaced Object
	 */
	public Object set(Object element, int position) throws IndexOutOfBoundsException {
		// check to make sure position is within bounds
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Out of Bounds!");
		}
		
		//create an Object to hold the element that will be replaced
		Object replacedElement = null;

		// create the previous node
		SLLNode current = head;
		
		if (!isEmpty()) {
			//if position is zero replace the first element
			if(position ==0) 
			{
				replacedElement = head.getElement();
				head.setElement(element);
			}
			else if(position ==size) 
			{
				//if the position is the end of the list, replace the last item
				while(current.getNext() !=null) 
				{
					current= current.getNext();
				}
			
				replacedElement = current.getElement();
				current.setElement(element);
			}
			else 
			{
				// loop through the list
				for (int i = 0; i < position; i++) {
					current = current.getNext();
					
				}
				replacedElement = current.getElement();
				current.setElement(element);
				
				//assign the replaced element object the element replaced
				

				// assign the previous node to the newNode
				current.setElement(element);

			}

		}
		return replacedElement;
	}

	/**
	 * Method that checks if a specified element exists in the list
	 * @return a boolean, true if element found, otherwise false
	 */
	public boolean contains(Object element) {
		boolean status = false;
		
		//set up current node - start at head
		SLLNode current = head;
		//loop through the list to see if the element exists
		while(current.getNext() != null) 
		{
			//if the element in the current node matches the element than return true
			if(current.getElement().equals(element)) 
			{
				status = true;
			}
			
			//make sure it loops again
			current = current.getNext();
		}
		return status;
	}

	/**
	 * Method to return the index of a specified element
	 * @return an int of the index position
	 */
	public int indexOf(Object element) {

		
		//return -1 if element is not found
		int i = -1;
		
		//create the current node - start at head
		SLLNode current = head;
		
		//loop through the list to find a match to return position
		while(current.getNext() != null) 
		{
			//count the index
			i++;
			//if match found return the index
			if(current.getElement().equals(element)) 
			{
				//return the index
				return i;
			}
			
			//make sure it loops again
			current = current.getNext();
		}
		return -1;
	}

	/**
	 * Method to return the size of the list
	 * @return an int that is the size of the list
	 */
	public int size() {
		// return size (have been keeping track of it in methods
		return size;
	}

	/**
	 * Method to test if list is empty
	 */
	public boolean isEmpty() {
		return (head == null);
	}
	
	
	/**
	 * Method to print the list... just to see it
	 */
	public void printList() 
	{
		SLLNode current = head;
		for(int i =0; i<size(); i++) 
		{
			System.out.println(current.getElement());
			current = current.getNext();
		}
	}

}
