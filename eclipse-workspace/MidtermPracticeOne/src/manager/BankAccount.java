package manager;

public abstract class BankAccount {
	
	private double balance = 0;
	private int numDesposits = 0;
	private int numWithdrawals = 0;
	private double annualInterestRate;
	private double monthlyServiceCharges;
	
	public BankAccount(double balance, double interest) 
	{
		this.balance = balance;
		this.annualInterestRate = interest;
	}
	
	public int getNumDesposits() {
		return numDesposits;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public double getMonthlyServiceCharges() {
		return monthlyServiceCharges;
	}

	public void setMonthlyServiceCharges(double monthlyServiceCharges) {
		this.monthlyServiceCharges = monthlyServiceCharges;
	}

	public int getNumWithdrawals() {
		return numWithdrawals;
	}

	public void setNumDesposits(int numDesposits) {
		this.numDesposits = numDesposits;
	}

	public void setNumWithdrawals(int numWithdrawals) {
		this.numWithdrawals = numWithdrawals;
	}

	public void deposits(double deposit) 
	{
		balance += deposit;
		numDesposits++;
	}

	public void withdraw(double withdrawal) 
	{
		balance -= withdrawal;
		numWithdrawals++;
		
	}
	
	public void calcInterest() 
	{
		double monthlyInterestRate = annualInterestRate/12;
		double monthlyInterest = balance * monthlyInterestRate;
		
		balance += monthlyInterest;
	}
	
	public void monthlyProcess() 
	{
		balance -=monthlyServiceCharges;
		calcInterest();
		numDesposits = 0;
		numWithdrawals = 0;
		monthlyServiceCharges = 0;
	}
}
