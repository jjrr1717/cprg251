package linkedListOne;

public class SLLNode {

	//attributes
	private Object element;
	private SLLNode next;
	
	
	public SLLNode(Object o, SLLNode n) 
	{
		this.element = 0;
		this.next = n;
	}
	
	//has this constructor because first element does not have a next
	public SLLNode(Object o) 
	{
		this.element = o;
		this.next = null;
	}
	
	public Object getElement() 
	{
		return element;
	}
	
	public void setElement(Object element) 
	{
		this.element = element;
	}
	
	public SLLNode getNext() 
	{
		return next;
	}
	
	public void setNext(SLLNode next) 
	{
		this.next = next;
	}
}
