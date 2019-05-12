package controller;

public class Document {
	
	private String [] authors = new String[10];
	int arrayCounter = 0;
	private String date;
	
	public Document(String [] authors, String date) 
	{
		this.authors = authors;
		this.date = date;
	}
	
	public String [] getAuthors() 
	{
		return authors;
	}
	
	
	public void addAuthor(String name) 
	{
		

	}
	
	
	
	public String getDate() 
	{
		return date;
	}
	

}
