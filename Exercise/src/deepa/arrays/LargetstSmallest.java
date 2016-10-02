package deepa.arrays;

public class LargetstSmallest {

	public static void main(String[] args) {

		int[] a = { 30, 6, 7, 80, 10 };
		int max = 0;
		for (int i = 0; i < a.length - 1; i++) {

			if (a[i] > a[i + 1] && a[i] > max) {
				max = a[i];
			}

		}
		System.out.println("max=" + max);
	}

}
