package deepa.string;

public class BinaryToDecimal {

	public static void main(String[] args) {
		int j = 0;
		String binary = "1011";
		int decimal = 0;
		for (int i = binary.length() - 1; i >= 0; i--) {
			if (binary.charAt(i) == '1') {
				decimal += 1 * (Math.pow(2.00, j));
			

			} else {
				decimal += 0;
			}

		j++;
		}

		System.out.println("binary  " + binary);
		System.out.println("decimal " + decimal);
	}

}
