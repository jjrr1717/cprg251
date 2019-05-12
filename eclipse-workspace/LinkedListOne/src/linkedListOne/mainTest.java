package linkedListOne;

public class mainTest {
	public static void main(String [] args) 
	{
		SLL myList = new SLL();
		
		myList.add(1);
		myList.append(2);
		myList.add(3, 1);
		myList.printList();
		myList.remove();
		System.out.println("*****************************");
		myList.printList();
		myList.add(1);
		myList.add(0);
		System.out.println("*******************************");
		myList.printList();
		myList.removeLast();
		System.out.println("*******************************");
		myList.printList();
		myList.remove(1);
		System.out.println("*******************************");
		myList.printList();
		System.out.println("*******************************");
		System.out.println(myList.get());
		System.out.println("*******************************");
		System.out.println(myList.getLast());
		System.out.println("*******************************");
		System.out.println(myList.get(0));
		
	}

}
