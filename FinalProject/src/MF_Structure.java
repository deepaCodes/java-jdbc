import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

//https://github.com/sonal-shah/Query-Processor/blob/master/src/edu/stevens/cs562/queryprocessor/
//https://github.com/jgaoxxxx/CS562_Project

public class MF_Structure {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner filein = new Scanner(new File("inputtext.txt"));
		Map<Integer, String> inputMap = new HashMap<Integer, String>();
		int line = 0;
		//To input all the file entries into a hashmap and access it through line number
		while (filein.hasNextLine()) {
			line++;
			inputMap.put(line, filein.nextLine());
		}
		for (Entry<Integer, String> im : inputMap.entrySet()) {
			System.out.println(im.getKey()+" "+ im.getValue());
		
		}
	    String id3=inputMap.get(3);
		String id4 = inputMap.get(4);
	    System.out.println(id3+" "+ id4);
	}

}
