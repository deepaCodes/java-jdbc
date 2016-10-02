package deepa.loop;

public class TribonacciSeries {

	public static void main(String[] args) {
	int n=3;
	int t1=1,tn;
	int t2=1;
	int t3=2;
	
		System.out.print(" "+ t1 +" "+ t2 + " "+ t3);
	while(n<=20){
		
		tn=t1+t2+t3;
		t1=t2;
		t2=t3;
		t3=tn;
		n++;
		System.out.print("  "+ tn);
	}
	
	
	
	
	}

}
