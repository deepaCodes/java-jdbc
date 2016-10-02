package bankpakage;

public class TestBank {

	public static void main(String[] args) {

		BankOperation b= new BankOperation();
		b.addAccount("deepa", "deepa".hashCode(), 100);
		b.addAccount("ramesh", "ramesh".hashCode(), 100000);
		b.addAccount("rames", "rames".hashCode(), 20);
		b.printacc();
		System.out.println("-------------------");
		b.changeName(-938314596, "tikla");
		
		b.setBalance(95461621, 25000);
		b.printacc();

	}

}
