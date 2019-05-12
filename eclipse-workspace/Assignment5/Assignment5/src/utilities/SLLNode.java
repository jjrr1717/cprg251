/**
 * Class to represent a node in a singly linked list
 * @author Jocelyn Wegen
 * @version March 31, 2019
 */

package utilities;

public class SLLNode {
	
	//attributes
	/**
	 * Element in the list.
	 */
	private Object element;
	/**
	 * Reference to the next node in the list.
	 */
	private SLLNode next;
	
	/***************Constructors*****************************/
	
	/**
	 * Constructor to create a node with a reference
	 * to the next node. 
	 * @param o the element to be created
	 * @param n a reference to the next node
	 */
	public SLLNode(Object o, SLLNode n) 
	{
		this.element = o;
		this.next = n;
	}
	
	/**
	 * Constructor for an element at the end of the list. 
	 * One that does not contain a reference to the next 
	 * node.
	 * @param o the element to be created
	 */
	public SLLNode(Object o) 
	{
		this.element = o;
	}
	
	/***************Setters and Getters**********************/
	
	/**
	 * Method to get element
	 * @return an element Object
	 */
	public Object getElement() {
		return element;
	}
	/**
	 * Method to set selement
	 * @param element an Object
	 */
	public void setElement(Object element) {
		this.element = element;
	}
	/**
	 * Method to get the next node
	 * @return the next node
	 */
	public SLLNode getNext() {
		return next;
	}
	/**
	 * Method to set the next node
	 * @param next is the next node
	 */
	public void setNext(SLLNode next) {
		this.next = next;
	}
	
}
