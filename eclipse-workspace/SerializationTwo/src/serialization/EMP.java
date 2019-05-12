package serialization;

import java.io.Serializable;

public class EMP implements Serializable {

	private static final long serialversionUID = 64861314L;
	
	
	private transient int age; //this will not get serialized
	private static int counter;
	private String name;
	private int id;
	
	public EMP(int age, String name, int id) 
	{
		this.age = age;
		this.name = name;
		this.id = id;
	}
	
	public String toString() 
	{
		return "Age: " + getAge() + 
				"\nID: " + getId() + 
				"\nName: " + getName() + 
				"\nCounter: " + getCounter();
	}

	public int getAge() {
		return age;
	}

	public static void setCounter(int counter) {
		//because the counter is static (call it by it's class name
		EMP.counter = counter;
	}

	public static int getCounter() {
		return counter;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
