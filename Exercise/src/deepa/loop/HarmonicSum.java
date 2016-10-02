package deepa.loop;

public class HarmonicSum {

	public static void main(String[] args) {

		int n = 5000;
		double s1 = leftToRight(n);
		double s2 = rightToLeft(n);
		double difference = s1 - s2;
		System.out.println("difference :" + difference);

	}

	static double leftToRight(int n) {
		double sum = 0;

		for (double i = 1; i <= n; ++i) {
			sum = sum + 1 / i;
		}

		System.out.println("harmonic sum: " + sum);
		return sum;
	}

	static double rightToLeft(int n) {
		double sum = 0;
		for (double i = n; i >= 1; i--) {
			sum = sum + 1 / i;
		}
		System.out.println("sum2 :" + sum);
		return sum;
	}

}
