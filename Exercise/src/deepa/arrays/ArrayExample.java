package deepa.arrays;

public class ArrayExample {

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 6, 7, 8 };
		int[] a2 = { 100, 9, 500, 3, 7, 50, 1 };
		int val = 8, max = 0, min = 0;
		System.out.println(" length of array:  " + a1.length);

		for (int i = 0; i < a1.length; i++) {
			if (a1[i] == val) {

				System.out.println("position of 7:  " + (i + 1));
			}

		}

		for (int i = 0; i < a2.length; i++) {
			for (int y = 0; y < a2.length; y++) {
				if (a2[i] > a2[y] && a2[i] > max) {
					max = a2[i];
				}
			}
		}
		System.out.println("max:  " + max);

		for (int i = 0; i < a2.length; i++) {
			if (a2[i] > a2[a2.length -1 - i] && a2[i] > min) {
				min = a2[i];
			} 

		}
		System.out.println("min="+min);
	}

}
