package deepa.loop;

public class FibonacciSeries {

	public static void main(String[] args) {
		
	int fn;
	int fn1=1;
	int fn2=1;
	int n=2;
	double sum=2, avg=0;
	
	System.out.print(fn1+  " "+ fn2 );
	while(n<=20){
		fn=fn1+fn2;
		fn1=fn2;
		fn2=fn;
		sum=sum+fn;
		System.out.print(" " + fn);
		n++;
	}
	
	
	avg=sum/20;
	System.out.println("\naverage= "+ avg);
	
	}
	
	

}
