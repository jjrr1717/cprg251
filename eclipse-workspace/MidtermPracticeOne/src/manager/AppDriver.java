package manager;

public class AppDriver {
	public static void main (String [] args) 
	{
		SavingsAccount test = new SavingsAccount(100, 10);
		
		System.out.println(test.getBalance());
		test.deposit(300);
		System.out.println(test.getBalance());
		test.withdraw(380);
		System.out.println(test.getBalance());
		test.withdraw(10);
		System.out.println(test.getBalance());
		test.deposit(2);
		System.out.println(test.getBalance());
		test.deposit(50);
		System.out.println(test.getBalance());
		System.out.println(test.getNumDesposits());
		System.out.println(test.getNumWithdrawals());
		test.calcInterest();
		System.out.println(test.getBalance());
		System.out.println(test.getNumDesposits());
		System.out.println(test.getNumWithdrawals());
		test.monthlyProcess();
		System.out.println(test.getBalance());
		System.out.println(test.getNumDesposits());
		System.out.println(test.getNumWithdrawals());
		
		
		
	}

}
