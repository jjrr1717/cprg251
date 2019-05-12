package inheritance_practice;

public class MySubClass extends MySuperClass {
	public MySubClass() 
	{
		System.out.println("I'm the Subclass constructor!");
	}
	
	public void methodA() 
	{
		System.out.println("I'm method A in Subclass!");
		super.methodA();
	}
	
	public String sayGoodMorning(String name) 
	{
		return super.sayGoodMorning() + " " + name + "!";
	}

}
