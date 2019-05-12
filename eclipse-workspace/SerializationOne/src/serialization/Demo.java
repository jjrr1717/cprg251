package serialization;

import java.io.Serializable;

public class Demo implements Serializable{
	
	//still better to use private - just wanted to make this demo easier
	public int age;
	public String name;
	
	public Demo(int age, String name) 
	{
		this.age = age;
		this.name = name;
	}
	
	public String toString() 
	{
		return "Age: " + age +
				"\nName: " + name;
	}

}
