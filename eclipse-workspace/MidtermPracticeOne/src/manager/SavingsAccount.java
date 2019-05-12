package manager;

public class SavingsAccount extends BankAccount {

	private boolean activeAccount = true;;

	public SavingsAccount(double balance, double interest) {
		super(balance, interest);
	}

	public void withdraw(double withdrawal) {
		if (activeAccount) {
			super.withdraw(withdrawal);
			if (super.getBalance() < 25) {
				activeAccount = false;
				System.out.println("Insufficient Funds for withdrawal. Must have $25.00" + " or more to withdrawal");
			}
		}

	}

	public void deposit(double deposit) {
		//double potentialValue = super.getBalance() + deposit;
			super.deposits(deposit);
			if (super.getBalance() > 25 && activeAccount == false) {
				activeAccount = true;
			}
	}

	public void monthlyProcess() {
		int numOfWithdrawals = super.getNumWithdrawals();
		double potentialBalance = getBalance();

		if (numOfWithdrawals > 4) {
			super.setMonthlyServiceCharges((numOfWithdrawals - 4) + 1);
			potentialBalance = getBalance() - numOfWithdrawals;
		}

		if (potentialBalance < 25) {
			activeAccount = false;
		}

		else if (potentialBalance >= 25) {
			activeAccount = true;
			super.monthlyProcess();
		}

	}

}
