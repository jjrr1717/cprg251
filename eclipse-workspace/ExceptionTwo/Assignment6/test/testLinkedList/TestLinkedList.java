package testLinkedList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.SLL;

public class TestLinkedList {

	// create a linked list
	SLL list;

	@Before
	public void initialize() {
		list = new SLL();
	}

	@After
	public void close() {
		list = null;
	}

	@Test
	public void testIsEmptyWhenEmpty() {

		assertTrue(list.isEmpty());

		int size = list.size();

		assertEquals(0, size);

	}

	@Test
	public void testIsEmptyWhenNotEmpty() {
		list.add(1);
		list.add(2);
		list.add(3);

		assertFalse(list.isEmpty());
	}

	@Test
	public void testAddAtStart() {
		// when list is empty
		list = new SLL();
		list.add(2);
		assertEquals("Failed when list is empty: ", 2, list.get(0));

		// when list has more than one element
		list.add(3);
		assertEquals("Failed when list is not empty: ", 2, list.get(1));
		assertEquals(3, list.get(0));

	}

	@Test
	public void testAppend() {
		// when list is empty
		list.append(10);
		assertEquals("Failed when list is empty: ", 10, list.get(0));

		// when list has more than one element
		list.append(11);
		assertEquals("Failed when list is not empty: ", 11, list.get(1));
		assertEquals("Failed when list is not empty and using getLast(): ", 11, list.getLast());
		// when list has more than two elements
		list.append(12);
		list.append(13);
		assertEquals("Failed when list has more than two elements: ", 13, list.getLast());
		assertEquals(13, list.get(3));
	}

	@Test
	public void testAddAtPosition() {
		// Test when list is empty
		list.add(1, 0);
		assertEquals("Failed when list is empty: ", 1, list.get(0));
		// test when list is not empty
		list.add(2, 1);
		assertEquals("Failed when list is not empty: ", 2, list.get(1));
		// test when adding element to position that already has an element
		list.add(3, 0);
		assertEquals("Failed to add at correct location: ", 3, list.get(0));
		// make sure the other elements have shifted down and still exist
		assertEquals("Failed to move existing elements: ", 1, list.get(1));
		assertEquals("Failed to move existing elements: ", 2, list.get(2));

		// test the exception handling for negative positions
		try {
			list.add(4, -1);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

		// test the exception handling for positions greater than size

		try {
			list.add(5, 8);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e2) {
			assertTrue(true);
		}

	}

	@Test
	public void testClear() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		list.clear();

		boolean empty = list.isEmpty();
		assertTrue(empty);
	}

	@Test
	public void testRemoveFirstElement() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// the first item is 4
		list.remove();

		assertEquals("Failed remove when list is not empty: ", 3, list.get(0));

		// make sure 4 didn't move anywhere down the list
		boolean found = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(i)) {
				found = true;
			}
		}

		assertFalse(found);

	}

	@Test
	public void testRemoveLast() {
		// if there is only one item in list
		list.add(10);
		list.removeLast();
		assertEquals("Failed when the list only contains one element: ", null, list.getLast());

		// if there is more than one element in the list
		list.add(11);
		list.add(12);
		list.add(13);

		// the last element will be 11
		list.removeLast();
		assertEquals("Failed when the list contains more than one element: ", 12, list.getLast());
	}

	@Test
	public void testRemoveAtPosition() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// remove the 2 at 2nd position
		list.remove(2);

		// 2nd position should now contain 1
		assertEquals(1, list.get(2));

		// make sure two is not anywhere else in the list
		boolean found = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(2)) {
				found = true;
			}

		}

		assertFalse(found);
		// test to see if exceptions are handled for a negative position
		try {
			list.remove(-1);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

		// test the exception handling for positions greater than size

		try {
			list.remove(10);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e2) {
			assertTrue(true);
		}

	}

	@Test
	public void testGetFirstElement() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// should get 4
		int theElement = (Integer) list.get();

		assertEquals(4, theElement);
	}

	@Test
	public void testGetLast() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// should get 1
		int theElement = (Integer) list.getLast();

		assertEquals(1, theElement);
	}

	@Test
	public void testGetAtPosition() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// will get 2, at 2nd position
		int theElement = (Integer) list.get(2);
		assertEquals(2, theElement);

		// test to see if exceptions are handled for a negative position
		try {
			list.get(-1);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

		// test the exception handling for positions greater than size

		try {
			list.get(10);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e2) {
			assertTrue(true);
		}

	}

	@Test
	public void testSet() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// test if replacing first position - will replace 4 with 5
		list.set(5, 0);

		assertEquals("Tested with 5 at position 0: ", 5, list.get());

		// test replacing last element = will replace 1 with 8 - when entering the size
		list.set(8, 4);

		assertEquals("Tested with 8 at position 3 (last element)", 8, list.getLast());
		
		// test to see if exceptions are handled for a negative position
		try {
			list.set(8, -1);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

		// test the exception handling for positions greater than size

		try {
			list.set(8, 10);
			fail("Exception has not been handled correctly!");
		} catch (IndexOutOfBoundsException e2) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSetInMiddle() 
	{
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.set(10, 2);
		boolean found = false;
		
		//test to make sure two is still not in list
		for(int i = 0; i < list.size(); i++) 
		{
			if(list.get(i).equals(2)) 
			{
				found = true;
				
			}
			
			System.out.println(list.get(i));
		}
		
		assertFalse(found);
	}

	@Test
	public void testContains() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// test if list contains a 2
		boolean found = list.contains(2);
		assertTrue(found);

		// test if to make sure it returns false if not found
		found = list.contains(8);
		assertFalse(found);
	}

	@Test
	public void testIndexOf() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// test to see where 3 is in the list (s/b 1st index)
		int index = list.indexOf(3);
		assertEquals(1, index);

		// test to see if return -1 if not found
		index = list.indexOf(6);
		assertEquals(-1, index);
	}

	@Test
	public void testSize() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		// the size should be four
		assertEquals("Tested with four items: ", 4, list.size());
	}

}
