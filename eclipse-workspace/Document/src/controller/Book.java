package controller;

public class Book extends Document {
	
	public Book(String[] authors, String date) {
		super(authors, date);
		// TODO Auto-generated constructor stub
	}



	private String title;
	
	
	
	public String getTitle() 
	{
		return title;
	}

}
