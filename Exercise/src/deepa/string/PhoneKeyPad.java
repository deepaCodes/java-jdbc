package deepa.string;

import java.util.Scanner;

public class PhoneKeyPad {

	public static void main(String[] args) {
		//String s = "studentloan";
		
		String s="";
		Scanner in= new Scanner(System.in);     //input string dynamically
		System.out.println("enter string");
		s=in.next();
		
		
		
		
		String s1 = s.toLowerCase();
		int i = 0;
		int length = s.length();
		System.out.println("string is: " + s);
		System.out.print("codes are: ");
		while (length > 0) {
			switch (s1.charAt(i)) {

			case 'a':
			case 'b':
			case 'c':
				System.out.print(" " + 2);
				break;
			case 'd':
			case 'e':
			case 'f':
				System.out.print(" " + 3);
				break;
			case 'g':
			case 'h':
			case 'i':
				System.out.print(" " + 4);
				break;
			case 'j':
			case 'k':
			case 'l':
				System.out.print(" " + 5);
				break;
			case 'm':
			case 'n':
			case 'o':
				System.out.print(" " + 6);
				break;
			case 'p':
			case 'q':
			case 'r':
			case 's':
				System.out.print(" " + 7);
				break;
			case 't':
			case 'u':
			case 'v':
				System.out.print(" " + 8);
				break;
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				System.out.print(" " + 9);
				break;

			}
			i++;
			length--;
		}

	}

}
