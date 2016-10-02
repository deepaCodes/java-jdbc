package deepa.conditional;

public class OddEven {

	public static void main(String[] args) {
		int number;
		number = 23;
		if (number % 2 == 0) {
			System.out.println("even");
		} else {
			System.out.println("odd");
		}

		switch (number % 2) {
		case 0:
			System.out.println("even");
			break;
		default:
			System.out.println("odd");

		}
	}

}
