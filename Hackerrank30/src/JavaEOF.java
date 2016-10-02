import java.awt.peer.SystemTrayPeer;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class JavaEOF {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. 
         * Your class should be named Solution. */
    	Scanner s = new Scanner(System.in);
    	int line=1;
    	while(s.hasNext()){
    	String d=s.nextLine();
    	System.out.println(line+" "+d);
    	line++;
    	}
    	    	
    	s.close();
    }
}