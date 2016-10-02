package deepa.loop;

public class SumAndAverage {

	private float[] a;

	public void setarray(float[] a) {
		this.a = a;
	}

	public void whiledo() {
		float sum = 0;
		int i = 0;
		do {
			sum = sum + a[i];
			i++;
		} while (i < a.length);
		float avg = sum / a.length;
		System.out.println("length: " + a.length);
		System.out.println("sum = " + sum);
		System.out.println("average= " + avg);

	}

	public void forloop() {
		float sum = 0;
		double avg;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];
		}

		avg = sum / a.length;
		System.out.println("length for: " + a.length);
		System.out.println("sum for = " + sum);
		System.out.println("average for= " + avg);
	}
}
