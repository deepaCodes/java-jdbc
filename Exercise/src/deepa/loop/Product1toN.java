package deepa.loop;

public class Product1toN {

	public static void main(String[] args) {
		int product = 1, n = 12;
		for (int i = 1; i <= n; i++) {
			product = product * i;
		}
		System.out.println("product : " + product);

	}
}