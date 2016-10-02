package assignment2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(" test \f  this is test");
		
		System.out.printf("%1s  %7s   %-7s   %-6s   %-6s%n", "n", "result1", "result2", "time1", "time2");
		System.out.printf("%1d  %7.2f   %7.1f   %4dms   %4dms%n", 5, 1000F, 20000F, 1000, 1250);
		System.out.printf("%1d  %7.2f   %7.1f   %4dms   %4dms%n", 6, 300F, 700F, 200, 950);
		
		System.out.format("%1s%15s%15s%15s%20s",
			    "Grade", "Last Name", "First Name", "Student Number", "Parent Email");
		
		System.out.println();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(df.format(new Date("1/01/2005")));
	}

}
