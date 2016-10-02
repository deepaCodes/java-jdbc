import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Binary {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		int rem;
		
		for (int i = 0; i < T; i++) {
			List<Integer> mylist  = new ArrayList<Integer>();
			int x = s.nextInt();
			if (x <= 1) {
				System.out.println(x);
			} else {
				while (x >= 1) {
					rem = x % 2;
					mylist.add(rem);
					x = x / 2;
				}
			}
			for(int j=mylist.size()-1;j>=0;j--){
				System.out.print(mylist.get(j));
			}
			System.out.println();
		}
	}
}
