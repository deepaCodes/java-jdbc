package deepa.loop;

public class CozaLozaWoza {

	public static void main(String[] args) {

		for (int i = 1; i <= 110; i++) {

			if (i % 3 == 0 && i % 5 != 0 && i % 7 != 0) {
				System.out.print(" coza ");
			}
			if (i % 5 == 0 && i % 3 != 0 && i % 7 != 0) {
				System.out.print(" loza ");

			}
			if (i % 7 == 0 && i % 3 != 0 && i % 5 != 0) {
				System.out.print(" wooza ");
			}
			if (i % 3 == 0 && i % 5 == 0) {
				System.out.print("coza-Loza");
			}
			if (i % 5 == 0 && i % 7 == 0) {
				System.out.print("Loza-Wooza");
			}
			if (i % 3 == 0 && i % 7 == 0) {
				System.out.print("coza-Wooza");
			}

			if (i % 3 != 0 && i % 5 != 0 && i % 7 != 0) {
				System.out.print(" " + i);
			}
			if (i % 11 == 0)
				System.out.println(" ");

		}

	}
}
