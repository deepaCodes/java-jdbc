package deepa.loop;

public class ComputePI {

	public static void main(String[] args) {
		int n = 10000000;
		double sum = 0;
		for (double i = 1; i <= n; i = i + 2) {
			if (i % 4 == 1) {
				sum = sum + 1 / i;
			}
			if (i % 4 == 3) {
				sum = sum - 1 / i;
			}
		}
	/*	for(int i=0;i<5;i++){
			System.out.println("\t"+ i);
			if(i==3){
				continue; //break
			}
			System.out.println("out of if after break");
		}*/
		System.out.println("PI value : " + sum*4);
		System.out.println("PI computer: " + Math.PI);
	}

}
