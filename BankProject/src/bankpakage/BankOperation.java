package bankpakage;


public class BankOperation {

	private Bank bank = new Bank();

	public Boolean createAccount(String name) {

		return bank.createAccount(name);
	}

	public void addAccount(String name, int number, double balance) {
		bank.setAccount(name, number, balance);
	}

	public void setBalance(int accountNumber, double balance) {
		bank.setBalance(accountNumber, balance);

	}

	public void changeName(int accountNumber, String name) {
		bank.changeName(accountNumber, name);

	}

	public void printacc() {
		bank.printAccount();
	}
	

}
