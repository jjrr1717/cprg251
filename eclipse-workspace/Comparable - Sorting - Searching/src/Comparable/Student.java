package Comparable;

public class Student implements Comparable<Student> {
	private int age;
	private String name;
	private long id;
	
	public Student(int age, String name, long id) {
		this.age = age;
		this.name = name;
		this.id = id;
	}

	public int getAge() {
		return age;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String toString () {
		return "Age: " + getAge() + 
				" Name: " + getName() +
				" Id: " + getId();
	}


	 @Override	
	 public int compareTo(Student o) {
		int result = 1;
		if(this.getAge() < o.getAge())
			result = -1;
		else if(this.getAge() == o.getAge())
			result = 0;
		else
			result = 1;
		return result;
	}

}
