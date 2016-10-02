package deepa.loop;

public class SumAndAverageMain {

	public static void main(String[] args) {
		float[] a = { 1.5f, 2.7f, 3.0f, 4.0f, 6.1f, 100.6f };

		SumAndAverage sa = new SumAndAverage();
		sa.setarray(a);
		sa.whiledo();
		sa.forloop();

	}

}
