package linkedListOne;

public interface List {
	
	public boolean append(Object element);
	//add as the first node
	public boolean add(Object element);
	public boolean add(Object element, int position);
	public void clear();
	
	//remove the first node
	//so we can see what we removed
	public Object remove();
	public Object removeLast();
	public Object remove(int index);
	
	//return first node without removing it
	public Object get();
	public Object getLast();
	public Object get(int index);
	
	public int size();
	public boolean isEmpty();
	
	public int indexOf(Object element);
	public boolean contains(Object element);
	
	public Object set(Object element, int position)throws IndexOutOfBoundsException;
	
	

}
