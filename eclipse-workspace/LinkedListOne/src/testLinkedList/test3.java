package testLinkedList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import linkedListOne.SLL;
import linkedListOne.SLLNode;

public class test3 {
	
	SLL list;
	private SLLNode head;
	
	@Before
	public void initialize() 
	{
		list = new SLL();
	}
	
	@After
	public void close() 
	{
		list = null;
	}
	
	@Test
	public void testSLLCreation() 
	{
		
		assertEquals("Did not create SLL correctly ", true, list.isEmpty());
		assertEquals("The size of the list is not correct ", 0, list.size());
	}
	
	
	@Test
	public void testAdd() 
	{
		list.add("Hello");
		
		String element = (String) list.get(0);
		
		assertEquals("Hello", element);
	}
	
	@Test
	public void testSize() 
	{
		list.add("Hello");
		list.add("There");
		list.add("TheSize");
		
		int theSize = list.size();
		
		assertEquals(3, theSize);
	}
	
	@Test
	public void testAddAtPosition() 
	{
		
		list.add("There", 0);
		
		String indexedAdd = (String) list.get(0);
		
		assertEquals("There", indexedAdd);
		
	}
	
	@Test
	public void testClear() 
	{
		list.add("Hello");
		list.add("There");
		
		list.clear();
		
		int listSize = list.size();
		
		assertEquals(0, listSize);
		
		
		
	}
	
	@Test
	public void testRemove() 
	{
		
		boolean found = false;
		list.add("Hello");
		list.add("There");
		
		list.remove();
		
		String firstElement = (String) list.get(0);
		
		assertEquals("Hello", firstElement);
		
		//also test to make sure hello isn't there
		for(int i = 0; i < list.size(); i++) 
		{
			String element = (String) list.get(i);
			if(element.equals("There")) 
			{
				found = true;
			}
		}
		
		assertFalse(found);
	}
	
	@Test
	public void testGet() 
	{
		Object o = new String("Hello");
		Object o2 = new String("There");
		Object o3 = new String("TheSize");
		
		list.add(o);
		list.add(o2);
		list.add(o3);
		
		String theElement = (String) list.get();
		assertEquals("TheSize", theElement);
	}
	
	@Test
	public void testGetLast() 
	{
		Object o = new String("Hello");
		Object o2 = new String("There");
		Object o3 = new String("TheSize");
		
		list.add(o);
		list.add(o2);
		list.add(o3);
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.getLast());
		
		String theElement = (String) list.getLast();
		assertEquals("Hello", theElement);
	}
	
	@Test
	public void testGetAtPosition() 
	{
		Object o = new String("Hello");
		Object o2 = new String("There");
		Object o3 = new String("TheSize");
		
		list.add(o);
		list.add(o2);
		list.add(o3);
		
		String p1 = (String) list.get(0); 
		String p2 = (String) list.get(1);
		String p3 = (String) list.get(2);
		
		assertEquals("TheSize", p1);
		assertEquals("There", p2);
		assertEquals("Hello", p3);
	}
	
	@Test
	public void testRemoveLast() 
	{
		Object o = new String("Hello");
		Object o2 = new String("There");
		Object o3 = new String("TheSize");
		
		list.add(o);
		list.add(o2);
		list.add(o3);
		
		list.removeLast();
		
		String theLastElement = (String) list.getLast();
		System.out.println(theLastElement);
		
		assertEquals("Hello", theLastElement);
	}
	
	@Test
	public void testIsEmpty() 
	{
		boolean empty = list.isEmpty();
		assertTrue(empty);
	}
	
	@Test
	public void testIsEmptyIfNotEmpty() 
	{
		Object o = new String("Hello");
		Object o2 = new String("There");
		Object o3 = new String("TheSize");
		
		list.add(o);
		list.add(o2);
		list.add(o3);
		
		boolean empty = list.isEmpty();
		
		assertFalse(empty);
	}
	
	/*@Test
	public  void testIndexOf() 
	{
		
	}*/
}
