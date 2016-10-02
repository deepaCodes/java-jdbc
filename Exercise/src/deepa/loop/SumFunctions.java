package deepa.loop;

public class SumFunctions {

	void addbetweentwo() {
		int a = 111, b = 8899;
		int sum = 0, avg = 0, count = 0;
		for (int i = a; i <= b; i++) {
			sum += i;
			count++;
		}
		avg = sum / count;
		System.out.println("sum= :" + sum + " average: " + avg + "count: "
				+ count);
	}

	void Addoddnumbers() {
		int sum = 0, avg = 0, count = 0;
		for (int i = 1; i <= 100; i++) {
			if (i % 2 != 0) {
				sum = sum + i;
				count++;
			}
		}
		avg = sum / count;
		System.out.println("sum of odd numbers: " + sum + " average : " + avg
				+ " count: " + count);
	}

	void Divisibleby7() {
		int sum = 0, count = 0, avg = 0;
		for (int i = 1; i <= 100; i++) {
			if (i % 7 == 0) {
				count++;
				sum = sum + i;
			}
		}
		avg = sum / count;
		System.out.println("sum divisible by 7: " + sum + " average : " + avg
				+ " count: " + count);
	}

	void SumOfSquares() {
		int sum = 0, square = 0;
		for (int i = 1; i <= 100; i++) {
			square = i * i;
			sum = sum + square;
		}
		System.out.println("sum of squares :" + sum);
	}

}
