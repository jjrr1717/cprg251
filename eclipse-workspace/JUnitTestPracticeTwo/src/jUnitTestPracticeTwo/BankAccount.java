package jUnitTestPracticeTwo;

public class BankAccount {

	private String name;
	private long accountNumber;
	private String address;
	private double balance = 0;
	private double draft;
	private double monthlyCharge;
	private int numberOfWithdraw;
	
	
	public BankAccount() 
	{
	}
	
	public BankAccount(String name, long accountNumber, String address, double balance, double draft) 
	{
		this.name = name;
		this.accountNumber = accountNumber;
		this.address = address;
		this.balance = balance;
		this.draft = draft;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountNumber() {
		return accountNumber;
	}


	public double getDraft() {
		return draft;
	}

	public void setDraft(double draft) {
		this.draft = draft;
	}
	
	public void deposit(double amount) 
	{
		if(amount >=0) 
		{
			balance += amount;
		}
		else 
		{
			System.out.println("Denied");
		}
	}
	
	public double getBalance() {
		double balance = 0;
		if(this.balance >=0) 
		{
			balance = this.balance;
		}
		
		return balance;
	}

	public void withdraw(double amount) 
	{
		double allowedAmount = getBalance() + getDraft();
		if(amount < allowedAmount && amount >=0) 
		{
			balance -=amount;
		}
		else 
		{
			System.out.println("Denied");
		}
	}
	

	
	
}
