import java.util.*;
import java.io.*;

class Hashmapdic {
	public static void main(String[] argh) {
		Scanner s = new Scanner(System.in);

		Map<String, Integer> dict = new HashMap<String, Integer>();
		int N = s.nextInt();
		s.nextLine();
		for (int i = 0; i < N; i++) {
			String name = s.nextLine();
			int number = s.nextInt();
			s.nextLine();
			dict.put(name, number);
		}

		System.out.println("Enter the name of the person you want to search");
		while (s.hasNext()) {
			String sname = s.nextLine();

			if (dict.containsKey(sname)) {
				System.out.println(sname + "= " + dict.get(sname));
			} else {
				System.out.println("Not found");
			}
		}
	}
}

