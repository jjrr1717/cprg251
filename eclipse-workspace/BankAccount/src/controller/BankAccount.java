package controller;

public abstract class BankAccount 
{
	
	String owner;
	double balance;
	
	public void deposit(double amount) 
	{
		balance += amount;
	}
	
	
	public abstract void withdrawal(double amount);
	
	

}
