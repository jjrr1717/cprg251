 package linkedListOne;

public class SLL implements List {
	
	private SLLNode head;
	private int size;
	
	public SLL() 
	{
		head = null;
		size = 0;
	}

	@Override
	public boolean append(Object element) {
		boolean status = false;
		
		SLLNode insert = new SLLNode(element);
		
		if(!isEmpty()) 
		{
			//start at the head
			SLLNode current = head;
			while(current.getNext() !=null) 
			{
				//move the current down the line until it gets to the end
				current = current.getNext();
			}
			//now that you are at the end put the new node into the current position, which is the end
			current.setNext(insert);
			
			size++;
			status = true;
		}
		else 
		{
			head = insert; 
			size++;
			status = true;
		}
		
		
		return status;
	}

	@Override
	public boolean add(Object element) {
		boolean status = false;
		
		if(!isEmpty()) 
		{
			//put pointer at first node first
			SLLNode current = head;
			
			//put current in object
			SLLNode insert = new SLLNode(element);
			
			//put the element inside the current SLLNode because adding it to the first of the list
			head = insert;
			
			//connect to the rest of the chain - current. Making the old head the next node in the list. 
			insert.setNext(current);
			
			status = true;
			
			//increase the size of the list
			size++;
			
		}
		else 
		{
			//now this is when the list is empty
			//set the head.  The element will be the head. 
			head = new SLLNode(element);
			status = true;
			size++;
		}
		
		return status;
	}

	@Override
	public boolean add(Object element, int position) {
		
		if(position < 0 || position > size()) 
		{
			throw new IndexOutOfBoundsException("The index is outside the bounds!");
		}
		
		boolean status = false;
		if(!isEmpty()) 
		{
			//if position is 0 want to add to the beginning
			if(position ==0) 
			{
				//add the element to the beginning (add already does this). 
				add(element);
			}
			//if position equals the total size then it should be appended to the end. 
			else if(position ==size)
			{
				//add the element to the end (append already does this).  
				append(element);
			}
			else 
			{
				int i;
				SLLNode previous;
				previous = head;
				for(i = 0; i<position -1; i++) 
				{
					previous = previous.getNext();
				}
				SLLNode insert = new SLLNode(element);
				
				insert.setNext(previous.getNext());
				
				previous.setNext(insert);
				status = true;
				size++;
				
			}
		}
		else 
		{
			//if it's empty
			head = new SLLNode(element);
			status = true;
			size++;
		}
		return status;
	}

	@Override
	public void clear() {
		//this removes the link (the garbage will get ride of the nodes later
		head = null;
		size = 0;
		
	}

	@Override
	public Object remove() {
		if(!isEmpty()) 
		{
			SLLNode del = head;
			head  = head.getNext();
			Object item = del.getElement();
			size--;
			return item;
		}
		return null;
	}

	@Override
	public Object removeLast() {
		Object item = null;
		
		if(!isEmpty() && size ==1) 
		{
			item =  head.getElement();
			head = null;
			size--;
			return item;
		}
		else if(!isEmpty()) 
		{
			SLLNode del = head.getNext();
			SLLNode prev = head;
			while(del.getNext() !=null) 
			{
				del = del.getNext();
				prev = prev.getNext();
			}
			
			item = del.getElement();
			prev.setNext(null);
			size--;
			return item;
		}
		return null;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		
		if(index <0 || index > size()) 
		{
			throw new IndexOutOfBoundsException("Out of Bounds!");
		}
		
		if(!isEmpty()) 
		{
			if(index ==0) 
			{
				remove();
			}
			else 
			{
			//first start from the beginning
			SLLNode del = head.getNext();
			
			//get previous node
			SLLNode prev = head;
			
			//loop through until you get to the indexed node
			for(int i = 0; i<index; i++) 
			{
				del = del.getNext();
				prev = prev.getNext();
			}
			
			//now we have the node to be deleted, put it in Object
			Object item = del.getElement();
			//put the previous node into the position we deleted
			prev.setNext(del.getNext());
			//decrease size
			size--;
			
			return item;
			}
		}
		
		return null;
	}

	@Override
	public Object get() {
		if(!isEmpty()) 
		{
			Object item = head.getElement();
			return item;
		}
		return null;
	}

	@Override
	public Object getLast() {
		//create object
		Object  item = null;
		
		//create current node 
		SLLNode current = head;
		
		if(!isEmpty()) 
		{
			//loop through list until you get to last node
			for(int i = 0; i<size; i++) 
			{
				current = current.getNext();
			}
				//assign object the last node
				item = current.getElement();
		}
		return item;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		
		//set up a Object for found element
		Object foundNode = null;
		
		//set up SLLNode for start of list
		SLLNode current = head;
		

		
		if(index < 0 || index > size) 
		{
			throw new IndexOutOfBoundsException("Out of Bounds");
		}
		
		
		if(!isEmpty())
		{
			//loop through linked list until reach index
			for(int i = 0; i< index; i++) 
			{
				//set the current as that node
				current = current.getNext();
				//set the object as the current node until it reaches the end
				
			}
			foundNode = current.getElement();
			return foundNode;
		}
		
		
		
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head ==null;
		
	}

	@Override
	public int indexOf(Object element) {
		//create index 
		int i = -1;
		//create boolean for found item
		boolean found = false;
		
		//create starting node
		SLLNode current = head;
		
		if(!isEmpty()) 
		{
			//loop through list until find element
			while(current.getNext()!=null) 
			{
				i++;
				//compare the element to the current head
				if(element.equals(current.getElement())) 
				{
					return i;
				}
				
				current = current.getNext();
			}
		}
		return -1;
	}

	@Override
	public boolean contains(Object element) {
		boolean status = false;
		
		//set up my current location - which will be head
		SLLNode current = head;
		//loop through the list until it reaches the end or it finds a match
		while(current !=null) 
		{
			//if match is found between current and the element then status is true, otherwise false
			if(element.equals(current.getElement())) 
			{
				status = true;
			}
			current = current.getNext();
		}
		
		
		return status;
	}

	@Override
	public Object set(Object element, int position) throws IndexOutOfBoundsException {
		if(position <0 || position > size()) 
		{
			throw new IndexOutOfBoundsException("Out of bounds!");
		}
		
		//put the old object into an Object
		Object old = null;
		
		if(!isEmpty()) 
		{
			if(position ==0) 
			{
				old = head.getElement();
				head.setElement(element);
			}
			else if(position ==size) 
			{
				SLLNode set = head;
				while(set.getNext() !=null) 
				{
					set = set.getNext();
				}
				old = set.getElement();
				set.setElement(element);
			}
			else 
			{
				SLLNode set = head;
				for(int i = 0; i<position; i++) 
				{
					set = set.getNext();
				}
				old = set.getElement();
				set.setElement(element);
			}
		}
		return old;
	}
	
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
